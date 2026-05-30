package com.sarinsa.plushandstuff.common.core.registry;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import com.sarinsa.plushandstuff.common.plushie.PlushieType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;

import static com.sarinsa.plushandstuff.common.core.registry.PNSRecipes.RecipeKeys;

public class PNSPlushieTypes {
    
    public static final ResourceKey<Registry<PlushieType>> REGISTRY_KEY = ResourceKey.createRegistryKey( PlushStuff.id( "plushie_type" ) );
    
    
    /** Called during data gen to generate default registry entries. */
    public static void bootstrap( BootstrapContext<PlushieType> ctx ) {
        plushieType( ctx, EntityType.ZOMBIE, RecipeKeys.Sewing.ZOMBIE );
        plushieType( ctx, EntityType.CREEPER, RecipeKeys.Sewing.CREEPER );
    }
    
    /** Convenience method for generating a sewing material entry. */
    @SafeVarargs
    private static void plushieType( BootstrapContext<PlushieType> ctx, EntityType<? extends LivingEntity> entityType,
                                     ResourceKey<Recipe<?>>... recipeKeys ) {
        final String entityName = BuiltInRegistries.ENTITY_TYPE.getKey( entityType ).getPath();
        final ResourceKey<PlushieType> key = ResourceKey.create( PNSPlushieTypes.REGISTRY_KEY, PlushStuff.id( entityName ) );
        ctx.register( key, new PlushieType( entityType, List.of( recipeKeys ) ) );
    }
}
