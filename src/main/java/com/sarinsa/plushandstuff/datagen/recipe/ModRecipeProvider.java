package com.sarinsa.plushandstuff.datagen.recipe;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    
    protected ModRecipeProvider( HolderLookup.Provider registries, RecipeOutput output ) {
        super( registries, output );
    }
    
    @Override
    protected void buildRecipes() {
    
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
