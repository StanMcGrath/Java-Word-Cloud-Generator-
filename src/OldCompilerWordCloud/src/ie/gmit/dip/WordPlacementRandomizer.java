package ie.gmit.dip;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 
 * @author Stan McGrath
 * @version 1.0
 * @since 1.8
 *
 * The class WordPlacementRandomizer randomizes the point on the X and Y axis 
 * at which words will be drawn using methods randomizePointX and randomizePointY.
 *
 */
public class WordPlacementRandomizer {
	
	private static final Random RANDOM = new Random();
	
	/** The method randomizePointX randomizes the point on the X axis somewhere
	 * around the center of the image, at which the word will be drawn
	 * into the word cloud. It works by finding the center point of the image
	 * and offsetting the point on the X axis by a value determined by a random number.
	 * 
	 * @param image - the BufferedImage to draw.
	 * @return x - the randomized position on the X axis.
	 */
	public int randomizePointX(BufferedImage image) {
		int random = RANDOM.nextInt(15);
		int x = (int) (image.getHeight() / 1.2);
		
		if (random == 0) {
			x = x + 20;
		} else if (random == 1) {
			x = x - 20;
		} else if (random == 2) {
			x = x + 50;
		} else if (random == 3) {
			x = x - 50;
		} else if (random == 4) {
			x = x + 80;
		} else if (random == 5) {
			x = x - 80;
		} else if (random == 6) {
			x = x + 120;
		} else if (random == 7) {
			x = x - 120;
		} else if (random == 8) {
			x = x + 150;
		} else if (random == 9) {
			x = x - 150;
		} else if (random == 10) {
			x = x + 180;
		} else if (random == 11) {
			x = x - 180;
		} else if (random == 12) {
			x = x + 210;
		} else if (random == 13) {
			x = x - 210;
		} else if (random == 14) {
			x = x + 240;
		} 
		
		return x;
	}
	/** The method randomizePointY randomizes the point on the Y axis somewhere
	 * around the center of the image, at which the word will be drawn
	 * into the word cloud. It works by finding the center point of the image
	 * and offsetting the point on the Y axis by a value determined by a random number.
	 * 
	 * @param image - the BufferedImage to draw
	 * @return y - the randomized position on the Y axis.
	 */
	public int randomizePointY(BufferedImage image) {
		int random = RANDOM.nextInt(15);
		int y = image.getWidth() / 4;
		
		if (random == 0) {
			y = y + 20;
		} else if (random == 1) {
			y = y - 20;
		} else if (random == 2) {
			y = y + 50;
		} else if (random == 3) {
			y = y - 50;
		} else if (random == 4) {
			y = y + 80;
		} else if (random == 5) {
			y = y - 80;
		} else if (random == 6) {
			y = y + 120;
		} else if (random == 7) {
			y = y - 120;
		} else if (random == 8) {
			y = y + 150;
		} else if (random == 9) {
			y = y - 150;
		} else if (random == 10) {
			y = y + 180;
		} else if (random == 11) {
			y = y - 180;
		} else if (random == 12) {
			y = y + 210;
		} else if (random == 13) {
			y = y - 210;
		} else if (random == 14) {
			y = y + 240;
		} 
		
		return y;
	}

}
