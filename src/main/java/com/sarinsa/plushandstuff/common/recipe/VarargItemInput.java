package com.sarinsa.plushandstuff.common.recipe;

import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.ItemLike;

import java.util.Iterator;
import java.util.List;

/**
 * A recipe input that can hold a variable
 * amount of ingredients, but not a completely empty list.
 */
public class VarargItemInput implements RecipeInput, Iterable<ItemStack> {
    
    /** @return A new instance with the given list of item stack ingredients. */
    public static VarargItemInput ofStacks( List<ItemStack> input ) {
        return new VarargItemInput( input );
    }
    
    /** @return A new instance with the given list of item-like (blocks and/or items) ingredients. */
    public static VarargItemInput of( List<ItemLike> input ) {
        List<ItemStack> list = input.stream().map( ItemStack::new ).toList();
        return new VarargItemInput( list );
    }
    
    /** The ingredients in this input. */
    private final List<ItemStack> input;
    /** This input's stacked contents. Populated in the constructor. */
    private final StackedItemContents stackedContents = new StackedItemContents();
    
    
    // For internal usage, use the static creation methods above instead.
    private VarargItemInput( List<ItemStack> input ) {
        this.input = input;
        if( input.isEmpty() ) throw new IllegalArgumentException( "Input cannot be empty!" );
        
        for( ItemStack item : input ) {
            if( !item.isEmpty() ) {
                stackedContents.accountStack( item, 1 );
            }
        }
    }
    
    /**
     * Fetches the item stack at the given index from this input's list of item stacks.
     *
     * @return The item stack at the given index.
     * @throws IndexOutOfBoundsException if {@code index} is not within bounds.
     */
    @Override
    public ItemStack getItem( int index ) {
        if( index < 0 || index >= (input.size() - 1) )
            throw new IndexOutOfBoundsException( "No ingredient at index " + index + " for vararg input with size " + input.size() + "!" );
        return input.get( index );
    }
    
    /** @return The stacked item content instance for this input. */
    public StackedItemContents stackedContents() {
        return stackedContents;
    }
    
    /** @return An iterator for this input's item stacks. */
    @Override
    public Iterator<ItemStack> iterator() {
        return input.iterator();
    }
    
    /** @return The amount of item stacks in this input. */
    @Override
    public int size() {
        return input.size();
    }
}
