package com.sarinsa.plushandstuff.common.block.entity;

import com.sarinsa.plushandstuff.common.core.registry.PNSBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SewingMachineBlockEntity extends BlockEntity {
    
    public SewingMachineBlockEntity( BlockPos pos, BlockState state ) {
        super( PNSBlockEntities.SEWING_MACHINE.get(), pos, state );
    }
}
