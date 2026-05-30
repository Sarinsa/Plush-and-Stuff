package com.sarinsa.plushandstuff.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public final class ClientUtil {
    
    /**
     * @param range The length of the ray cast.
     * @return The entity the player is currently looking at.
     */
    @Nullable
    public static Entity getLookAtEntity( double range ) {
        final Minecraft mc = Minecraft.getInstance();
        
        if( mc.player == null )
            return null;
        
        final Entity cameraEntity = Minecraft.getInstance().getCameraEntity();
        
        if( cameraEntity == null )
            return null;
        
        final HitResult hitResult = LocalPlayer.pick( cameraEntity, 0.0, range, 1.0F );
        
        return hitResult instanceof EntityHitResult entityHitResult ? entityHitResult.getEntity() : null;
    }
    
    
    // Utility class
    private ClientUtil() { }
}
