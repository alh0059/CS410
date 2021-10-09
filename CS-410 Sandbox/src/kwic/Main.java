package kwic;

public class Main {

	public static void main(String[] args) throws Exception {
		
	FileHelper fileHelper = new FileHelper();
		
		
	// Initializing data structures in order to create the KWIC table.
	final int chapterSize = 555;	// The number of words inside the chapter, used to initialize memory space for storing each word.
	
	String words[] = new String[chapterSize];          // Array of words in the chapter
	String precedingWords[] = new String[chapterSize]; // Array of words preceding every word in the chapter.
	String succedingWords[] = new String[chapterSize]; // Array of words succeeding every word in the chapter.
	int chapterCount[] = new int[chapterSize];		   // Array of integers denoting the chapter for every word. 
	int wordCount[] = new int[chapterSize] ;		   // Array of integers denoting the word counter/location of every word. 
		
	// Here, I set the chapter # and word count # for the KWIC.
	for(int i = 0; i < words.length; i++) {
		wordCount[i] = i; 		// Word count is i, and will change with primary key when sorting.
		chapterCount[i] = 2;	// The chapter is 2, for all words in the KWIC.
	}
	

	fileHelper.readFromCSV(words); // Reading the chapter from the text file. And placing each word into the words array for Strings.
	
	// Determining proceeding and succeeding words, and placing them in the appropriate output arrays.
	getPreceding(words, precedingWords); // Getting the 5 preceding words for every word in the chapter.
	getProceding(words, succedingWords); // Getting the 5 succeeding words for every word in the chapter.
	
	//printTable(words, chapterCount, wordCount, precedingWords, followingWords);	// Printing the KWIC
	//fileHelper.printToCSV(words, chapterCount, wordCount, precedingWords, followingWords);	// Printing KWIC to the CSV file.
	
	sortStrings(words, chapterCount, wordCount, precedingWords, succedingWords);
	
	printTable(words, chapterCount, wordCount, precedingWords, succedingWords);	// Printing the KWIC
	fileHelper.printToCSV(words, chapterCount, wordCount, precedingWords, succedingWords);	// Printing KWIC to the CSV file.
	
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
			System.out.printf("%13s | %d | %3d | %41s | %41s | %n",  array[i], chapters[i], wordCount[i], preceding[i], following[i]);	
	}
	
	
	public static void sortStrings(String[] key, int[] chapters, int[] wordCount, String[] preceding, String[] following) {
	
		for (int i = 0; i < key.length; i++) {
            for (int j = i + 1; j < key.length; j++) {
                
                // to compare one string with other strings
                if (key[i].compareTo( key[j]) > 0) {
                    // swapping
                	swapStrings(key, i, j);
                	swapInts(chapters, i, j);
                	swapInts(wordCount, i, j);
                	swapStrings(preceding, i, j);
                	swapStrings(following, i, j);
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


