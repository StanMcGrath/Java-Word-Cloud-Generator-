package ie.gmit.dip;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Stan McGrath
 * @version 1.0
 * @since 1.8
 * 
 * The class FrequencyTableAdder uses the method addToMap to map keys (words) to values (frequency of occurrences)
 * from a list.
 *
 */
public class FrequencyTableAdder {
		
	@SuppressWarnings("deprecation")
	/**
	 * The method addToMap takes in as parameter a List<String>, and creates a HashMap with the 
	 * keys (word) mapped to their frequency of occurances (values). 
	 * 
	 * @param words - the list of words to create the frequency map from.
	 * @return table - the HashMap of words mapped to frequencies of occurrences.
	 */
	public HashMap<String, Integer> addToMap(List<String> words) { // map strings to integers (O(1)
		HashMap<String, Integer> table = new HashMap<String, Integer>();
		for (String word : words) {
			String lcWord = word.toLowerCase();
			int frequency = 1;
			
			if(table.containsKey(lcWord)) { // O(1) //search and see1 if key exists in map
				frequency = table.get(lcWord); // search and retrieve value associated with key
				frequency++;
			}
			table.put(lcWord, new Integer(frequency)); //0(1) // add to a map
		}
		
		return table;
	
	}

}
