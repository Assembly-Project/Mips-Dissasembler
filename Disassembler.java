import java.util.*;

//Class Disassembler
public class Disassembler 
{
	private String binBits = new String();
	

	public static void main(String[] args) 
	{
		
	}
	
	void setBits(String b)
	{
		binBits = b;
	}
	
	int opcode()
	{
		String op = binBits.substring(0,6);
		
		if(op.equals("000000"))
			return 0; //r-format
		else if(op.equals("000010") || op.equals("000011"))
			return 1; //j-format
		else
			return 2; //i-format
	}
	
}
