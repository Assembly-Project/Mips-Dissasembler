import java.util.*;

//Class Disassembler
public class Disassembler 
{
	Output output = new Output();
	
	final String registers[] = {"$zero","$at","$v0","$v1","$a0","$a1","$a2","$a3","$t0","$t1","$t2","$t3","$t4",
			"$t5","$t6","$t7","$s0","$s1","$s2","$s3","$s4","$s5","$s6","$s7","$t8","$t9","$k0","$k1","$gp","$sp",
			"$fp","$ra"};
	String bitString;
	int fType;
	int rs,rt,rd,shamt;
	String funct;
	String jAddress;
	
	public void setbits(String bitString) {
		this.bitString = bitString;
		fType = opcode(bitString.substring(0,6));
	}
	
	public void instructionFormat() {
		
		if(fType == 0) //do instructions for r-types
			decodeRInstruction();
		
		else if(fType == 1) //do instructions for j-types
			decodeJInstruction();
		
		else //do instructions for i-type
			decodeIInstruction();
	}
	
	private void decodeRInstruction() {
		rs = decodeReg(bitString.substring(6,11));
		rt = decodeReg(bitString.substring(11,16));
		rd = decodeReg(bitString.substring(16,21));
		shamt = decodeShamt(bitString.substring(21,26));
		funct = decodeRFunc(bitString.substring(26,32));
		if(funct.equals("syscall"))
		{
			output.setmipsCodeMisc("Syscall");
		}
		else
			output.setmipsCodeR(registers[rs], registers[rt], registers[rd], shamt, funct);
	}
	
	private void decodeIInstruction() {
		funct = decodeIJFunc(bitString.substring(0,6));
		rs = decodeReg(bitString.substring(6,11));
		rt = decodeReg(bitString.substring(11,16));
		
		output.setmipsCodeI(funct, registers[rs], registers[rt], "TEMP");
	}
	
	private void decodeJInstruction() {
		funct = decodeIJFunc(bitString.substring(0,6));
		jAddress = decodeJAddress(bitString.substring(6));
		
		output.setmipsCodeJ(funct, jAddress);
	}

	//decodes the opcode
	private int opcode(String bits)
	{
		if(bits.equals("000000"))
			return 0; //r-format
		else if(bits.equals("000010") || bits.equals("000011"))
			return 1; //j-format
		else
			return 2; //i-format
	}
	
	//decodes the register bits
	private int decodeReg(String bits)
	{
		//starts at the fifth bit and works towards to first adding the appropriate power of 2
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
	
	//decodes shamt
	private int decodeShamt(String bits)
	{
		//starts at the 5th bit and goes to the first while adding the correct power of 2
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
	
	//decodes the function name for r-type instruction and syscall
	private String decodeRFunc(String bits)
	{
		//finds the value of the bits
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
		
		//searches for a matching hex code to for the value of the bits passed to it
			//and returns the name of the instruction
		if(func == 0x20)
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
		else if(func == 0xc)
			funcName = "syscall";
		
		
		return funcName;
	}
	
	//decodes the function name for I and J types
		//conflict in the opcode/function code with addi and syscall
	private String decodeIJFunc(String bits)
	{
		//finds the value of the bits passed to it
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
		
		//compares the value of bits passed to it and compares that to the 
			//hex values for the instructions. Returns instruction name
		if(func == 0x8)
			funcName = "addi";//i-format
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
		else if(func == 0x2)//j-types
			funcName = "j";
		else if(func == 0x3)
			funcName = "jal";
		
		return funcName;
	}
	
	//decodes the 26 bit address for J type instructions
	private String decodeJAddress(String bits)
	{
		String address = new String();
		String hexAddress = new String();
		int binToHex;
		//sign extend for 26 bit address. Can replace 4 0's if PC counter is known.
		address = "0000" + bits + "00";
		
		for(int i = 0;i<32;i=i+4)
		{
			//finds the numerical value of the four bits that it is looking at
			binToHex = 0;
			if(address.charAt(i+3) == '1')
				binToHex += 1;
			if(address.charAt(i+2) == '1')
				binToHex += 2;
			if(address.charAt(i+1) == '1')
				binToHex += 4;
			if(address.charAt(i) == '1')
				binToHex += 8;
			
			//takes the numerical value of the four bits, converts it to hex and adds that to the hexadecimal address.
			if(binToHex < 10)
				hexAddress += Integer.toString(binToHex);
			else if(binToHex == 10)
				hexAddress += 'A';
			else if(binToHex == 11)
				hexAddress += 'B';
			else if(binToHex == 12)
				hexAddress += 'C';
			else if(binToHex == 13)
				hexAddress += 'D';
			else if(binToHex == 14)
				hexAddress += 'E';
			else if(binToHex == 15)
				hexAddress += 'F';
		}
			
		return hexAddress;
	}
	
	private String decodeImmField(String bits)
	{
		
		return "";
	}
}
