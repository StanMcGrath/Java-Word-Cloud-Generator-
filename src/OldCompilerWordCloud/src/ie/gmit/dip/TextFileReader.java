package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
 * and implements a getWords() method.
 * 
 */
public class TextFileReader implements SourceReaderator {

	private String line;
	private List<String> words = new ArrayList<String>();

 /**
 * The method getWords takes as parameter a String named file, converts this
 * file into a File and using FileInputStream, InputStreamReader and a BufferedReader,
 * reads each line of the file and splits it into individual words. For each word, if the
 * word contains any character other than a-z or A-Z, the word is not added to the list. 
 * Otherwise, if the length of the word is greater than 2 characters, it is converted to 
 * lower case and added to the list.
 * 
 * @param file - text file to read.
 * @return words - List of words read from .txt file.
 */
	public List<String> getWords(String file) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
		while ((line = br.readLine()) != null) {
			String[] lineArray = line.split(" ");
			for (String word : lineArray) {

				if (!word.matches("[a-zA-Z]+")) {

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
