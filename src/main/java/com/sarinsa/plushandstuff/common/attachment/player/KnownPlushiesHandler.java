package com.sarinsa.plushandstuff.common.attachment.player;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sarinsa.plushandstuff.common.core.registry.PNSPlushieTypes;
import com.sarinsa.plushandstuff.common.core.registry.PNSRecipes;
import com.sarinsa.plushandstuff.common.plushie.PlushieType;
import com.sarinsa.plushandstuff.common.recipe.SewingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class KnownPlushiesHandler {
    
    public static final Codec<KnownPlushiesHandler> DIRECT_CODEC = RecordCodecBuilder.create(
            ( instance ) -> instance.group(
                            Codec.list( PlushieType.DIRECT_CODEC ).fieldOf( "known_plushies" ).forGetter( o -> o.knownPlushies )
                    )
                    .apply( instance, KnownPlushiesHandler::new )
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, KnownPlushiesHandler> STREAM_CODEC = StreamCodec.composite(
            PlushieType.STREAM_CODEC.apply( ByteBufCodecs.list() ),
            o -> o.knownPlushies,
            KnownPlushiesHandler::new
    );
    
    
    private final List<PlushieType> knownPlushies;
    
    public KnownPlushiesHandler( List<PlushieType> knownPlushies ) {
        this.knownPlushies = knownPlushies;
    }
    
    public KnownPlushiesHandler() {
        this.knownPlushies = new ArrayList<>();
    }
    
    /**
     * Checks if the specified entity type has any plushie types associated with it.
     * If the entity type does have associated plushie types, they will be added to
     * this handler's list of known types if they aren't already present.
     *
     * @param level      The server level object. Need for registry lookup access.
     * @param entityType The entity type to check.
     * @return True if any plushie types were added to the list of known types.
     */
    public boolean maybeAddKnownEntity( ServerLevel level, EntityType<?> entityType ) {
        final Registry<PlushieType> registry = level.registryAccess().lookupOrThrow( PNSPlushieTypes.REGISTRY_KEY );
        
        boolean added = false;
        
        for( PlushieType plushieType : registry ) {
            if( plushieType.getEntityType() == entityType ) {
                // Only add if list does not contain the type already
                if( !knownPlushies.contains( plushieType ) ) {
                    knownPlushies.add( plushieType );
                    added = true;
                }
            }
        }
        return added;
    }
    
    /** @return True if this handler's internal map contains the specified entity type as a key. */
    public boolean knowsEntity( EntityType<?> entityType ) {
        for( PlushieType plushieType : knownPlushies ) {
            if( plushieType.getEntityType() == entityType )
                return true;
        }
        return false;
    }
    
    /** @return The list of sewing recipes associated with the specified entity type. */
    @Nullable
    public Iterable<SewingRecipe> getRecipesForType( ServerLevel level, EntityType<?> entityType ) {
        final List<ResourceKey<Recipe<?>>> recipeIds = new ArrayList<>();
        
        for( PlushieType plushieType : knownPlushies ) {
            for( ResourceKey<Recipe<?>> recipeId : plushieType.sewingRecipeIds() ) {
                // Multiple plushie types can technically contain references to the same recipe, so check for duplicates
                if( !recipeIds.contains( recipeId ) ) {
                    recipeIds.add( recipeId );
                }
            }
        }
        final RecipeManager recipeManager = level.recipeAccess();
        
        // Collect all registered sewing recipes and filter out unobtained ones
        return recipeManager.recipeMap().byType( PNSRecipes.SEWING_TYPE.get() ).stream()
                .filter( holder -> recipeIds.contains( holder.id() ) )
                .map( RecipeHolder::value )
                .toList();
    }
}
