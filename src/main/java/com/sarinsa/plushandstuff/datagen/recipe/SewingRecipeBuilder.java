package com.sarinsa.plushandstuff.datagen.recipe;

import com.sarinsa.plushandstuff.common.recipe.SewingRecipe;
import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Recipe;

import javax.annotation.Nullable;

public class SewingRecipeBuilder implements RecipeBuilder {
    
    private final int materialCount;
    private final ItemStackTemplate result;
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    
    private SewingRecipe.Group group = SewingRecipe.Group.OTHER;
    @Nullable
    private ResourceKey<Recipe<?>> customId = null;
    
    
    private SewingRecipeBuilder( int materialCount, ItemStackTemplate result ) {
        this.materialCount = materialCount;
        this.result = result;
    }
    
    
    /** @return A new sewing recipe builder with the specified arguments. */
    public static SewingRecipeBuilder of( int materialCount, ItemStackTemplate template ) {
        return new SewingRecipeBuilder( materialCount, template );
    }
    
    /** @return A new sewing recipe builder with the specified arguments. */
    public static SewingRecipeBuilder of( int materialCount, ItemStackTemplate template, @Nullable ResourceKey<Recipe<?>> customId ) {
        return new SewingRecipeBuilder( materialCount, template ).withId( customId );
    }
    
    
    public SewingRecipeBuilder withId( @Nullable ResourceKey<Recipe<?>> customId ) {
        this.customId = customId;
        return this;
    }
    
    @Override
    public SewingRecipeBuilder unlockedBy( String name, Criterion<?> criterion ) {
        advancementBuilder.unlockedBy( name, criterion );
        return this;
    }
    
    @Override
    public SewingRecipeBuilder group( @Nullable String group ) {
        return group( SewingRecipe.Group.fromName( group == null ? SewingRecipe.Group.OTHER.name() : group ) );
    }
    
    public SewingRecipeBuilder group( SewingRecipe.Group group ) {
        this.group = group;
        return this;
    }
    
    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId( result );
    }
    
    @Override
    public void save( RecipeOutput output, ResourceKey<Recipe<?>> id ) {
        final SewingRecipe recipe = new SewingRecipe(
                RecipeBuilder.createCraftingCommonInfo( true ),
                group,
                materialCount,
                result
        );
        output.accept( customId != null ? customId : id, recipe, advancementBuilder.build( output, id, RecipeCategory.DECORATIONS ) );
    }
}
