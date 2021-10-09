package kwic;

public class Main {

	public static void main(String[] args) {
		
	FileHelper fileHelper = new FileHelper();
		
		
	// Initializing data structures in order to create the KWIC table.
		
	String words[] = new String[109];          // Array of words in the chapter
	String precedingWords[] = new String[109]; // Array of words preceding every word in the chapter.
	String followingWords[] = new String[109]; // Array of words succeeding every word in the chapter.
	int chapterCount[] = new int[109];		   // Array of integers denoting the chapter for every word. 
	int wordCount[] = new int[109] ;		   // Array of integers denoting the word counter/location of every word. 
		
	
	// In the String variables below, Chapter 2 of Egil's Saga is split in into Strings to be passed into the KWIC.
	// I should have used more inspiring names for these, but they're only used once to get the words into the array.
		
	String sentence1 = "Audbjorn was then king over the Firthfolk; there was an earl of his named Hroald, whose son was";
	String sentence2 = "Thorir. Atli the Slim was then an earl, he dwelt at Gaula; he had sons - Hallstein, Holmstein, and";
	String sentence3 = "Herstein; and a daughter, Solveig the Fair. It happened one autumn that much people were gathered at";
	String sentence4 = "Gaula for a sacrificial feast, then saw Aulvir Hnuf Solveig and courted her; he afterwards asked her to";
	String sentence5 = "wife. But the earl thought him an unequal match and would not give her. Whereupon Aulvir composed";
	String sentence6 = "many love-songs, and thought so much of Solveig that he left freebooting, but Thorolf and Eyvind";
	String sentence7 = "Lambi kept it on.";
	
	// Here, I set the chapter # and word count # for the KWIC.
	for(int i = 0; i < words.length; i++) {
		wordCount[i] = i; 		// Word count is i, and will change with primary key when sorting.
		chapterCount[i] = 2;	// The chapter is 2, for all words in the KWIC.
	}
	
	// Splitting sentences from above into words.
	splitToWords(sentence1, words);
	splitToWords(sentence2, words);
	splitToWords(sentence3, words);
	splitToWords(sentence4, words);
	splitToWords(sentence5, words);
	splitToWords(sentence6, words);
	splitToWords(sentence7, words);
	
	// Determining proceeding and succeeding words, and placing them in the appropriate output arrays.
	
	getPreceding(words, precedingWords); // Getting the 5 preceding words for every word in the chapter.
	getProceding(words, followingWords); // Getting the 5 succeeding words for every word in the chapter.
	
	printTable(words, chapterCount, wordCount, precedingWords, followingWords);	// Printing the KWIC
	fileHelper.printToCSV(words, chapterCount, wordCount, precedingWords, followingWords);	// Printing KWIC to the CSV file.
	
	sortStrings(words, chapterCount, wordCount, precedingWords, followingWords);
	
	printTable(words, chapterCount, wordCount, precedingWords, followingWords);	// Printing the KWIC
	fileHelper.printToCSV(words, chapterCount, wordCount, precedingWords, followingWords);	// Printing KWIC to the CSV file.
	
	}
	

	public static void getPreceding(String[] inputArray, String[] outputArray) {
		
		int j;
		for(int i = 0; i < inputArray.length; i++) {
			String temp= "";
			j = i - 1;
			while(j >= 0 && i - j <= 5) {
				temp = temp + inputArray[j]+" ";
				j--;
			}
			outputArray[i] = temp;
		}
	}
	
	public static void getProceding(String[] inputArray, String[] outputArray) {
		
		int j;
		for(int i = 0; i < inputArray.length; i++) {
			String temp= "";
			j = i + 1;
			while(j < inputArray.length && j - i <= 5) {
				temp = temp + inputArray[j]+" ";
				j++;
			}
			outputArray[i] = temp;
		}
	}
	
	
	public static void splitToWords(String input, String[] array) {
		
		int k = 0; // K integer is for the last empty spot in the output array.
		
		// Loop through output array and adjust K to the first empty spot for adding new words.
		for(int i = 0; i < array.length; i++) 
			if(array[i] != null)
				k++;
		
		// Split input string and placing in temporary array.
		String temp[] = input.split("\\W+"); 
		
		//Adding temporary array items to output array.
		for(int i = 0; i < temp.length; i++) 
			array[k++] = temp[i];
	}
	
	public static void printTable(String[] array, int[] chapters, int[] wordCount, String[] preceding, String[] following) {
		for(int i = 0; i < array.length; i++) 
			System.out.printf("%12s | %d | %3d | %38s | %38s | %n",  array[i], chapters[i], wordCount[i], preceding[i], following[i]);	
	}
	
	
	public static void sortStrings(String[] key, int[] chapters, int[] wordCount, String[] preceding, String[] following) {
	
		for (int i = 0; i < key.length; i++) {
            for (int j = i + 1; j < key.length; j++) {
                
                // to compare one string with other strings
                if (key[i].compareTo( key[j]) > 0) {
                    // swapping
                	swapStrings(key, i, j);
//                	swapInts(chapters, i, j);
//                	swapInts(wordCount, i, j);
//                	swapStrings(preceding, i, j);
//                	swapStrings(following, i, j);
                }
            }//END FIRST LOOP
        }// END SECOND LOOP
	}
	
	public static void swapStrings(String[] array, int a, int b) {
		String temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	public static void swapInts(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
}//END MAIN


