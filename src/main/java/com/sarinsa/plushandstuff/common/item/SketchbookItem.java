package com.sarinsa.plushandstuff.common.item;

import com.sarinsa.plushandstuff.client.ClientUtil;
import com.sarinsa.plushandstuff.common.attachment.player.KnownPlushiesHandler;
import com.sarinsa.plushandstuff.common.core.registry.PNSAttachments;
import com.sarinsa.plushandstuff.common.network.NetworkHelper;
import com.sarinsa.plushandstuff.common.plushie.PlushieType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLLoader;

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
        if( FMLLoader.getCurrent().getDist() == Dist.CLIENT ) {
            // TODO configurable range
            final Entity observedEntity = ClientUtil.getLookAtEntity( 10.0 );
            
            if( observedEntity instanceof LivingEntity ) {
                final EntityType<?> entityType = observedEntity.getType();
                final KnownPlushiesHandler handler = player.getData( PNSAttachments.KNOWN_PLUSHIES );
                
                if( PlushieType.isPlushieEntity( level.registryAccess(), entityType ) && !handler.knowsEntity( entityType ) ) {
                    NetworkHelper.sendSketchEntityUpdate( player, entityType );
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.PASS;
        }
        return InteractionResult.CONSUME;
    }
}
