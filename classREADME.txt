///////////////////////////////////////////////

Features of WordCloud Application - G00398383

///////////////////////////////////////////////

This .txt document describes the design and rationale
of each feature of our WordCloud application, in 
order of excecution.

In accordance with the SRP, we aimed to fully 
encapsulate each chunk of responsibility of our
application into distinct classes, and to loosely 
couple these classes as much as possible in
order to shield them from change and promote re-use. 
There are 13 classes and 1 Interface in total.

///////////////////////////////////////////////

1) Runner Class / Main Method

The "Runner" Class is the entry point of excecution
in our application. It contains only the Main method,
which creates a new instance of the "Menu" class and 
calls the public start() method of this Menu class.

///////////////////////////////////////////////

2) Menu Class

The Menu Class is responsible only for Menu logic. 
We declare private instance variables whose values 
will be assigned through console-based user input.
These variables include "filePath", which is the 
source of the desired .txt file to run in the application,
"urlString", which is the desired url to run in the application
(in String format), "maxWords", which is the desired number of 
words to draw in the WordCloud, and a boolean to keep the
menu running as long as needed using a while loop.

The Menu initializes a Scanner from the Java Scanner class in
order to read user input to the console to pick options from the 
menu. Inside a while loop, instructions are printed out to the 
console, and the user is asked step by step to input the parameters
discussed above. This is done via the following functions: 

"showOption0()": 

Asks the user whether they would like to input a .txt file or URL,
in which case either showOption1Text() or showOption1URL() is called.

"showOption1Text()": 

Asks the user to specify the source of the desired .txt file on their
machine and shows a sample of the correct input format.

"showOption1URL()":

Asks the user to specify the source of the desired URL link and shows
a sample of the correct input format.

"showOption2()":

Asks the user to enter the maximum number of words to be displayed in 
the WordCloud.

"showOption3()":

Asks the user to specify the name of the WordCloud.png file to be saved.
When this final parameter is entered, this function calls the 
"generateWordCloud()" method.

"generateWordCloud()": 

Creates a new instance of the "WordCloudGenerator" class and calls the setter
methods contained within to set the user specified parameters as it's own 
variables. Then calls the "generateWordCloudFromTxtOrURL()" method of that class.
Finally, sets the boolean "keepRunning" to false, to close the Menu.

///////////////////////////////////////////////

3) WordCloudGenerator Class

This Class is responsible for calling each of the various classes in our 
application and delegating their reponsibilities. All mentioned delegated
classes and functions will be explained in their relevant sections in this .txt
document.

We declare instance variables to match those described in the "Menu" class
above, as well as their respective setter methods, also described above. These
are setFilePath(), setMaxWords(), setSaveFileName(), and setUrlString(). 

The method "generateWordCloudFromTxtOrURL()", which was called by the "Menu" class
(mentioned above), detects whether the user has inputted a .txt file or a URL using
if else if statements. In the first case, an instance of the class "TextFileReader"
is created, and new List<String> is declared, and this list is populated with the return 
parameter of the public getWords() method of the "TextFileReader" class. The method
generateWordCloud() is then called. In the second case, an instance of the class "URLReader" 
is created, a new List<String> is declared and this list is populated with the return 
parameter of the public getWords() method of the "URLReader" class. The method 
generateWordCloud() is then called, which takes a number of steps. This function is 
responsible for almost all of the delegation in the programme and works as follows.

"generateWordCloud()":

Step 1: Creates a new instance of the "ignoreWordsReader" class, declares a TreeSet<String>, 
and populates this treeset with the return parameter of the createIgnoreWordsTreeSet() method 
from the "ignoreWordsReader" class. (Creates a treeset of ignorewords to enable fast searching).

Step 2: Creates a new instance of the "ignoreWordsRemover" class, declares a new List<String>,
and populates it with the return parrameter of the removeIgnoreWords() method from the 
"ignoreWordsRemover" class. (Removes the ignore words from the user's inputted list). 

Step 3: Creates an instance of the "FrequencyTableAdder" class, declares a 
HashMap<String, Integer>, and populates it with the return parameter of the addToMap() function
of the "FrequencyTableAdder" class. (Maps String keys (words) to Integer values (Number of occurances of those words).

Step 4: Creates an instance of the "FrequencyTableSorter" class, Creates an instance of 
the "WordCloudDrawer" class, and calls its drawWordCloud() method on the return parameter of 
the sortFrequencyTable() method of the "FrequencyTableSorter" class. (Frequency table in descending order and draws
the sorted words to the word cloud). 

After completing these steps, the wordcloud has been generated and "WordCloud generated" is printed to the console.
The programme ends here.

///////////////////////////////////////////////

4) TextFileReader Class

We declare private instance "line" and "words" variables of type String and List<String>.

The getWords() method takes the user-menu specified .txt file as a parameter, creates a new instance of
the BufferedReader Java Class, and uses this BufferedReader to read an InputStream which reads a FileInputStream
from the .txt file. While the next line in the BufferedReader is not empty, it creates a new String[] Array, and splits
the line into words which populate this temporary array. For every new word in this array, special characters are removed,
and if the length of the word is greater than 2 characters long, the word is added as an element our List<String>. This
process is repeated until there are no more lines to be read by the BufferedReader, at which point our List<String> is fully populated by words from the user specified .txt file.

///////////////////////////////////////////////

5) URLReader Class

We declare private instances "line", "words" and "url" variables of type String, List<String> and URL. 

The method stringToURL() takes in the parameter of urlString which is the user-specified URL inputted in the "Menu" class.
(Described above in the relevant section). A new Instance of the StringToURLConverter is created, and its method stringToURL() is called, which converts the urlString into URL format. 

the getWords() method takes in the parameter of the user-menu specified urlString, calls the stringToURL() method on it,
and creates a new instance of the BufferedReader Java class, using this BufferedReader to read an InputStream from the
URL. From here on the same process described above in "4) TextFileReader Class" is conducted, but with some extra
logic in the form of if else if statements which check to see if the words contain certain HTML tags or special characters,
in which case those words are not added to the List<String>.


///////////////////////////////////////////////

i1) SourceReaderator Interface

This interface specifies the "line" and "words" variables of type String and list that must be used by any Source Readers in the programme, and specifies the getWords() method which takes in a String parameter and returns a List<String>, which must also be used by any Source Readers in the programme. Implemented by 4) TextFileReader and 5) URLReader classes.


///////////////////////////////////////////////

6) StringToURLConverter Class

We declare a private instance variable "url" of type URL.

The method stringToURL() takes in a string as a parameter and assigns the value of this string to our "url" variable
of type URL.

///////////////////////////////////////////////

7) IgnoreWordsReader Class

The method getIgnoreWords() in this class creates a new instance of the BufferedReader Java Class, uses it to read an InputStreamReader on a FileInputStream from a hard coded file called "ignorewords.txt" which sits in the project directory.
It then creates a variable "ignoreWord" of type String and a list "ignoreWordsList" of type ArrayList<String>. While the next line in the BufferedReader is not empty, we add this line to our ignoreWordsList. We then return this list.

The method createIgnoreWordsTreeSet() creates a new TreeSet<String> and populates it with the return value of the above getIgnoreWords() method. (list of ignore words). This TreeSet of ignore words is then returned. We chose to use a TreeSet data structure here so that this Set of ignore words can be read very fast- in O(Log n) search time- as every word in our source .txt file (or URL) must be checked against every word in this ignore words list. The O(n) speed of a standard array would have been much too slow in the case of source material with a very large quantity of words. 

///////////////////////////////////////////////

8) IgnoreWordsRemover Class

The method removeIgnoreWords() in this class takes in as parameters our source list of words and our TreeSet (mentioned above in section 7). it then creates a new "updatedList" of type List<String>, and for each element in our original list, 
checks to see if the element exists in the TreeSet. If it exists, it is not added to our new list, if it does not exist, it 
is added to the list. The updated List is then returned. (Original source list with ignore words removed). This function
operates in O(n) time, as each element in the original list must be checked (O(n)), and thus the O(log n) speed of
searching the TreeSet is a negligible addition to time complexity.

///////////////////////////////////////////////

9) FrequencyTableAdder Class

The method addToMap() in this class takes in as its parameter a List<String>. (Our list with ignorewords removed). It then
declares a new HashMap<String, Integer>, and iterates through each word of our list using a for loop. For each word in our list, we declare an int variable "frequency". Each word is converted to lower case and added to the table as a key, and if the word already exists, the frequency value is incremented. The word (key) is then mapped to the frequency (value) of occurances. This repeats for each string in our list and returns a HashMap<String, Integer> of words mapped to frequencies.

///////////////////////////////////////////////

10) FrequencyTableSorter Class

The method sortFrequencyTable() in this class takes in as its parameter our HashMap (described above in section 9). It creates
an entry set from this map, and a new ArrayList from this entrySet. It then overrides the inbuilt Comparator of the HashMap to 
compare values (frequencies of occurances) and sort them in descending order, using a lamdba expression which was auto generated 
using Ctrl+1 in the Eclipse IDE from an anonymous function that we created. This new list is then returned.

///////////////////////////////////////////////

11) WordCloudDrawer Class

We declare private instance variables of "maxFontSize" and "minFontSize" of type int, and "totalWords", "minPercentage" and
"maxPercentage" of type double. 

The method drawWordCloud() in this class takes in as a parameter the sorted List described above in Section 10, as well as the
user-menu defined "numberOfWords" and "saveFileName" int and String variables. It then creates a new instance of BufferedImage
from Javas BufferedImage Class of a predefined image size. Instances of "WordPlacementRandomizer" and "WordStyleRandomizer" 
classes are then created. A for loop calculates the percentage ratios of each word, relative to the maximum and minimum FontSize 
values. We use this to make sure words are distributed between a maximum and minimum font size, so that the words will always be
drawn within the boundaries of the BufferedImage. Another for loop is used to loop over our list until the "maxWords" limit has been
reached, or until we reach the last element of the list (in case the length of the list is less than the value of "maxWords", to avoid
an indexOutOfBounds exception being thrown. Within this for loop, for each element, we check whether the element is at index 0 
(i.e., the first word in the list, and therefore the word with the highest frequency of occurances, since the list is in descending 
order). If this is true, we hard code the font colour to be red (so it "pops" out more visually), and draw it to roughly the middle
of the image. We use our size forloop mentioned above to calculate the size relative to the other words in the list (this one will 
always be the biggest). If the element is not at index 0, a check is performed using if else statements to see whether the frequency
of the word is the same as the previous word. If it is, it will inherit the same font, colour and size as the previous word, and be
drawn to a random point on the image using the "WordPlacementRandomizer" class. If the frequency of the word is not the same, a new 
font style and colour is generated using our "WordStyleRandomizer" class, and the word is drawn to a random point on the image using
the "WordPlacementRandomizer" class. When the end of the for loop is reached and the last element has been drawn to the WordCloud,
we finally draw the entire WordCloud into a .png image, and save it with the name specified by the user in the user-menu.


///////////////////////////////////////////////

12) WordPlacementRandomizer Class

We declare a new instance of the Java Random class. 

The method randomizePointX() takes in as a parameter the BufferedImage we will be drawing to, to get roughly the center point of the image.
It then calculates a random int value between 0 and 14, and based on this random value, offsets the point on the X axis where the word 
will be drawn by a given distance determined by that value using if else statements. This new point is returned by the method as an int value.

The method randomizePointY() works in exactly the same way, but offsets along the Y axis instead.

///////////////////////////////////////////////

13) WordStyleRandomizer Class

We declare a new instance of the Java Random class. 

The method randomizeFont() creates a string[] array of all possible fonts in the Java Graphics Environment, and returns a random font from the 
whole length of this array divided by 2 (a random font from the first half of the array of all possible fonts- this was because some fonts in the
second half appear as boxes on our Windows7 OS- i.e they may be unsupported by older OS's).

The method randomizeStyle() calculates a random integer number between 0 and 2 (3 total values possible) and returns either the bold, plainor italic 
style constant based on which random number was generated using if else statements.

The method randomizeColor() calculates a random integer number between 0 and 8, and returns one of 9 possible colours based on which random number
was generated using if else statements.


///////////////////////////////////////////////

Extra)

WordPlacementRandomizer and WordStyleRandomizer classes were deployed on the initiative of the author of this application. The loop in the WordCloudDrawer 
class which draws words relative to the size of other words and within a max and minimum size range was also deployed as an extra feature.

No code was asset-stripped from the internet, the entire application was created by the author using knowledge gained from the course material lectures and 
javadocs, as well as some google searches for minor syntactical details or how Java API.

The "ignorewords" .txt file is located both in the project folder and src folder. This is by design, as when testing our project in the Eclipse IDE, 
it could only read the .txt file from the Project folder, but when testing through the Windows command prompt, it appeared the text file could only be 
read from the src folder. The author wished for the application to work both through command prompt and eclipse IDE so the .txt file is in both folders.


///////////////////////////////////////////////