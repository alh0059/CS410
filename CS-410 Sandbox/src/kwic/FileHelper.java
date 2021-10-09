package kwic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class FileHelper {

	
	
	public void printToCSV(String[] array, int[] chapters, int[] wordCount, String[] preceding, String[] following) {
		
		File file = new File("index01.csv");
		
		try {
			if(file.createNewFile()) {
				System.out.println("File created");
			}else
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

	}
}
