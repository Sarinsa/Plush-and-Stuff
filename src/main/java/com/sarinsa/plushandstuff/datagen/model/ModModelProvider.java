package com.sarinsa.plushandstuff.datagen.model;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import com.sarinsa.plushandstuff.common.core.registry.PNSBlocks;
import com.sarinsa.plushandstuff.common.core.registry.PNSItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {
    
    public ModModelProvider( PackOutput output ) {
        super( output, PlushStuff.MODID );
    }
    
    @Override
    protected void registerModels( BlockModelGenerators blockModels, ItemModelGenerators itemModels ) {
        blockModels.createTrivialCube( PNSBlocks.SEWING_MACHINE.get() );
        
        itemModels.createFlatItemModel( PNSItems.SKETCHBOOK.get(), ModelTemplates.FLAT_ITEM );
    }
    
    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return Stream.empty();
    }
    
    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return Stream.empty();
    }
}
