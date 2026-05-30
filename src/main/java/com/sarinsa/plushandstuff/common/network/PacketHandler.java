package com.sarinsa.plushandstuff.common.network;

import com.sarinsa.plushandstuff.common.network.message.SketchedEntityMessage;
import com.sarinsa.plushandstuff.common.network.work.CommonWork;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class PacketHandler {
    
    /** The current network version. */
    private static final String VERSION = "1";
    
    
    @SubscribeEvent
    public static void register( RegisterPayloadHandlersEvent event ) {
        final PayloadRegistrar registrar = event.registrar( VERSION );
        
        registrar.playToServer( SketchedEntityMessage.TYPE, SketchedEntityMessage.STREAM_CODEC, CommonWork::handleSketchedEntityMessage );
    }
}
