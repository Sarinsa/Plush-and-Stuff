package com.sarinsa.plushandstuff.common.core.registry;

import com.sarinsa.plushandstuff.common.block.entity.SewingMachineBlockEntity;
import com.sarinsa.plushandstuff.common.core.PlushStuff;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;

public class PNSBlockEntities {
    
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create( Registries.BLOCK_ENTITY_TYPE, PlushStuff.MODID );
    
    
    public static final Supplier<BlockEntityType<SewingMachineBlockEntity>> SEWING_MACHINE =
            REGISTRY.register( "sewing_machine", () -> new BlockEntityType<>( SewingMachineBlockEntity::new, Set.of( PNSBlocks.SEWING_MACHINE.get() ) ) );
}
