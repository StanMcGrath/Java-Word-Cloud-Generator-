package ie.gmit.dip;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 
 * @author Stan McGrath
 * @version 1.0
 * @since 1.8
 *
 */
public class IgnoreWordsRemover {
	
/** 
 * The method removeIgnoreWords takes in as parameters a desired list of words and a treeSet of ignore words, and adds
 * words not present in the ignore words treeset to a new list. It compares each word in the list against the words 
 * in the treeset, and if there is a match, the word is not added to the list, otherwise the word is added.
 * 
 * 
 * @param list - list of words to check
 * @param treeSet - ignore words treeset
 * @return updatedList - new list of words with ignore words removed
 */
public List<String> removeIgnoreWords(List<String> list, TreeSet<String> treeSet) {
		
		List<String> updatedList = new ArrayList<String>();
		
		for (String element : list) {
		
			if(treeSet.contains(element)) {
			
				// do nothing
				
			} else {
				
				updatedList.add(element);
			
			}
		
		}
		
		return updatedList;
		
	}

}
