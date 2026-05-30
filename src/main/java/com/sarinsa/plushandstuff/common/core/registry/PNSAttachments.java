package com.sarinsa.plushandstuff.common.core.registry;

import com.sarinsa.plushandstuff.common.attachment.player.KnownPlushiesHandler;
import com.sarinsa.plushandstuff.common.core.PlushStuff;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public final class PNSAttachments {
    
    public static final DeferredRegister<AttachmentType<?>> REGISTRY = DeferredRegister.create( NeoForgeRegistries.ATTACHMENT_TYPES, PlushStuff.MODID );
    
    
    private static final Supplier<AttachmentType<KnownPlushiesHandler>> KNOWN_PLUSHIES = REGISTRY.register(
            "known_plushies", () -> AttachmentType.serializable( KnownPlushiesHandler::new )
                    .copyOnDeath()
                    .sync( new KnownPlushiesHandler.SyncHandler() )
                    .build() );
    
    
    // Utility class
    private PNSAttachments() { }
}
