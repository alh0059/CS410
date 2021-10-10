package kwic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class FileHelper {

	public void printToCSV(String[] array, int[] chapters, int[] wordCount, String[] preceding, String[] following) {
		
		File file = new File("index01.csv");
		
		try {
			if(file.createNewFile()) 
				System.out.println("File created");
			else 
				System.out.println("File exists");	
		} catch (IOException e1) {
			System.out.println("File error");
			e1.printStackTrace();
		}
		
		try {
            FileWriter output = new FileWriter(file);
            CSVWriter write = new CSVWriter(output);

            // Header column value
            String[] header = { "Word", "Chapter #", "Word #", "Preceeding Word", "Succeeding Word" };
            write.writeNext(header);
            for(int i = 0; i < array.length; i++) {
            	 String[] data = { array[i], Integer.toString(chapters[i]), Integer.toString(wordCount[i]), preceding[i], following[i] };
            	 write.writeNext(data);
            }
           
            write.close();
        } catch (Exception e) {
            System.out.println("Error writing to the file");
            e.printStackTrace();
        }
	} // end of printToCSV
	
	
	public void readFromCSV(String[] outputArray) throws Exception {
		File file = new File("chapter4.txt");
		BufferedReader br = null;
		// Creating an object of BuffferedReader class
        try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        // Declaring a string variable
        String string;
        // Consition holds true till
        // there is character in a string
        while ((string = br.readLine()) != null) {
        	splitToWords(string, outputArray);
        }
 
            // Print the string
            System.out.println(string);
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
	
}// END Class
