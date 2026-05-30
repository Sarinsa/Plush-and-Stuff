package com.sarinsa.plushandstuff.common.color_provider;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sarinsa.plushandstuff.common.core.registry.PNSColorProviders;
import net.minecraft.world.item.DyeColor;

public record DyeColorProvider(DyeColor dye) implements ColorProvider {
    
    public static final MapCodec<DyeColorProvider> CODEC = RecordCodecBuilder.mapCodec( instance ->
            instance.group(
                    DyeColor.CODEC.fieldOf( "dye" ).forGetter( o -> o.dye )
            ).apply( instance, DyeColorProvider::new ) );
    
    @Override
    public int getColor() {
        return dye.getTextureDiffuseColor();
    }
    
    @Override
    public ColorProviderType<?> getType() {
        return PNSColorProviders.DYE.get();
    }
}
