package com.sarinsa.plushandstuff.common.core.registry;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import com.sarinsa.plushandstuff.common.inventory.SewingMachineMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class PNSMenuTypes {
    
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create( Registries.MENU, PlushStuff.MODID );
    
    
    public static final Supplier<MenuType<SewingMachineMenu>> SEWING_MACHINE =
            REGISTRY.register( "sewing_machine", () -> new MenuType<>( SewingMachineMenu::new, FeatureFlags.VANILLA_SET ) );
}
