package com.sarinsa.plushandstuff.common.core;

import com.mojang.logging.LogUtils;
import com.sarinsa.plushandstuff.common.core.registry.*;
import com.sarinsa.plushandstuff.common.network.PacketHandler;
import com.sarinsa.plushandstuff.common.plushie.PlushieType;
import com.sarinsa.plushandstuff.common.sewing.SewingMaterial;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import org.slf4j.Logger;

@Mod( PlushStuff.MODID )
public final class PlushStuff {
    
    public static final String MODID = "plushandstuff";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    
    public PlushStuff( IEventBus modEventBus, ModContainer modContainer ) {
        modEventBus.addListener( PNSColorProviders::onNewRegistry );
        modEventBus.addListener( this::onCommonSetup );
        modEventBus.addListener( this::onNewDatapackRegistry );
        modEventBus.register( PacketHandler.class );
        
        PNSItems.REGISTRY.register( modEventBus );
        PNSTriggers.REGISTRY.register( modEventBus );
        PNSColorProviders.REGISTRY.register( modEventBus );
        PNSRecipes.register( modEventBus );
    }
    
    /** Called during common setup. */
    private void onCommonSetup( FMLCommonSetupEvent event ) { }
    
    /** Called when new data-registries can be created. */
    private void onNewDatapackRegistry( DataPackRegistryEvent.NewRegistry event ) {
        event.dataPackRegistry( PNSSewingMaterials.REGISTRY_KEY, SewingMaterial.DIRECT_CODEC );
        event.dataPackRegistry( PNSPlushieTypes.REGISTRY_KEY, PlushieType.DIRECT_CODEC );
    }
    
    
    /** @return An identifier with this mod's namespace and the specified path. */
    public static Identifier id( String path ) {
        return Identifier.fromNamespaceAndPath( MODID, path );
    }
}
