package ie.gmit.dip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/** 
 *
 * @author Stan McGrath
 * @version 1.0
 * @since 1.8
 *
 * The WordCloudGenerator is responsible for most of the delegation in our application.
 * It calls all of the necessary classes and methods in order to draw the WordCloud.
 */
public class WordCloudGenerator {
	
	private String filePath;
	private int maxWords;
	private String saveFileName;
	private String urlString;
	
	// setters from user input menu.
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public void setMaxWords(int maxWords) {
		this.maxWords = maxWords;
	}
	
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
	
	/**
	 * The generateWordCloudFromTxtOrUrl method checks whether a .txt file or URL has been 
	 * entered by the user. If a .txt file has been entered, the generateWordCloud method is called
	 * using the .txt file as a parameter. If a URL has been entered, the generateWordCloud method
	 * is called using the URL as a parameter.
	 * 
	 * @throws Exception
	 */
public void generateWordCloudFromTxtOrUrl() throws Exception { 

		if (filePath != null) {
			
			TextFileReader tfr = new TextFileReader();
			List<String> txtList = tfr.getWords(filePath);
			generateWordCloud(txtList, maxWords, saveFileName);

		} else if (urlString != null) {
			
			URLReader urlr = new URLReader();
			List<String> urlList = urlr.getWords(urlString);
			generateWordCloud(urlList, maxWords, saveFileName);
		
		}

	}
	
/**
 * The generateWordCloud method creates a new instance of the various classes required to generate the Word Cloud,
 * and calls their methods to do so. It takes in as parameters the list to draw from, the max words to draw
 * and the saveFileName to save, draws the word cloud 
 * 
 * 
 * @param list - the final, sorted list to generate the word cloud from
 * @param maxWords - maximum number of words to draw
 * @param saveFileName - name of file to save
 */
private void generateWordCloud(List<String> list, int maxWords, String saveFileName) {
	
		
		IgnoreWordsReader ignoreWordsReader = new IgnoreWordsReader();
		TreeSet<String> ignoreWordsTreeSet = new TreeSet<String>();
		ignoreWordsTreeSet = ignoreWordsReader.createIgnoreWordsTreeSet();
		
		IgnoreWordsRemover ignoreWordsRemover = new IgnoreWordsRemover();
		List<String> updatedList = new ArrayList<String>();
		updatedList = (ignoreWordsRemover.removeIgnoreWords(list, ignoreWordsTreeSet));
		
		FrequencyTableAdder addToFrequencyTable = new FrequencyTableAdder();
		HashMap<String, Integer> frequencyMap = new HashMap<String, Integer>();
		frequencyMap = addToFrequencyTable.addToMap(updatedList);
		
		FrequencyTableSorter sortMap = new FrequencyTableSorter();
		
		WordCloudDrawer drawWordCloud = new WordCloudDrawer();
		
		try {
		
			drawWordCloud.drawWordCloud(sortMap.sortFrequencyTable(frequencyMap), maxWords, saveFileName);
			System.out.println("WordCloud Generated.");
			System.out.println("It can be found in the /bin folder of the project directory.");
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Invalid File Path Entered. Please Try Again.");
		}
		
	}

}

