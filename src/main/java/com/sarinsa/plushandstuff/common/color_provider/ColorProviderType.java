package com.sarinsa.plushandstuff.common.color_provider;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.sarinsa.plushandstuff.common.core.registry.PNSColorProviders;

import java.util.function.Supplier;

public interface ColorProviderType<T extends ColorProvider> {
    
    Supplier<Codec<ColorProvider>> DIRECT_CODEC = () -> PNSColorProviders.BASE_REGISTRY
            .byNameCodec()
            .dispatch( "color_provider_type", ColorProvider::getType, ColorProviderType::codec );
    
    /** @return This color provider type's codec. */
    MapCodec<T> codec();
}
