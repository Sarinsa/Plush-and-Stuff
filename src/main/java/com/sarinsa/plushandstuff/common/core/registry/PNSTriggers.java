package com.sarinsa.plushandstuff.common.core.registry;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import com.sarinsa.plushandstuff.common.trigger.SketchEntityTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PNSTriggers {
    
    public static final DeferredRegister<CriterionTrigger<?>> REGISTRY = DeferredRegister.create( BuiltInRegistries.TRIGGER_TYPES, PlushStuff.MODID );
    
    public static final DeferredHolder<CriterionTrigger<?>, SketchEntityTrigger> SKETCH_ENTITY = REGISTRY.register( "sketch_entity", SketchEntityTrigger::new );
}
