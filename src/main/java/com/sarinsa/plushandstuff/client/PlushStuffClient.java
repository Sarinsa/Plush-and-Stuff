package com.sarinsa.plushandstuff.client;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import com.sarinsa.plushandstuff.datagen.recipe.ModRecipeProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod( value = PlushStuff.MODID, dist = Dist.CLIENT )
public class PlushStuffClient {
    
    public PlushStuffClient( IEventBus modEventBus, ModContainer container ) {
        //container.registerExtensionPoint( IConfigScreenFactory.class, ConfigurationScreen::new );
        
        modEventBus.register( PlushStuffClient.class );
    }
    
    @SubscribeEvent
    static void onClientSetup( FMLClientSetupEvent event ) {
    
    }
    
    @SubscribeEvent
    public static void onGatherServerData( GatherDataEvent.Client event ) {
        final PackOutput packOutput = event.getGenerator().getPackOutput();
        
        // Generating both client and server data in this one call
        
        event.createProvider( ModRecipeProvider.Runner::new );
    }
}
