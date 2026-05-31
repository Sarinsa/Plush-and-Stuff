package com.sarinsa.plushandstuff.client;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import com.sarinsa.plushandstuff.common.core.registry.PNSPlushieTypes;
import com.sarinsa.plushandstuff.common.core.registry.PNSSewingMaterials;
import com.sarinsa.plushandstuff.datagen.lang.ModLangProvider;
import com.sarinsa.plushandstuff.datagen.model.ModModelProvider;
import com.sarinsa.plushandstuff.datagen.recipe.ModRecipeProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;

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
        // Generating both client and server data in this one call
        
        final PackOutput packOutput = event.getGenerator().getPackOutput();
        
        final RegistrySetBuilder regBuilder = new RegistrySetBuilder()
                .add( PNSSewingMaterials.REGISTRY_KEY, PNSSewingMaterials::bootstrap )
                .add( PNSPlushieTypes.REGISTRY_KEY, PNSPlushieTypes::bootstrap );
        
        event.addProvider( new DatapackBuiltinEntriesProvider( packOutput, event.getLookupProvider(), regBuilder, Set.of( PlushStuff.MODID ) ) );
        event.addProvider( new ModModelProvider( packOutput ) );
        event.addProvider( new ModLangProvider( packOutput ) );
        event.createProvider( ModRecipeProvider.Runner::new );
    }
}
