package com.sarinsa.plushandstuff.common.attachment.player;

import com.sarinsa.plushandstuff.common.recipe.SewingRecipe;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import org.jspecify.annotations.Nullable;

import java.util.Map;

public class KnownPlushiesHandler implements ValueIOSerializable {
    
    private Map<EntityType<?>, SewingRecipe> knownPlushRecipes;
    
    
    @Override
    public void serialize( ValueOutput output ) {
    
    }
    
    @Override
    public void deserialize( ValueInput input ) {
    
    }
    
    public static class SyncHandler implements AttachmentSyncHandler<KnownPlushiesHandler> {
        
        @Override
        public boolean sendToPlayer( IAttachmentHolder holder, ServerPlayer to ) {
            return AttachmentSyncHandler.super.sendToPlayer( holder, to );
        }
        
        @Override
        public void write( RegistryFriendlyByteBuf buf, KnownPlushiesHandler attachment, boolean initialSync ) {
        
        }
        
        @Override
        @Nullable
        public KnownPlushiesHandler read( IAttachmentHolder holder, RegistryFriendlyByteBuf buf, @Nullable KnownPlushiesHandler previousValue ) {
            return null;
        }
    }
}
