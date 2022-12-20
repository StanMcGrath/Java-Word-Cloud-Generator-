package ie.gmit.dip;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.List;
import java.util.Map.Entry;

/**
 * 
 * @author Stan McGrath 
 * @version 1.0
 * @since 1.8
 *
 * The WordCloudDrawer class is responsible for the drawing of the word cloud using the method
 * drawWordCloud().
 */
public class WordCloudDrawer { 
	
	private int maxFontSize = 100;
	private int minFontSize = 20;
	private double totalWords = 0.0;
	private double minPercentage = 0.0;
	private double maxPercentage = 0.0;
	
	/**
	 * The DrawWordCloud method draws the word cloud to a BufferedImage, taking in as parameters our sorted List 
	 * of words mapped to frequency values, user specified number of words to draw and user specified name 
	 * of file to save.
	 * -
	 * @param list - EntrySet list of words mapped to values to draw.
	 * @param numberOfWords - integer numberOfWords to draw.
	 * @param saveFileName - String name of file to save.
	 * 
	 * */
	public void drawWordCloud(List<Entry<String, Integer>> list, int numberOfWords, String saveFileName) throws Exception {

		BufferedImage image = new BufferedImage(1200, 600, BufferedImage.TYPE_4BYTE_ABGR);
		WordPlacementRandomizer wordPlacementRandomizer = new WordPlacementRandomizer();
		WordStyleRandomizer wordStyleRandomizer = new WordStyleRandomizer();
		Graphics graphics = image.getGraphics();

		/*The below for loop calculates the percentage ratios of each word, relative to the maximum
		 * and minimum FontSize values. We use this to make sure words are distributed between a maximum
		 * and minimum font size, so that the words will always be drawn within the boundaries of the
		 * buffered image.
		 */
		
		for (int i = 0; ((i <= numberOfWords) && (i < list.size())); i++) {
			totalWords += list.get(i).getValue();
		}

		for (int i = 0; ((i <= numberOfWords) && (i < list.size())); i++) {
			if (list.get(i).getValue() / totalWords > maxPercentage || i == 0) {
				maxPercentage = list.get(i).getValue() / totalWords;
			}
			if (list.get(i).getValue() / totalWords < maxPercentage || i == 0) {
				minPercentage = list.get(i).getValue() / totalWords;
			}
		}
		
		
		/* The below for loop loops over our list of words, and stops when the index reaches the user specified
		 * maximum number of words to be drawn. It will also stop when it reaches the end of the list, to avoid
		 * an indexOutOfBounds exception being thrown. it Executes in O(1) Time. (The list is a HashMap). */

		for (int i = 0; ((i <= numberOfWords) && (i < list.size())); i++) { 
																			
			String word = list.get(i).getKey();
			Font tempFont = null;
			Color tempColor = null;

			/* The below check checks if the word is at index 0 of the list (i.e., if it is the biggest word, 
			 * (having the highest frequency of occurrences), since our list is sorted in descending order. 
			 * The font is set to be of type SANS_SERIF, of style BOLD, and of a Size calculated based on its 
			 * relativity to the other words in the list, within the bounds of maxFontSize and minFontSize. The
			 * word is then drawn to the image. It then sets the temporary Colour and Font variables to be the 
			 * same, in case the next word has the same frequency of occurrences (in which case it will use these 
			 * same values) Executes in O(1) Constant time. (Needs only to access the first index of the list).
			 */
			
			if (i == 0) {
				Font font = new Font(Font.SANS_SERIF, Font.BOLD, (int)((maxFontSize - minFontSize) * (list.get(i).getValue() / totalWords - minPercentage) / (maxPercentage - minPercentage) + minFontSize));
				Color thisColor = Color.red;
				graphics.setColor(thisColor);
				graphics.setFont(font);
				graphics.drawString(word, (int) (image.getHeight() / 2.4), image.getWidth() / 4);
				tempFont = font;
				tempColor = thisColor;

			} else {
				
				/*The below check is run for every word that is not at index 0 of the list. It checks whether
				 * the frequency of occurrences of the new word is the same as the word at the previous index.
				 * If it is, it uses the same fontSize and Colour as the previous word, and draws it at a random
				 * point relative to the center of the image. Excecutes in O(1) Time (HashMap). 
				 */

					if ((list.get(i).getValue()).equals(list.get(i - 1).getValue())) {

					Font font = tempFont;
					graphics.setColor(tempColor);
					graphics.setFont(font);
					graphics.drawString(word, wordPlacementRandomizer.randomizePointX(image), wordPlacementRandomizer.randomizePointY(image));
					
					} else {
						
					/* The below code block is run when the frequency value for the checked word is not the same
					 * as the frequency value of the word at the previous index of the list. In this case, a random
					 * Font and Colour is generated, the word is drawn at a random point relative to the center of 
					 * the image, at a size calculated based on its relative frequency to other words in the list and 
					 * and within the bounds of maxFontSize and minFontSize, and the temporary Colour and Font variables 
					 * are set to be the same, in case the next word has the same frequency of occurrences. 
					 * If it does, the same values will be used, if not, a new Font and Colour will again be generated.
					 */

					Font newFont = new Font(wordStyleRandomizer.randomizeFont(), wordStyleRandomizer.randomizeStyle(), (int)((maxFontSize - minFontSize) * (list.get(i).getValue() / totalWords - minPercentage) / (maxPercentage - minPercentage) + minFontSize));
					Color thisColor3 = wordStyleRandomizer.randomizeColor();
					graphics.setColor(thisColor3);
					graphics.setFont(newFont);
					graphics.drawString(word, wordPlacementRandomizer.randomizePointX(image), wordPlacementRandomizer.randomizePointY(image));
					tempFont = newFont;
					tempColor = thisColor3;

				}

			}

		}
		
		//close the graphics object to release any system resources it is using, and draw the final image.
		
		graphics.dispose();
		ImageIO.write(image, "png", new File(saveFileName + ".png"));

	}

}