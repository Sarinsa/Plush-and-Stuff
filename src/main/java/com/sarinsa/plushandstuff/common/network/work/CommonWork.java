package com.sarinsa.plushandstuff.common.network.work;

import com.sarinsa.plushandstuff.common.attachment.player.KnownPlushiesHandler;
import com.sarinsa.plushandstuff.common.core.registry.PNSAttachments;
import com.sarinsa.plushandstuff.common.core.registry.PNSTriggers;
import com.sarinsa.plushandstuff.common.network.message.SketchedEntityMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

public class CommonWork {
    
    @SuppressWarnings( "unused" )
    public static void handleSketchedEntityMessage( SketchedEntityMessage message, IPayloadContext context ) {
        final MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if( server == null ) return;
        
        final ServerPlayer player = server.getPlayerList().getPlayer( message.playerUUID() );
        if( player == null ) return;
        
        final KnownPlushiesHandler handler = player.getData( PNSAttachments.KNOWN_PLUSHIES );
        
        if( handler.maybeAddKnownEntity( player.level(), message.observedEntity() ) ) {
            player.syncData( PNSAttachments.KNOWN_PLUSHIES );
            // TODO Custom sound event
            // noinspection resource
            player.level().playSound( null, player, SoundEvents.BOOK_PAGE_TURN, SoundSource.PLAYERS, 1.0F, 1.0F );
            PNSTriggers.SKETCH_ENTITY.get().trigger( player, message.observedEntity() );
        }
    }
}
