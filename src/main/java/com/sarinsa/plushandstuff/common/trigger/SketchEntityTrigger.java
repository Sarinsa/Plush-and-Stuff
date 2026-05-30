package com.sarinsa.plushandstuff.common.trigger;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sarinsa.plushandstuff.common.core.registry.PNSTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;

import java.util.Optional;

public class SketchEntityTrigger extends SimpleCriterionTrigger<SketchEntityTrigger.TriggerInstance> {
    
    @Override
    public Codec<SketchEntityTrigger.TriggerInstance> codec() {
        return SketchEntityTrigger.TriggerInstance.CODEC;
    }
    
    public void trigger( ServerPlayer player, EntityType<?> entityType ) {
        trigger( player, ( instance ) -> instance.matches( player, entityType ) );
    }
    
    public record TriggerInstance(Optional<ContextAwarePredicate> player,
                                  EntityType<?> entityType) implements SimpleCriterionTrigger.SimpleInstance {
        
        public static final Codec<SketchEntityTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                inst -> inst.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf( "player" ).forGetter( SketchEntityTrigger.TriggerInstance::player ),
                                EntityType.CODEC.fieldOf( "type" ).forGetter( SketchEntityTrigger.TriggerInstance::entityType )
                        )
                        .apply( inst, SketchEntityTrigger.TriggerInstance::new )
        );
        
        public static Criterion<SketchEntityTrigger.TriggerInstance> forEntity( EntityType<?> entityType ) {
            ContextAwarePredicate predicate = ContextAwarePredicate.create();
            return PNSTriggers.SKETCH_ENTITY.get().createCriterion(
                    new SketchEntityTrigger.TriggerInstance( Optional.of( predicate ), entityType ) );
        }
        
        public boolean matches( ServerPlayer serverPlayer, EntityType<?> entityType ) {
            return this.entityType == entityType;
        }
    }
}
