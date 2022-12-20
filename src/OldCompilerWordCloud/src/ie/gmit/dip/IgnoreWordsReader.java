package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 
 * @author Stan McGrath
 * @version 1.0
 * @since 1.8
 * 
 * The class IgnoreWordsReader reads a .txt file of ignore words and converts it into a treeset so it can be very
 * quickly searched.
 *
 */
public class IgnoreWordsReader {

	/**
	 *  The method getIgnoreWords converts the contents of a .txt file word by word into a list.
	 *  It reads from a .txt file line by line using a BufferedReader and adds each line to a list.
	 * 
	 * @return ignoreWordsList - the list of ignorewords created from the .txt file
	 */
	
	private List<String> getIgnoreWords() {

		BufferedReader br = null;

		try {

			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ignorewords.txt"))));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String ignoreWord;

		List<String> ignoreWordsList = new ArrayList<String>();

		try {
			while ((ignoreWord = br.readLine()) != null) {
				ignoreWordsList.add(ignoreWord);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ignoreWordsList;
	}

	/** 
	 * The method createIgnoreWords calls the method getIgnoreWords and turns its returned list into a treeset.
	 * 
	 * @return ignoreWordsTreeSet - treeset of ignore words.
	 */

	public TreeSet<String> createIgnoreWordsTreeSet() {

		TreeSet<String> ignoreWordsTreeSet = new TreeSet<String>(getIgnoreWords());

		return ignoreWordsTreeSet;

	}

}