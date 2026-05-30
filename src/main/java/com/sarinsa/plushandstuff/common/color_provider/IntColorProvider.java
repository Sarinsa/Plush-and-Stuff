package com.sarinsa.plushandstuff.common.color_provider;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sarinsa.plushandstuff.common.core.registry.PNSColorProviders;

/** A color provider implementation that returns a color from an integer. */
public record IntColorProvider(int color) implements IColorProvider {
    
    public static final MapCodec<IntColorProvider> CODEC = RecordCodecBuilder.mapCodec( instance ->
            instance.group(
                    Codec.INT.fieldOf( "color" ).forGetter( o -> o.color )
            ).apply( instance, IntColorProvider::new ) );
    
    
    /** @return This provider's color integer. */
    @Override
    public int getColor() {
        // Clamp as color
        return Math.clamp( color, 0x00000000, 0xFFFFFFFF );
    }
    
    /** @return This color provider's type. */
    @Override
    public ColorProviderType<?> getType() {
        return PNSColorProviders.INTEGER.get();
    }
}
