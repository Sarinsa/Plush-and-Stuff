package com.sarinsa.plushandstuff.common.sewing;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sarinsa.plushandstuff.common.color_provider.ColorProviderType;
import com.sarinsa.plushandstuff.common.color_provider.DyeColorProvider;
import com.sarinsa.plushandstuff.common.color_provider.IColorProvider;
import com.sarinsa.plushandstuff.common.color_provider.IntColorProvider;
import net.minecraft.core.Holder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

public record SewingMaterial(Holder<Item> item, IColorProvider colorProvider) {
    
    public static final Codec<SewingMaterial> DIRECT_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Item.CODEC.fieldOf( "item" ).forGetter( o -> o.item ),
                            ColorProviderType.DIRECT_CODEC.get().fieldOf( "color_provider" ).forGetter( o -> o.colorProvider )
                    )
                    .apply( instance, SewingMaterial::new )
    );
    
    
    /**
     * Convenience method for creating sewing materials for data gen.
     *
     * @param item     The item of the sewing material.
     * @param dyeColor The {@link DyeColor} to associate with the given item.
     */
    @ApiStatus.Internal
    public static SewingMaterial of( Holder<Item> item, DyeColor dyeColor ) {
        return new SewingMaterial( item, new DyeColorProvider( dyeColor ) );
    }
    
    /**
     * Convenience method for creating sewing materials for data gen.
     *
     * @param item     The item of the sewing material.
     * @param colorInt The color to associate with the given item.
     */
    @ApiStatus.Internal
    public static SewingMaterial of( Holder<Item> item, Integer colorInt ) {
        return new SewingMaterial( item, new IntColorProvider( colorInt ) );
    }
}
