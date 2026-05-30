package com.sarinsa.plushandstuff.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sarinsa.plushandstuff.common.color_provider.IColorProvider;
import com.sarinsa.plushandstuff.common.core.registry.PNSRecipes;
import com.sarinsa.plushandstuff.common.core.registry.PNSSewingMaterials;
import com.sarinsa.plushandstuff.common.sewing.SewingMaterial;
import net.minecraft.core.Registry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SewingRecipe implements Recipe<VarargItemInput> {
    
    public static final MapCodec<SewingRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            Recipe.CommonInfo.MAP_CODEC.forGetter( o -> o.commonInfo ),
                            Group.DIRECT_CODEC.fieldOf( "plushie_group" ).forGetter( o -> o.group ),
                            Codec.INT.fieldOf( "material_count" ).forGetter( o -> o.materialCount ),
                            ItemStackTemplate.CODEC.fieldOf( "result" ).forGetter( o -> o.result )
                    )
                    .apply( instance, SewingRecipe::new )
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, SewingRecipe> STREAM_CODEC = StreamCodec.composite(
            Recipe.CommonInfo.STREAM_CODEC,
            o -> o.commonInfo,
            Group.STREAM_CODEC,
            o -> o.group,
            ByteBufCodecs.INT,
            o -> o.materialCount,
            ItemStackTemplate.STREAM_CODEC,
            o -> o.result,
            SewingRecipe::new
    );
    
    /** The serializer used by sewing recipes. */
    public static final RecipeSerializer<SewingRecipe> SERIALIZER = new RecipeSerializer<>( MAP_CODEC, STREAM_CODEC );
    
    /** The plushie group for this recipe. */
    private final Group group;
    /** The amount of sewing materials needed for this recipe. */
    private final int materialCount;
    /** The crafting result of this recipe. */
    private final ItemStackTemplate result;
    
    /** The common info holder for this recipe. */
    private final Recipe.CommonInfo commonInfo;
    /**
     * Inventory placement info for this recipe.
     * This is created and cached when requested with {@link SewingRecipe#placementInfo()}.
     */
    @Nullable
    private PlacementInfo placementInfo;
    
    
    public SewingRecipe( Recipe.CommonInfo commonInfo, Group group,
                         int materialCount, ItemStackTemplate result ) {
        this.commonInfo = commonInfo;
        this.group = group;
        this.materialCount = materialCount;
        this.result = result;
    }
    
    /** @return True if the given specified input matches the ingredients required to craft this recipe. */
    @Override
    public boolean matches( VarargItemInput input, Level level ) {
        // Amount of input items must match material count
        if( input.size() != materialCount ) {
            return false;
        }
        final Registry<SewingMaterial> registry = level.registryAccess().lookupOrThrow( PNSSewingMaterials.REGISTRY_KEY );
        final Map<Item, IColorProvider> map = registry.stream()
                .collect( Collectors.toMap( ( m ) -> m.item().value(), SewingMaterial::colorProvider ) );
        
        // Make sure every input is a recognized item in a registered SewingMaterial
        for( ItemStack itemStack : input ) {
            if( !map.containsKey( itemStack.getItem() ) ) {
                return false;
            }
        }
        return true;
    }
    
    /** Assembles this recipe. This creates a new item stack from the recipe's result. */
    @Override
    public ItemStack assemble( VarargItemInput input ) {
        return result.create();
    }
    
    /** @return Whether this recipe should show a notification when it is unlocked in the recipe book. */
    @Override
    public boolean showNotification() {
        return commonInfo.showNotification();
    }
    
    /** @return This recipe's group. Used by the recipe manager to display recipe info. */
    @Override
    public String group() {
        return group.getSerializedName();
    }
    
    /** @return This recipe's serializer. */
    @Override
    public RecipeSerializer<? extends Recipe<VarargItemInput>> getSerializer() {
        return PNSRecipes.SEWING_SERIALIZER.get();
    }
    
    /** @return This recipe's recipe type. */
    @Override
    public RecipeType<? extends Recipe<VarargItemInput>> getType() {
        return PNSRecipes.SEWING_TYPE.get();
    }
    
    /** @return This recipe's placement info. Creates and returns a new instance if one does not already exist. */
    @Override
    public PlacementInfo placementInfo() {
        if( placementInfo == null ) {
            // TODO - Perhaps we need a custom ingredient for our shenanigans.
            //        Or maybe an empty list is fine, we'll see
            placementInfo = PlacementInfo.create( List.of() );
        }
        return placementInfo;
    }
    
    /** @return This recipe's recipe book category. */
    @Override
    public RecipeBookCategory recipeBookCategory() {
        return PNSRecipes.SEWING_CATEGORY.get();
    }
    
    
    /**
     * Represents a plushie group. This is used by both the sewing machine block
     * and the recipe manager for display info.
     */
    public enum Group implements StringRepresentable {
        ANIMALS( "animals" ),
        MONSTERS( "monsters" ),
        CREATURES( "creatures" ),
        OTHER( "other" );
        
        public static final Codec<Group> DIRECT_CODEC = StringRepresentable.fromEnum( Group::values );
        
        public static final StreamCodec<RegistryFriendlyByteBuf, Group> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8, Group::getSerializedName, Group::fromName
        );
        
        final String name;
        
        Group( String name ) {
            this.name = name;
        }
        
        
        /** @return This group's serializable identity name. */
        @Override
        public String getSerializedName() {
            return name;
        }
        
        /**
         * @return The group that corresponds to the specified name.
         * @throws IllegalArgumentException if no group with the specified name exists.
         */
        public static Group fromName( String name ) {
            for( Group group : Group.values() ) {
                if( group.name.equals( name ) ) {
                    return group;
                }
            }
            throw new IllegalArgumentException( "Group '" + name + "' does not exist!" );
        }
    }
}
