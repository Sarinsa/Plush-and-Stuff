package com.sarinsa.plushandstuff.datagen.recipe;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import com.sarinsa.plushandstuff.common.trigger.SketchEntityTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static com.sarinsa.plushandstuff.common.core.registry.PNSRecipes.RecipeKeys;

public class ModRecipeProvider extends RecipeProvider {
    
    protected ModRecipeProvider( HolderLookup.Provider registries, RecipeOutput output ) {
        super( registries, output );
    }
    
    @Override
    protected void buildRecipes() {
        sewing( 3, new ItemStackTemplate( Items.ZOMBIE_HEAD ), EntityType.ZOMBIE, RecipeKeys.Sewing.ZOMBIE )
                .save( output );
        sewing( 4, new ItemStackTemplate( Items.CREEPER_HEAD ), EntityType.CREEPER, RecipeKeys.Sewing.CREEPER )
                .save( output );
    }
    
    /**
     * @param materialCount  The amount of sewing materials needed as input.
     * @param result         The result the recipe will produce.
     * @param requiredEntity The entity that unlocks this recipe, when noted down in a sketchbook.
     * @param recipeId       The registry ID of the finished recipe.
     * @return A new sewing recipe builder
     */
    private SewingRecipeBuilder sewing( int materialCount, ItemStackTemplate result,
                                        EntityType<?> requiredEntity, @Nullable ResourceKey<Recipe<?>> recipeId ) {
        return SewingRecipeBuilder.of( materialCount, result, recipeId )
                .unlockedBy( "has_sketched", SketchEntityTrigger.TriggerInstance.forEntity( requiredEntity ) );
    }
    
    
    public static final class Runner extends RecipeProvider.Runner {
        
        public Runner( PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider ) {
            super( output, lookupProvider );
        }
        
        @Override
        protected RecipeProvider createRecipeProvider( HolderLookup.Provider lookupProvider, RecipeOutput output ) {
            return new ModRecipeProvider( lookupProvider, output );
        }
        
        @Override
        public String getName() {
            return PlushStuff.MODID + " recipes";
        }
    }
}
