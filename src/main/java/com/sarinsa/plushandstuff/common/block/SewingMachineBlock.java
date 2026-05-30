package com.sarinsa.plushandstuff.common.block;

import com.sarinsa.plushandstuff.common.block.entity.SewingMachineBlockEntity;
import com.sarinsa.plushandstuff.common.util.TranslationUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class SewingMachineBlock extends Block implements EntityBlock {
    
    public SewingMachineBlock( Properties properties ) {
        super( properties );
    }
    
    @Override
    @Nullable
    public BlockEntity newBlockEntity( BlockPos pos, BlockState state ) {
        return new SewingMachineBlockEntity( pos, state );
    }
    
    @Override
    protected InteractionResult useWithoutItem( BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult ) {
        if( !level.isClientSide() ) {
            player.openMenu( state.getMenuProvider( level, pos ) );
            player.awardStat( Stats.INTERACT_WITH_CRAFTING_TABLE );
        }
        return InteractionResult.SUCCESS;
    }
    
    @Override
    protected MenuProvider getMenuProvider( BlockState state, Level level, BlockPos pos ) {
        return new SimpleMenuProvider( ( containerId, inventory, _ ) ->
                new CraftingMenu( containerId, inventory, ContainerLevelAccess.create( level, pos ) ), TranslationUtil.SEWING_MACHINE_CONTAINER
        );
    }
}
