package com.sarinsa.plushandstuff.common.core.registry;

import com.sarinsa.plushandstuff.common.block.SewingMachineBlock;
import com.sarinsa.plushandstuff.common.core.PlushStuff;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PNSBlocks {
    
    public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.Blocks.createBlocks( PlushStuff.MODID );
    
    
    public static final DeferredBlock<SewingMachineBlock> SEWING_MACHINE =
            REGISTRY.registerBlock( "sewing_machine", SewingMachineBlock::new );
}
