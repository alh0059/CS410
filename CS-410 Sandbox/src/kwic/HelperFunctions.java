package kwic;

public class HelperFunctions {

		public void getPreceding(String[] inputArray, String[] outputArray) {
				
			int j; // Loop variable
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
	
	public void getProceding(String[] inputArray, String[] outputArray) {
		
		int j; // Loop variable
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
	
	public void printTable(String[] array, int[] chapters, int[] wordCount, String[] preceding, String[] following) {
		for(int i = 0; i < array.length; i++) 
			System.out.printf("%13s | %d | %3d | %41s | %41s | %n",  array[i], chapters[i], wordCount[i], preceding[i], following[i]);	
	}
	
	// Bad brute force swapping algorithm. Should use a more advanced sorting algorithm in the future.
	public void sortWords(String[] key, int[] chapters, int[] wordCount, String[] preceding, String[] following) {
	
		for (int i = 0; i < key.length; i++) {
            for (int j = i + 1; j < key.length; j++) {
                
                if (key[i].compareTo( key[j]) > 0) {
                    // Swapping every column in the KWIC, based on the primary key. 
                	// For different primary key, sequence parameters in a different order when calling the function.
                	swapStrings(key, i, j);
                	swapInts(chapters, i, j);
                	swapInts(wordCount, i, j);
                	swapStrings(preceding, i, j);
                	swapStrings(following, i, j);
                }
            }//END FIRST LOOP
        }// END SECOND LOOP
	}// END sortStrings()W
	
	public void swapStrings(String[] array, int a, int b) {
		String temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	public void swapInts(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
} // END HelperFunctions
