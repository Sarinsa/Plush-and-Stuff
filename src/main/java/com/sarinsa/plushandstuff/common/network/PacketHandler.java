package com.sarinsa.plushandstuff.common.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class PacketHandler {
    
    /** The current network version. */
    private static final String VERSION = "1";
    
    
    @SubscribeEvent
    public static void register( RegisterPayloadHandlersEvent event ) {
        // final PayloadRegistrar registrar = event.registrar( VERSION );
    }
}
