package com.sarinsa.plushandstuff.datagen.model;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import com.sarinsa.plushandstuff.common.core.registry.PNSBlocks;
import com.sarinsa.plushandstuff.common.core.registry.PNSItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {
    
    public ModModelProvider( PackOutput output ) {
        super( output, PlushStuff.MODID );
    }
    
    @Override
    protected void registerModels( BlockModelGenerators blockModels, ItemModelGenerators itemModels ) {
        simpleBlockWithItem( blockModels, PNSBlocks.SEWING_MACHINE );
        
        itemModels.generateFlatItem( PNSItems.SKETCHBOOK.get(), ModelTemplates.FLAT_ITEM );
    }
    
    private void simpleBlockWithItem( BlockModelGenerators blockModels, DeferredBlock<? extends Block> block ) {
        blockModels.createTrivialCube( PNSBlocks.SEWING_MACHINE.get() );
        blockModels.registerSimpleItemModel( block.get(), ModelLocationUtils.getModelLocation( block.get() ) );
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
