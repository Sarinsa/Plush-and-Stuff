package com.sarinsa.plushandstuff.datagen.recipe;

import com.sarinsa.plushandstuff.common.recipe.SewingRecipe;
import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.Nullable;

// TODO
public class SewingRecipeBuilder implements RecipeBuilder {
    
    private SewingRecipe.Group group;
    
    
    @Override
    public RecipeBuilder unlockedBy( String name, Criterion<?> criterion ) {
        return this;
    }
    
    @Override
    public RecipeBuilder group( @Nullable String group ) {
        return group( SewingRecipe.Group.fromName( group == null ? SewingRecipe.Group.OTHER.name() : group ) );
    }
    
    public RecipeBuilder group( SewingRecipe.Group group ) {
        this.group = group;
        return this;
    }
    
    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return null;
    }
    
    @Override
    public void save( RecipeOutput output, ResourceKey<Recipe<?>> location ) {
    
    }
}
