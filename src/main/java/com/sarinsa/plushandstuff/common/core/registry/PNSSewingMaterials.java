package com.sarinsa.plushandstuff.common.core.registry;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import com.sarinsa.plushandstuff.common.sewing.SewingMaterial;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

public class PNSSewingMaterials {
    
    public static final ResourceKey<Registry<SewingMaterial>> REGISTRY_KEY = ResourceKey.createRegistryKey( PlushStuff.id( "sewing_material" ) );
    
    
    /** Called during data gen to generate default registry entries. */
    public static void bootstrap( BootstrapContext<SewingMaterial> ctx ) {
        final HolderLookup<Item> itemLookup = ctx.holderLookup( Registries.ITEM ).orElseThrow();
        
        // Expect each dye to have a wool block with the same partial name
        for( DyeColor color : DyeColor.values() ) {
            final String woolName = color.getName() + "_wool";
            final Holder.Reference<Item> reference = itemLookup.getOrThrow(
                    ResourceKey.create( Registries.ITEM, Identifier.withDefaultNamespace( woolName ) )
            );
            
            materialFromWool( ctx, SewingMaterial.of( reference, color ) );
        }
    }
    
    /** Convenience method for generating a sewing material entry. */
    private static void materialFromWool( BootstrapContext<SewingMaterial> ctx, SewingMaterial material ) {
        final String itemName = BuiltInRegistries.ITEM.getKey( material.item().value() ).getPath();
        final ResourceKey<SewingMaterial> key = ResourceKey.create( REGISTRY_KEY, PlushStuff.id( itemName ) );
        ctx.register( key, material );
    }
}
