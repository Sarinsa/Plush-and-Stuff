package com.sarinsa.plushandstuff.common.plushie;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sarinsa.plushandstuff.common.recipe.SewingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;

public class PlushieType {
    
    public static final Codec<PlushieType> DIRECT_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            EntityType.CODEC.fieldOf( "entity_type" ).forGetter( o -> o.entityType ),
                            Codec.list( Recipe.KEY_CODEC ).fieldOf( "sewing_recipes" ).forGetter( o -> o.sewingRecipeIds )
                    )
                    .apply( instance, PlushieType::new )
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, PlushieType> STREAM_CODEC = StreamCodec.composite(
            EntityType.STREAM_CODEC,
            o -> o.entityType,
            ResourceKey.streamCodec( Registries.RECIPE ).apply( ByteBufCodecs.list() ),
            o -> o.sewingRecipeIds,
            PlushieType::new
    );
    
    private final EntityType<?> entityType;
    private final List<ResourceKey<Recipe<?>>> sewingRecipeIds;
    
    
    public PlushieType( EntityType<?> entityType, List<ResourceKey<Recipe<?>>> sewingRecipeIds ) {
        this.entityType = entityType;
        this.sewingRecipeIds = sewingRecipeIds;
    }
    
    public EntityType<?> getEntityType() {
        return entityType;
    }
    
    public Iterable<ResourceKey<Recipe<?>>> sewingRecipeIds() {
        return sewingRecipeIds;
    }
    
    public boolean containsRecipe( Identifier recipeId ) {
        return sewingRecipeIds.contains( ResourceKey.create( Registries.RECIPE, recipeId ) );
    }
    
    public boolean containsRecipe( ResourceKey<SewingRecipe> recipeKey ) {
        return sewingRecipeIds.contains( recipeKey );
    }
}
