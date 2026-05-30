package com.sarinsa.plushandstuff.common.core.registry;

import com.mojang.serialization.MapCodec;
import com.sarinsa.plushandstuff.common.color_provider.ColorProvider;
import com.sarinsa.plushandstuff.common.color_provider.ColorProviderType;
import com.sarinsa.plushandstuff.common.color_provider.DyeColorProvider;
import com.sarinsa.plushandstuff.common.color_provider.IntColorProvider;
import com.sarinsa.plushandstuff.common.core.PlushStuff;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class PNSColorProviders {
    
    public static final ResourceKey<Registry<ColorProviderType<?>>> REGISTRY_KEY = ResourceKey.createRegistryKey( PlushStuff.id( "color_providers" ) );
    public static final DeferredRegister<ColorProviderType<?>> REGISTRY = DeferredRegister.create( REGISTRY_KEY, PlushStuff.MODID );
    
    public static Registry<ColorProviderType<?>> BASE_REGISTRY;
    
    
    public static final Supplier<ColorProviderType<DyeColorProvider>> DYE = REGISTRY.register( "dye", of( DyeColorProvider.CODEC ) );
    public static final Supplier<ColorProviderType<IntColorProvider>> INTEGER = REGISTRY.register( "int", of( IntColorProvider.CODEC ) );
    
    
    private static <T extends ColorProvider> Supplier<ColorProviderType<T>> of( MapCodec<T> codec ) {
        return () -> () -> codec;
    }
    
    /** Called when new registries can be created. */
    public static void onNewRegistry( NewRegistryEvent event ) {
        BASE_REGISTRY = event.create( new RegistryBuilder<>( REGISTRY_KEY ) );
    }
}
