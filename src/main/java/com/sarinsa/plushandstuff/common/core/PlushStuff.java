package com.sarinsa.plushandstuff.common.core;

import com.mojang.logging.LogUtils;
import com.sarinsa.plushandstuff.common.core.registry.PNSColorProviders;
import com.sarinsa.plushandstuff.common.core.registry.PNSItems;
import com.sarinsa.plushandstuff.common.core.registry.PNSRecipes;
import com.sarinsa.plushandstuff.common.core.registry.PNSSewingMaterials;
import com.sarinsa.plushandstuff.common.network.PacketHandler;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod( PlushStuff.MODID )
public final class PlushStuff {
    
    public static final String MODID = "plushandstuff";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    
    public PlushStuff( IEventBus modEventBus, ModContainer modContainer ) {
        modEventBus.register( PacketHandler.class );
        modEventBus.addListener( PNSSewingMaterials::onNewRegistry );
        modEventBus.addListener( PNSColorProviders::onNewRegistry );
        modEventBus.addListener( this::onCommonSetup );
        
        PNSItems.REGISTRY.register( modEventBus );
        PNSColorProviders.REGISTRY.register( modEventBus );
        PNSRecipes.register( modEventBus );
    }
    
    /** Called during common setup. */
    private void onCommonSetup( FMLCommonSetupEvent event ) { }
    
    
    /** @return An identifier with this mod's namespace and the specified path. */
    public static Identifier id( String path ) {
        return Identifier.fromNamespaceAndPath( MODID, path );
    }
}
