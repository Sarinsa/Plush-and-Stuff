package com.sarinsa.plushandstuff.common.plushie;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sarinsa.plushandstuff.common.core.registry.PNSPlushieTypes;
import com.sarinsa.plushandstuff.common.recipe.SewingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
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
    
    /** @return This plushie type's reference entity. */
    public EntityType<?> getEntityType() {
        return entityType;
    }
    
    /** @return An iterable of this plushie type's associated recipes. */
    public Iterable<ResourceKey<Recipe<?>>> sewingRecipeIds() {
        return sewingRecipeIds;
    }
    
    /** @return True if this plushie type contains the specified recipe ID. */
    public boolean containsRecipe( Identifier recipeId ) {
        return sewingRecipeIds.contains( ResourceKey.create( Registries.RECIPE, recipeId ) );
    }
    
    /** @return True if this plushie type contains the specified recipe key. */
    public boolean containsRecipe( ResourceKey<SewingRecipe> recipeKey ) {
        return sewingRecipeIds.contains( recipeKey );
    }
    
    /**
     * @return True if the specified entity type has any plushie types associated with it.
     * The plushie type registry is synced, so this method should yield the same
     * results on both server and client.
     */
    public static boolean isPlushieEntity( RegistryAccess registryAccess, EntityType<?> entityType ) {
        final Registry<PlushieType> registry = registryAccess.lookupOrThrow( PNSPlushieTypes.REGISTRY_KEY );
        
        for( PlushieType plushieType : registry ) {
            if( plushieType.entityType == entityType )
                return true;
        }
        return false;
    }
}
