
public class CodePair {
	private String code;
	private char character;
	
	// Constructor of the class, which takes in two variables 
	public CodePair(char c, String code) {
		character = c;
		this.code = code;
	}
	
	// public method to get the code
	public String getCode() {
		return code;
	}
	
	// public method to get the character
	public char getCharacter() {
		return character;
	}
	
	// public method to set the character
	public void setCharacter(char c) {
		character = c;
	}
	
	// public method to set the code
	public void setCode(String code) {
		this.code = code;
	}
	
	// public method that checks if the CodePair objects are equal by checking their characters
	public boolean equals(CodePair anotherPair) {
		if (character == anotherPair.getCharacter()){
			return true;
		}
		return false;
	}
}
