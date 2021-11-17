
public class ArrayCode {
	private CodePair[] codeList;
	private int size;
	private int nextSpot = 0;
	
	// constructor of the class which takes in the size of the ArrayCode
	public ArrayCode(int size) {
		codeList = new CodePair[size];
		this.size = size;
	}
	
	// private method to expand the capacity of the ArrayCode when it is full
	private void expand() {
		CodePair[] largeCodeList;
		if (size>100){
			size = size+20;
			largeCodeList = new CodePair[size];
		}
		else {
			size = size*2;
			largeCodeList = new CodePair[size];
		}
		for (int i=0; i<codeList.length; i++) {
			largeCodeList[i] = codeList[i];
		}
		codeList = largeCodeList;
	}
	
	// private method to decrease the capacity of the Array Code when it is only 25% full
	private void compress() {
		CodePair[] smallCodeList;
		size = size/2;
		smallCodeList = new CodePair[(size)];
		for (int i = 0; i < size; i++) {
			smallCodeList[i] =codeList[i];
		}
	}
	
	// // public method to add a CodePair to the array
	public void add(CodePair pair) {
		if (nextSpot >= size) {
			expand();
		}
		codeList[nextSpot] = pair;
		nextSpot ++;
	}
	
	// public method to remove a CodePair from the array
	public void remove (CodePair pairToRemove) {
		int counter=0;
		boolean check = true;
		// looks for the index position of the CodePair that needs to be removed
		while (check) {
			if (codeList[counter].equals(pairToRemove)) {
				check = false;
			}
			else {
				counter++;
			}
		}
		codeList[counter] = codeList[(nextSpot-1)];
		nextSpot --;
		if (nextSpot<(size/4)) {
			compress();
		}
	}
	
	// public method to find the index position of the CodePair given the code
	public int findCode(String code) {
		for (int i=0;i<nextSpot;i++){
			if (codeList[i].getCode().equals(code)) {
				return i;
			}
		}
		return (-1);
	}
	
	// public method to find the index position of the CodePair given the character
	public int findCharacter(char c) {
		for (int i=0;i<nextSpot;i++){
			if (codeList[i].getCharacter() == c) {
				return i;
			}
		}
		return (-1);
	}
	
	// public method to get the code of the CodePair at the given index position
	public String getCode(int i) {
		if (i < 0 || i >= size) {
			return null;
		} else {
			return codeList[i].getCode();
		}
	}
	
	// public method to get the character of the CodePair at the given index position
	public char getCharacter(int i) {
		if (i < 0 || i > nextSpot) {
			return 0;
		}
		return codeList[i].getCharacter();
	}
	
	// public method to get the size of the array
	public int getSize() {
		return size;
	}
	
	// public method to get the number of CodePairs in the array
	public int getNumPairs() {
		return (nextSpot);
	}	
}
