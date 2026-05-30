package com.sarinsa.plushandstuff.common.network.message;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.EntityType;

import java.util.UUID;

public record SketchedEntityMessage(UUID playerUUID, EntityType<?> observedEntity) implements CustomPacketPayload {
    
    public static final CustomPacketPayload.Type<SketchedEntityMessage> TYPE =
            new CustomPacketPayload.Type<>( PlushStuff.id( "sketched_entity_update" ) );
    
    
    public static final StreamCodec<RegistryFriendlyByteBuf, SketchedEntityMessage> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC,
            SketchedEntityMessage::playerUUID,
            EntityType.STREAM_CODEC,
            SketchedEntityMessage::observedEntity,
            SketchedEntityMessage::new
    );
    
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
