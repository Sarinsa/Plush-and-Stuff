package com.sarinsa.plushandstuff.common.color_provider;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sarinsa.plushandstuff.common.core.registry.PNSColorProviders;
import net.minecraft.world.item.DyeColor;

/** A color provider implementation that returns a color from a {@link DyeColor} enum constant. */
public record DyeColorProvider(DyeColor dye) implements IColorProvider {
    
    public static final MapCodec<DyeColorProvider> CODEC = RecordCodecBuilder.mapCodec( instance ->
            instance.group(
                    DyeColor.CODEC.fieldOf( "dye" ).forGetter( o -> o.dye )
            ).apply( instance, DyeColorProvider::new ) );
    
    
    /** @return This provider's color integer. */
    @Override
    public int getColor() {
        return dye.getTextureDiffuseColor();
    }
    
    /** @return This color provider's type. */
    @Override
    public ColorProviderType<?> getType() {
        return PNSColorProviders.DYE.get();
    }
}
