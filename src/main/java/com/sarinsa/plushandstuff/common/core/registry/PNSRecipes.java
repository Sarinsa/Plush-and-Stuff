package com.sarinsa.plushandstuff.common.core.registry;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import com.sarinsa.plushandstuff.common.recipe.SewingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class PNSRecipes {
    
    /**
     * Called from {@link PlushStuff#PlushStuff(IEventBus, ModContainer)}
     * to register the deferred registers in this class.
     */
    public static void register( IEventBus modBus ) {
        TYPE_REGISTRY.register( modBus );
        CATEGORY_REGISTRY.register( modBus );
    }
    
    //
    //------------------------------------ Recipe types ------------------------------------
    //
    public static final DeferredRegister<RecipeType<?>> TYPE_REGISTRY = DeferredRegister.create( Registries.RECIPE_TYPE, PlushStuff.MODID );
    
    public static final Supplier<RecipeType<SewingRecipe>> SEWING_TYPE = TYPE_REGISTRY.register( "sewing", () -> RecipeType.simple( PlushStuff.id( "sewing" ) ) );
    
    
    //
    //-------------------------------- Recipe book category --------------------------------
    //
    public static final DeferredRegister<RecipeBookCategory> CATEGORY_REGISTRY = DeferredRegister.create( Registries.RECIPE_BOOK_CATEGORY, PlushStuff.MODID );
    
    public static final Supplier<RecipeBookCategory> SEWING_CATEGORY = CATEGORY_REGISTRY.register( "sewing", RecipeBookCategory::new );
    
    
    // Utility class
    private PNSRecipes() { }
}
