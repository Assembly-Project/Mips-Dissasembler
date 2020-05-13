import java.util.*;
import java.io.*;

public class Input {
	final String filename = "InputFile.txt";
	List<String> bitString = new ArrayList<String>();
	String line;
	
    //Reads InputFile.txt line by line and sends it to bits array
	public void parseThroughFile() throws IOException {  
		FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
         
        while ((line = bufferedReader.readLine()) != null) 
        {
            bitString.add(line);
        }
        bufferedReader.close();
         
        bitString.toArray(new String[bitString.size()]);
	}
	
	//Returns the bits array
	public List<String> getBitString() {
		try {
			parseThroughFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bitString;
	}
	
}