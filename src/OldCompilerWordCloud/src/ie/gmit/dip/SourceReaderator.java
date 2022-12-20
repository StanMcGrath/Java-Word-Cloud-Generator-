package ie.gmit.dip;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author StanMcGrath
 * @version 1.0
 * @since 1.8
 * 
 * The SourceReaderator interface is an abstraction of a source reader class.
 * It specifies variables line of type String and words of type List, 
 * and a method getWords which source reader classes must implement.
 *
 */
public interface SourceReaderator {

	public String line = "";
	public List<String> words = new ArrayList<String>();
	
	public List<String> getWords(String string) throws Exception;

}
