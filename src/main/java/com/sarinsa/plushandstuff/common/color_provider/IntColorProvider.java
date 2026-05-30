package com.sarinsa.plushandstuff.common.color_provider;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sarinsa.plushandstuff.common.core.registry.PNSColorProviders;

public record IntColorProvider(int color) implements ColorProvider {
    
    public static final MapCodec<IntColorProvider> CODEC = RecordCodecBuilder.mapCodec( instance ->
            instance.group(
                    Codec.INT.fieldOf( "color" ).forGetter( o -> o.color )
            ).apply( instance, IntColorProvider::new ) );
    
    @Override
    public int getColor() {
        return color;
    }
    
    @Override
    public ColorProviderType<?> getType() {
        return PNSColorProviders.INTEGER.get();
    }
}
