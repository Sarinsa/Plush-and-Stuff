package com.sarinsa.plushandstuff.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class SketchbookItem extends Item {
    
    public SketchbookItem( Properties properties ) {
        super( properties
                .stacksTo( 1 )
        );
    }
    
    /**
     * Called when a player right-clicks while holding this item.
     */
    @Override
    public InteractionResult use( Level level, Player player, InteractionHand hand ) {
        if( level.isClientSide() ) {
        
        }
        return super.use( level, player, hand );
    }
}
