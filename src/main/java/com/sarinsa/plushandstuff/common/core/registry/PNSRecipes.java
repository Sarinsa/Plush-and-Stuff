package com.sarinsa.plushandstuff.common.core.registry;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import com.sarinsa.plushandstuff.common.recipe.SewingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
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
        SERIALIZER_REGISTRY.register( modBus );
    }
    
    
    public static class RecipeKeys {
        
        public static class Sewing {
            public static final ResourceKey<Recipe<?>> ZOMBIE = of( "zombie" );
            public static final ResourceKey<Recipe<?>> CREEPER = of( "creeper" );
            
            static ResourceKey<Recipe<?>> of( String path ) {
                return ResourceKey.create( Registries.RECIPE, PlushStuff.id( path ) );
            }
        }
    }
    
    //
    //------------------------------------ Recipe types ------------------------------------
    //
    public static final DeferredRegister<RecipeType<?>> TYPE_REGISTRY = DeferredRegister.create( Registries.RECIPE_TYPE, PlushStuff.MODID );
    
    public static final Supplier<RecipeType<SewingRecipe>> SEWING_TYPE = TYPE_REGISTRY.register( "sewing", () -> RecipeType.simple( PlushStuff.id( "sewing" ) ) );
    
    
    //
    //--------------------------------- Recipe Serializers ---------------------------------
    //
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZER_REGISTRY = DeferredRegister.create( Registries.RECIPE_SERIALIZER, PlushStuff.MODID );
    
    public static final Supplier<RecipeSerializer<SewingRecipe>> SEWING_SERIALIZER = SERIALIZER_REGISTRY.register( "sewing", () -> SewingRecipe.SERIALIZER );
    
    
    //
    //-------------------------------- Recipe book category --------------------------------
    //
    public static final DeferredRegister<RecipeBookCategory> CATEGORY_REGISTRY = DeferredRegister.create( Registries.RECIPE_BOOK_CATEGORY, PlushStuff.MODID );
    
    public static final Supplier<RecipeBookCategory> SEWING_CATEGORY = CATEGORY_REGISTRY.register( "sewing", RecipeBookCategory::new );
    
    
    // Utility class
    private PNSRecipes() { }
}
