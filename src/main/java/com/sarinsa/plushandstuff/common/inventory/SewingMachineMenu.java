package com.sarinsa.plushandstuff.common.inventory;

import com.sarinsa.plushandstuff.common.core.registry.PNSMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;

public class SewingMachineMenu extends AbstractContainerMenu {
    
    
    public SewingMachineMenu( int containerId, Inventory inventory ) {
        this( containerId, inventory, new SimpleContainer( 3 ), new SimpleContainerData( 4 ) );
    }
    
    
    public SewingMachineMenu( int containerId, Inventory inventory, Container sewingMachine, ContainerData sewingMachineData ) {
        super( PNSMenuTypes.SEWING_MACHINE.get(), containerId );
        
    }
    
    @Override
    public ItemStack quickMoveStack( Player player, int slotIndex ) {
        return null;
    }
    
    @Override
    public boolean stillValid( Player player ) {
        return false;
    }
}
