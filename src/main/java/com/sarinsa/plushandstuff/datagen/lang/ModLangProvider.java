package com.sarinsa.plushandstuff.datagen.lang;

import com.sarinsa.plushandstuff.common.core.PlushStuff;
import com.sarinsa.plushandstuff.common.core.registry.PNSBlocks;
import com.sarinsa.plushandstuff.common.core.registry.PNSItems;
import com.sarinsa.plushandstuff.common.util.TranslationUtil;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

import java.util.List;
import java.util.Locale;

public class ModLangProvider extends LanguageProvider {
    
    public static final String LOCALE = "en_us";
    
    private static final List<String> EXCEPTIONS = List.of(
            "of", "and", "the"
    );
    
    
    public ModLangProvider( PackOutput output ) {
        super( output, PlushStuff.MODID, LOCALE );
    }
    
    @Override
    protected void addTranslations() {
        // Items
        PNSItems.REGISTRY.getEntries().forEach( entry -> {
            // Skip block items; they are handled in the block loop below
            if( !(entry.get() instanceof BlockItem) ) {
                addItem( entry, toTitleCase( entry.getId().getPath().replaceAll( "_", " " ) ) );
            }
        } );
        // Blocks
        PNSBlocks.REGISTRY.getEntries().forEach( entry -> {
            addBlock( entry, toTitleCase( entry.getId().getPath().replaceAll( "_", " " ) ) );
        } );
        
        // Container & inventory
        addComponent( TranslationUtil.SEWING_MACHINE_CONTAINER, "Sewing Machine" );
    }
    
    /**
     * Adds a translation String for the specified component.
     *
     * @throws IllegalArgumentException If the contents of the specified component is not
     *                                  an instance of {@link TranslatableContents}.
     */
    private void addComponent( MutableComponent component, String translation ) {
        if( component.getContents() instanceof TranslatableContents contents ) {
            add( contents.getKey(), translation );
        }
        else {
            throw new IllegalArgumentException( "Component must have translatable contents!" );
        }
    }
    
    /**
     * Capitalizes the first letter of each individual word in the specified String,
     * except certain words such as "and", "of" and "the", unless they are the first word.
     *
     * @param string The String to process. Individual words MUST be separated by spaces.
     */
    @SuppressWarnings( "deprecation" )
    private static String toTitleCase( String string ) {
        final StringBuilder builder = new StringBuilder();
        final String[] words = string.split( " " );
        
        for( int i = 0; i < words.length; i++ ) {
            final String word = words[i];
            // Check for exceptions, unless it is the first word
            if( i != 0 && EXCEPTIONS.contains( word ) ) {
                builder.append( word.toLowerCase( Locale.ROOT ) );
            }
            else {
                builder.append( WordUtils.capitalize( word ) );
            }
            // Add space after each word (except the last word)
            if( i != words.length - 1 ) {
                builder.append( " " );
            }
        }
        return builder.toString();
    }
}
