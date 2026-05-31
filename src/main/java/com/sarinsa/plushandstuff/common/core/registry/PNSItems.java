package com.sarinsa.plushandstuff.common.core.registry;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import com.sarinsa.plushandstuff.common.item.SketchbookItem;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class PNSItems {
    
    public static final DeferredRegister.Items REGISTRY = DeferredRegister.Items.createItems( PlushStuff.MODID );
    
    
    public static final DeferredItem<SketchbookItem> SKETCHBOOK = REGISTRY.registerItem( "sketchbook", SketchbookItem::new );
    
    
    static void registerSimpleBlockItem( DeferredBlock<?> block ) {
        REGISTRY.registerSimpleBlockItem( block );
    }
    
    // Utility class
    private PNSItems() { }
}
