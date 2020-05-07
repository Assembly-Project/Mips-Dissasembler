package disassembler;
import java.util.*;

//Class Disassembler
public class Disassembler 
{
	
//note to self. Substring numbers are wrong because the actual binary input
	//numbers will be in reverse. 
	public static void main(String[] args) 
	{
		String binBits = setBits("000000000010001100010000");
		String registers[] = {"$zero","$at","$v0","$v1","$a0","$a1","$a2","$a3","$t0","$t1","$t2","$t3","$t4",
				"$t5","$t6","$t7","$s0","$s1","$s2","$s3","$s4","$s5","$s6","$s7","$t8","$t9","$k0","$k1","$gp","$sp",
				"$fp","$ra"};
		int fType = opcode(binBits.substring(0,6));
		int rs,rt,rd;
		
		if(fType == 0) //do instructions for r-types
		{
			rs = decodeReg(binBits.substring(6,11));
			rt = decodeReg(binBits.substring(11,16));
			rd = decodeReg(binBits.substring(16,21));
		}
		else if(fType == 1) //do instructions for j-types
		{
			
		}
		else //do instructions for i-type
		{
			
		}
	}
	
	static String setBits(String b)
	{
		return b;
	}
	
	static int opcode(String bits)
	{
		if(bits.equals("000000"))
			return 0; //r-format
		else if(bits.equals("000010") || bits.equals("000011"))
			return 1; //j-format
		else
			return 2; //i-format
	}
	
	static int decodeReg(String bits)
	{
		int reg=0;
		if(bits.charAt(4) == '1')
			reg += 1;
		if(bits.charAt(3) == '1')
			reg += 2;
		if(bits.charAt(2) == '1')
			reg += 4;
		if(bits.charAt(1) == '1')
			reg += 8;
		if(bits.charAt(0) == '1')
			reg += 16;
		return reg;
	}
	
	static int decodeShamt(String bits)
	{
		return 0;
	}
	
	static String decodeFunc(String bits)
	{
		
		return "";
	}
	
}
