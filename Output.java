import java.io.*;

public class Output {
	
	final String filename = "OutputFile.txt";
	String mipsCode;
	
	//To clear out the previous run
	public Output(){
		File wipeFile = new File(filename);
		wipeFile.delete();
	}
	
	private void outToFile() throws IOException {
		FileWriter fileWriter = new FileWriter(filename, true);
	    fileWriter.write(mipsCode + "\n");
	    fileWriter.close();
	}
	
	//For R Instructions
	public void setmipsCodeR(String rs, String rt, String rd, int shamt, String funct) {
		
		if(shamt > 0)
			mipsCode = funct + " " + rd + ", " + rt + ", " + shamt + "(" + rs + ")";
		else
			mipsCode = funct + " " + rd + ", " + rt + ", " + rs;
		
		try {
			outToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//For I Instructions
	public void setmipsCodeI(String opcode, String rs, String rt, String immediate) {
			mipsCode = opcode + " " + rs + ", " + rt + ", " + immediate;
			
			try {
				outToFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	//For J Instructions
	public void setmipsCodeJ(String opcode, String address) {
		mipsCode = opcode + " " + address + "\n";
		
		try {
			outToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//For Syscall and anything not an instruction
	public void setmipsCodeMisc(String misc) {
		mipsCode = misc + "\n";
		
		try {
			outToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
