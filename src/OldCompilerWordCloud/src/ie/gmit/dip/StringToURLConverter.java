package ie.gmit.dip;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @author StanMcGrath
 * @version 1.0
 * @since 1.8
 *
 * The StringToURLConverter class converts type string to URL.
 *
 */
public class StringToURLConverter {
	
	private URL url;
	
	/**
	 * The StringToURL method takes in a string as a parameter and converts it to a URL type.
	 * 
	 * @param string to convert to URL
	 * @return string converted to URL type
	 * @throws MalformedURLException
	 */
	public URL stringToURL(String string) throws MalformedURLException { 
		
		url = new URL(string);
		return url;
		
	}
	

}
