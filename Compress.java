
public class Compress {

	public static void main(String[] args) {
		System.out.println("Name of file to compress: "+args[0]);
		System.out.println("Name of file storing compression codes: "+args[1]);
		
		
		// Initialization of all the variables and objects used in the algorithm 
		String outputName = args[0].substring(0, args[0].length() - 3) + "zzz";
		TextFile codesFile = new TextFile(args[1],"read");
		TextFile compressFile = new TextFile(args[0],"read");
		CompressedFile outputFile = new CompressedFile(outputName,"write");
		ArrayCode codesArray = new ArrayCode(100);
		char nextChar, compressChar, charAt;
		String readCode, getCode;
		int position;

		// extract all the codes from the given document, and put them in the codesArray as CodePair objects
		nextChar = codesFile.readChar();
		while (nextChar != (char)0) {
			readCode = codesFile.readLine();
			CodePair nextCode = new CodePair(nextChar,readCode);
			codesArray.add(nextCode);
			nextChar = codesFile.readChar();
		}
		
		// reads through the file and uses the codesArray to find the code of each code for the character
		// writes the code as individual bits into the compressed file document 
		compressChar = compressFile.readChar();
		while (compressChar != (char)0) {
			position = codesArray.findCharacter(compressChar);
			getCode = codesArray.getCode(position);
			for (int i=0; i<getCode.length();i++) {
				charAt = getCode.charAt(i);
				outputFile.writeBit(charAt);
			}
			compressChar = compressFile.readChar();
		}
		
		// close all the files that were opened
		codesFile.close();
		compressFile.close();
		outputFile.close();
	}

}
