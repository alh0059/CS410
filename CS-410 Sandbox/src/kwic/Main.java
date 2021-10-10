package kwic;

public class Main {

	public static void main(String[] args) throws Exception {
		
	FileHelper fileHelper = new FileHelper(); // File handler object from FileHelper class. 
	HelperFunctions kwicHelper = new HelperFunctions();
		
	// Initializing data structures in order to create the KWIC table.
	final int chapter = 4;			// The chapter of Egil's Saga that the KWIC is being made with.
	final int chapterSize = 555;	// The number of words inside the chapter, used to initialize memory space for storing each word.
	
	String words[] = new String[chapterSize];          // Array of words in the chapter
	String precedingWords[] = new String[chapterSize]; // Array of words preceding every word in the chapter.
	String succedingWords[] = new String[chapterSize]; // Array of words succeeding every word in the chapter.
	int chapterCount[] = new int[chapterSize];		   // Array of integers denoting the chapter every word comes from. 
	int wordCount[] = new int[chapterSize] ;		   // Array of integers denoting the index of every word within the chapter. 
		
	// In this loop, I set the chapter # and word count # for the KWIC.
	for(int i = 0; i < chapterSize; i++) {
		wordCount[i] = i; 			// Word count is the integer i, this column of numbers moves with primary key when sorting.
		chapterCount[i] = chapter;	// The chapter is 4, for all words in the KWIC.
	}
	
	fileHelper.readFromCSV(words); // Reading the chapter from the text file. Placing each word into the words array for Strings.
	
	// Determining proceeding and succeeding words, and placing them in the appropriate output arrays.
	kwicHelper.getPreceding(words, precedingWords); // Getting the 5 preceding words for every word in the chapter.
	kwicHelper.getProceding(words, succedingWords); // Getting the 5 succeeding words for every word in the chapter.
	
	//printTable(words, chapterCount, wordCount, precedingWords, followingWords);	// Printing the KWIC
	fileHelper.printToCSV("index01.csv",words, chapterCount, wordCount, precedingWords, succedingWords);	// Printing KWIC index to the appropriate CSV file.
	
	kwicHelper.sortWords(words, chapterCount, wordCount, precedingWords, succedingWords);
	
	kwicHelper.printTable(words, chapterCount, wordCount, precedingWords, succedingWords);	// Printing the KWIC
	fileHelper.printToCSV("indexsorted.csv",words, chapterCount, wordCount, precedingWords, succedingWords);	// Printing the sorted KWIC to the appropriate CSV file.
	
	}
	
}//END MAIN


