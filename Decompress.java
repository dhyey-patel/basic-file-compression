
public class Decompress {

	public static void main(String[] args) {
		System.out.println("Name of file to decompress: "+args[0]);
		System.out.println("Name of file storing compression codes: "+args[1]);
		
		// Initialization of all the variables and objects used in the algorithm 
		String decompressedFileName = args[0].substring(0, args[0].length() - 3) + "dec";
		TextFile codesFile = new TextFile(args[1],"read");
		TextFile outputFile = new TextFile(decompressedFileName,"write");
		CompressedFile decompressFile = new CompressedFile(args[0],"read");
		ArrayCode codesArray = new ArrayCode(100);
		char nextCodeChar, nextFileChar;
		String readCode, checkCode = "";
		int findCode;
		
		// extract all the codes from the given document, and put them in the codesArray as CodePair objects
		nextCodeChar = codesFile.readChar();
		while (nextCodeChar != (char)0) {
			readCode = codesFile.readLine();
			CodePair nextCode = new CodePair(nextCodeChar,readCode);
			codesArray.add(nextCode);			
			nextCodeChar = codesFile.readChar();
		}
		
		/* reads through the compressed document one bit at a time
		 * gets the bit as a string and adds it to checkCode string
		 * then check code is searched through the codesArray
		 * if there is no match it looks as the next bit
		 * if the match is found it gets the character associated with the code, and writes it to the text document, and resets checkCode
		 */
		nextFileChar = decompressFile.readBit();
		while (nextFileChar != (char)0) {
			checkCode = checkCode+nextFileChar;
			findCode = codesArray.findCode(checkCode);
			if (findCode != (-1)) {
				outputFile.writeChar(codesArray.getCharacter(findCode));
				checkCode = "";
			}
			nextFileChar = decompressFile.readBit();
		}
		
		// close all the files that were opened 
		codesFile.close();
		outputFile.close();
		decompressFile.close();
	}

}
