package com.sarinsa.plushandstuff.common.core.registry;

import com.sarinsa.plushandstuff.common.block.SewingMachineBlock;
import com.sarinsa.plushandstuff.common.core.PlushStuff;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class PNSBlocks {
    
    public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.Blocks.createBlocks( PlushStuff.MODID );
    
    
    public static final DeferredBlock<SewingMachineBlock> SEWING_MACHINE = registerWithItem( "sewing_machine", SewingMachineBlock::new );
    
    
    private static <T extends Block> DeferredBlock<T> registerWithItem( String name, Function<BlockBehaviour.Properties, ? extends T> func ) {
        DeferredBlock<T> blockRegObj = REGISTRY.registerBlock( name, func );
        PNSItems.registerSimpleBlockItem( blockRegObj );
        return blockRegObj;
    }
}
