package com.sarinsa.plushandstuff.common.color_provider;

/**
 * Represents something that can return a color integer.
 */
public interface IColorProvider {
    
    /** @return This provider's color integer. */
    int getColor();
    
    /** @return This color provider's type. */
    ColorProviderType<?> getType();
}
