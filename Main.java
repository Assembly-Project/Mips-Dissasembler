import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		List<String> bitString;
		Disassembler disassembler = new Disassembler();
		Input input = new Input();
		
		bitString = input.getBitString();
		
		for(int index = 0; index < bitString.size(); index++) {
			disassembler.setbits(bitString.get(index));
			disassembler.instructionFormat();
		}
	}

}
