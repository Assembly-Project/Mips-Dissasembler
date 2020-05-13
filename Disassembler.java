import java.util.*;

//Class Disassembler
public class Disassembler 
{
	
//note to self. Substring numbers are wrong because the actual binary input
	//numbers will be in reverse. 
	public static void main(String[] args) 
	{
		String binBits = setBits("00000000001000110001000000100000");
		String registers[] = {"$zero","$at","$v0","$v1","$a0","$a1","$a2","$a3","$t0","$t1","$t2","$t3","$t4",
				"$t5","$t6","$t7","$s0","$s1","$s2","$s3","$s4","$s5","$s6","$s7","$t8","$t9","$k0","$k1","$gp","$sp",
				"$fp","$ra"};
		int fType = opcode(binBits.substring(0,6));
		int rs,rt,rd,shamt;
		String instrName = new String();
		
		if(fType == 0) //do instructions for r-types
		{
			rs = decodeReg(binBits.substring(6,11));
			rt = decodeReg(binBits.substring(11,16));
			rd = decodeReg(binBits.substring(16,21));
			shamt = decodeShamt(binBits.substring(21,26));
			instrName = decodeFunc(binBits.substring(26,32));
			System.out.println(instrName + " " + registers[rd] + " " + registers[rt] + " " + registers[rs] + " " + shamt + " ");
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
		int offset = 0;
		if(bits.charAt(4) == '1')
			offset += 1;
		if(bits.charAt(3) == '1')
			offset += 2;
		if(bits.charAt(2) == '1')
			offset += 4;
		if(bits.charAt(1) == '1')
			offset += 8;
		if(bits.charAt(0) == '1')
			offset += 16;
		return offset;
	}
	
	//I&J-format instructions will pass their opcode to this function
	static String decodeFunc(String bits)
	{
		String funcName = new String();
		int func = 0;
		if(bits.charAt(5) == '1')
			func += 1;
		if(bits.charAt(4) == '1')
			func += 2;
		if(bits.charAt(3) == '1')
			func += 4;
		if(bits.charAt(2) == '1')
			func += 8;
		if(bits.charAt(1) == '1')
			func += 16;
		if(bits.charAt(0) == '1')
			func += 32;
		
		//starts to decode the function bits and returns
			//the name of the instruction
		if(func == 0x20)//r-format instruction names
			funcName = "Add";
		else if(func == 0x21)
			funcName = "addu";
		else if(func == 0x24)
			funcName = "and";
		else if(func == 0x08)
			funcName = "jr";
		else if(func == 0x27)
			funcName = "nor";
		else if(func == 0x25)
			funcName = "or";
		else if(func == 0x2a)
			funcName = "slt";
		else if(func == 0x2b)
			funcName = "sltu";
		else if(func == 0x00)
			funcName = "sll";
		else if(func == 0x02)
			funcName = "srl";
		else if(func == 0x22)
			funcName = "sub";
		else if(func == 0x23)
			funcName = "subu";
		else if(func == 0x8)
			funcName = "addi";
		else if(func == 0x9)
			funcName = "addiu";
		else if(func == 0xc)
			funcName = "andi";
		else if(func == 0x4)
			funcName = "beq";
		else if(func == 0x5)
			funcName = "bne";
		else if(func == 0x24)
			funcName = "lbu";
		else if(func == 0x25)
			funcName = "lhu";
		else if(func == 0x30)
			funcName = "ll";
		else if(func == 0xf)
			funcName = "lui";
		else if(func == 0x23)
			funcName = "lw";
		else if(func == 0xd)
			funcName = "ori";
		else if(func == 0xa)
			funcName = "slti";
		else if(func == 0xb)
			funcName = "sltiu";
		else if(func == 0x28)
			funcName = "sb";
		else if(func == 0x38)
			funcName = "sc";
		else if(func == 0x29)
			funcName = "sh";
		else if(func == 0x2b)
			funcName = "sw";
		else if(func == 0x2)
			funcName = "j";
		else if(func == 0x3)
			funcName = "jal";
		
		return funcName;
	}
	
}