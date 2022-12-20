package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Stan McGrath
 * @version 1.0
 * @since 1.8
 *
 * The TextFileReader class implements the interface SourceReaderator.
 * It specifies variables line of type String and words of type List,
 * implements a getWords() method and declares a StringToURL method.
 * 
 */
public class URLReader implements SourceReaderator {
	
	private String line;
	private List<String> words = new ArrayList<String>();
	private URL url;
	
	/**
	 * 
	 * The StringToUrl method takes in as parameter a string called urlString
	 * and creates an instance of the StringToURLConverter class, and calls its
	 * method to convert this string to a URL type.
	 * 
	 * @param urlString - String to convert to URL
	 * @return url - The converted String (as URL type).
	 * 
	 */
	private URL stringToURL(String urlString) throws MalformedURLException {
	StringToURLConverter stu = new StringToURLConverter();
	url = stu.stringToURL(urlString);
	return url;
	}

	/**
	 * 
	 * The method getWords() takes as parameter a String named urlString, converts this
	 * file into a URL using the StringToURL method, and using an InputStreamReader and a 
	 * BufferedReader, reads each line of the file and splits it into individual words. For each word, 
	 * if the word contains any character other than a-z or A-Z, the word is not added to the list. 
	 * Otherwise, if the length of the word is greater than 2 characters, it is converted to 
	 * lower case and added to the list.
	 * 
	 * @param urlString - url to read.
	 * @return words - List of words read from .txt file.
	 * 
	 */
	public List<String> getWords(String urlString) throws Exception {
		
		stringToURL(urlString);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		while ((line = br.readLine()) != null) {
			String[] lineArray = line.split(" ");
			for (String word : lineArray) {

				if (!word.matches("[a-zA-Z]+")) {
					
				} else if (word.toLowerCase().startsWith("class")) {
					
				} else if (word.toLowerCase().startsWith("data")) {

				} else if (word.toLowerCase().startsWith("href")) {

				} else if (word.toLowerCase().startsWith("px")) {
				
				} else if (word.toLowerCase().startsWith("xml")) {
					
				} else if (word.toLowerCase().startsWith("src")) {	
					
				} else if (word.toLowerCase().contains("datalink")) {
				
				} else if (word.toLowerCase().startsWith("url")) {
					
				} else if (word.toLowerCase().startsWith("format")) {
				
				} else if (word.toLowerCase().startsWith("font")) {
					
				} else if (word.toLowerCase().startsWith("dcr")) {
					
				} else if (word.toLowerCase().startsWith("rgb")) {
					
				} else if (word.toLowerCase().startsWith("myclass")) {
					
				} else if (word.toLowerCase().startsWith("font")) {
					
				} else {
					
					if (word.length() > 2) {
						words.add(word.toLowerCase());
					}
				}
			}
		}

		return words;
	}

}