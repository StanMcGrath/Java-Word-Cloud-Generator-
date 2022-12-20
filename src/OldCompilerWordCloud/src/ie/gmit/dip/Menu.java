package ie.gmit.dip;

import java.util.Scanner;

public class Menu {
	
	private Scanner s;
	private String filePath; 
	private String urlString;
	private int maxWords; 
	private String saveFileName; 
	private boolean keepRunning = true;
	private boolean keepRunning2 = true;
	private boolean keepRunning3 = true;
	
	/**
	 * @author Stan McGrath
	 * @version 1.0
	 * @since 1.8
	 * 
	 * The Menu class prompts the user to specify parameters for the WordCloud application. 
	 * While loops and try-catches are used to force correct input types. Once all parameters
	 * have been entered correctly, the generateWordCloud() method is called.
	 */
	
	public Menu() {
		s = new Scanner(System.in);
	}
	
	public void start() throws Exception {
		System.out.println("///////////////////////////////////");
		System.out.println("Welcome to the WordCloud Programme!");
		System.out.println("///////////////////////////////////");

		while (keepRunning) {

			while (keepRunning2) {
			showOption0();
			}

			while (keepRunning3) {
			showOption2();
			}
			
			showOption3();
			
			generateWordCloud();
			
		}
	}
	
	/**The showOption0 method prompts the user to choose between a Text File
	 * or URL source to read from and input is read from a scanner.
	 * A Try-Catch is used to make sure the user
	 * types an integer value using keys between 0-9. When correct value types
	 * have been entered, the keepRunning2 boolean is set to false so the 
	 * option stops displaying. If the incorrect value types are entered,
	 * an error message is displayed to the console and the option remains
	 * showing, so the user can re-try entering the correct value type.
	 */
	private void showOption0() {
		System.out.println("Would you like to read from a URL or Text File?");
		System.out.println("Enter [1] to select Text File");
		System.out.println("Enter [2] to select URL");
		try {
		int choice = Integer.parseInt(s.nextLine());
		if (choice == 1) {
			keepRunning2 = false;
			showOption1Text();
		} else if (choice == 2) {
			keepRunning2 = false;
			showOption1URL();
		} else {
			System.out.println("[ERROR] Invalid Input. Please enter [1] or [2]");
		}
		} catch (Exception e) {
			System.out.println("[ERROR] Invalid Input. Please enter [1] or [2]");
		}
	}

	/** 
	 * The showOption1Text() method prompts the user to enter the filepath of the .txt
	 * file to be read from using a scanner to read input. The input is saved to the 
	 * global string variable "filePath".
	 */
	private void showOption1Text() {
		System.out.println("[Sample FilePath Format:] C:\\Users\\PC\\Desktop\\TestThis.txt");
		System.out.println("1) Please Enter the File Path of the text you wish to generate WordCloud from:");
		filePath = s.nextLine();
		System.out.println("Your chosen filePath: " + filePath);
	}

	/**
	 * The showOption1URL() method prompts the user to enter the URL of the page to be read
	 * from using a scanner to read input. The input is saved to the global string variable
	 * "urlString".
	 */
	private void showOption1URL() {
		System.out.println("[Sample URL Format:] https://markfishercyberfield.github.io/Video%20Archive.html");
		System.out.println("1) Please Enter the URL of the text you wish to generate WordCloud from:");
		urlString = s.nextLine();
		System.out.println("Your chosen URL: " + urlString);	
	}

	/** The showOption2() method asks the user to enter the maximum number of words to be drawn
	 * in the WordCloud. A Try-Catch is used to make sure a correct integer type is entered. When a
	 * correct value-type has been entered, the keepRunning3 boolean is set to false so the option stops
	 * displaying. Input is saved to the global variable "maxWords". If an incorrect value type (a non-integer
	 * type), an error message is displayed to the console and the option remains showing, so the user can re-try
	 * entering the correct value type.
	 * 
	 */
	private void showOption2() {
		System.out.println("2) Please Enter the Maximum Number of Words to be drawn in WordCloud:");
		
		try {
		maxWords = Integer.parseInt(s.nextLine());
		System.out.println("Max Words to be drawn in WordCloud:" + maxWords);
		keepRunning3 = false;
		
		} catch (Exception e) {
			System.out.println("[ERROR] Invalid number entered. Please try again with a valid integer value.");
		}
		
	}

	/**
	 * The method showOption3() prompts the user to specify the saved file name for their WordCloud. 
	 * input is saved in the global variable "saveFileName".
	 */
	private void showOption3() {
		System.out.println("3)Please Enter the File Name of the WordCloud image you wish to save:");
		saveFileName = s.nextLine();
		System.out.println("Your WordCloud will be saved as: " + saveFileName);
	}
	
	/**
	 * the method generateWordCloud creates a new instance of the WordCloudGenerator class
	 * and calls its method to generate the word cloud. After completion, the keepRunning variable
	 * is set to false so the menu closes.
	 * 
	 * @throws Exception
	 */
	private void generateWordCloud() throws Exception {
		
		System.out.println("Generating WordCloud... This may take a few moments...");
		WordCloudGenerator wcg = new WordCloudGenerator();
		wcg.setFilePath(filePath);
		wcg.setMaxWords(maxWords);
		wcg.setUrlString(urlString);
		wcg.setSaveFileName(saveFileName);
		wcg.generateWordCloudFromTxtOrUrl();
		keepRunning = false;
		
	}

}
