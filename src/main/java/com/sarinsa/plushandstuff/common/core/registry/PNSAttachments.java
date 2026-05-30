package com.sarinsa.plushandstuff.common.core.registry;

import com.sarinsa.plushandstuff.common.attachment.player.KnownPlushiesHandler;
import com.sarinsa.plushandstuff.common.core.PlushStuff;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public final class PNSAttachments {
    
    public static final DeferredRegister<AttachmentType<?>> REGISTRY = DeferredRegister.create( NeoForgeRegistries.ATTACHMENT_TYPES, PlushStuff.MODID );
    
    public static final Supplier<AttachmentType<KnownPlushiesHandler>> KNOWN_PLUSHIES = REGISTRY.register(
            "known_plushies", () -> AttachmentType.builder( () -> new KnownPlushiesHandler() )
                    .serialize( KnownPlushiesHandler.DIRECT_CODEC.fieldOf( "known_plushies" ) )
                    .sync( KnownPlushiesHandler.STREAM_CODEC )
                    .build()
    );
    
    // Utility class
    private PNSAttachments() { }
}
