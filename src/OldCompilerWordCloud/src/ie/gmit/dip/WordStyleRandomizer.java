package ie.gmit.dip;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Locale;
import java.util.Random;

/**
 * 
 * @author Stan McGrath
 * @version 1.0
 * @since 1.8
 * 
 * The class WordStyleRandomizer randomizes the style (font, style colour) of the words to be drawn
 * using methods randomizeFont(), randomizeStyle() and randomizeColor().
 *
 */
public class WordStyleRandomizer {
	
	private static final Random RANDOM = new Random();
	
	/** 
	 * The method randomizeFont randomizes the Font used to draw words in the Word Cloud.
	 * 
	 * @return the randomized font as type string.
	 */
	
	public String randomizeFont() {
		
		String[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(Locale.ENGLISH);
		return allFonts[RANDOM.nextInt(allFonts.length /2)];
		
	}
	
	/** The method randomizeStyle randomizes the Style constant used to draw words in the Word Cloud.
	 * 
	 * @return the randomized Style as an int number.
	 */
	
	public int randomizeStyle() {
		int random = RANDOM.nextInt(3);
		int font = 0;
		
		if (random == 0) {
			font = Font.BOLD;
		} else if (random == 1) {
			font = Font.ITALIC;
		} else if (random == 2) {
			font = Font.PLAIN;
		}
		return font;
		}
	
	/** 
	 * This method randomizeColor randomizes the Colour used to draw words in the Word Cloud.
	 * 
	 * @return randomized Color of type Color.
	 */
	
	public Color randomizeColor() {
		int random = RANDOM.nextInt(9);
		Color color = Color.black;
		
		if(random == 0) {
			 color = Color.black;
		} else if (random == 1) {
			 color = Color.blue;
		} else if (random == 2) {
			 color = Color.green;
		} else if (random == 3) {
			 color = Color.gray;
		} else if (random == 4) {
			 color = Color.orange;
		} else if (random == 5) {
			 color = Color.yellow;
		} else if (random == 6) {
			 color = Color.pink;
		} else if (random == 7) {
			color = Color.DARK_GRAY;
		} else if (random == 8) {
			 color = Color.cyan;
		}
		 
		return color;
		
	}

}
