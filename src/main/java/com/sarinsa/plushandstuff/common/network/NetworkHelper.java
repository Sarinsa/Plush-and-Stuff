package com.sarinsa.plushandstuff.common.network;

import com.sarinsa.plushandstuff.common.network.message.SketchedEntityMessage;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

/** Helper class for sending packets. */
public class NetworkHelper {
    
    /**
     * Sends a sketch entity update message to the server.
     *
     * @param sketchingPlayer The player to send an update for.
     * @param observedEntity  The entity that was "jotted down in the sketchbook".
     */
    public static void sendSketchEntityUpdate( Player sketchingPlayer, EntityType<?> observedEntity ) {
        ClientPacketDistributor.sendToServer( new SketchedEntityMessage( sketchingPlayer.getUUID(), observedEntity ) );
    }
}
