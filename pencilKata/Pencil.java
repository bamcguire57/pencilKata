package pencilKata;

public class Pencil {

	int leadDurability = 0;
	int eraserDurability = 0;
	public static final int leadDefault = 500;
	public static final int eraserDefault = 200;
	
	public Pencil() {
		leadDurability = leadDefault;
		eraserDurability = eraserDefault;
	}
	
	public Pencil(int instLeadDurability) {
		leadDurability = instLeadDurability;
	}
	
	public Pencil(int instLeadDurability, int instEraserDurability) {
		leadDurability = instLeadDurability;
		eraserDurability = instEraserDurability;
	}
	
	public String write(String paper, String writing) {
		return paper+=writing;
	}
	
	public String write(Pencil pencil, String paper, String writing) {
		for (int i = 0; i<writing.length(); i++) {
			char currentChar = writing.charAt(i);
			int leadRequired = 0;
			if (Character.isUpperCase(currentChar) || Character.isDigit(currentChar)) {
				leadRequired = 2;
			} else if (!Character.isWhitespace(currentChar)) {
				//Assume any special characters use the same amount of lead as a lower-case letter
				leadRequired = 1;
			}
			if (pencil.leadDurability >= leadRequired) {
				paper+=currentChar;
				pencil.leadDurability-=leadRequired;
			} else {
				break;
			}
		}
		return paper;
	}

	public Pencil sharpenPencil(Pencil pencil) {
		pencil.leadDurability = leadDefault;
		return pencil;
	}
	
	public Pencil sharpenPencil(Pencil pencil, int newLeadDurability) {
		//If you're not one for overloading functions, this could easily be a ternary checking the length of the parameter before using it and using the default if the passed int is null.
		pencil.leadDurability = newLeadDurability;
		return pencil;
	}
	
	public String erase(String paper, String stringToErase) {
		if (paper.contains(stringToErase)) {
			String whitespace = "";
			for (int i = 0; i<stringToErase.length(); i++) {
				whitespace+=" ";
			}
			int beginningOfLastInstance = paper.lastIndexOf(stringToErase);
			String newPaper = "";
			newPaper = paper.substring(0, beginningOfLastInstance);
			newPaper = newPaper.concat(whitespace);
			newPaper = newPaper.concat(paper.substring(beginningOfLastInstance+whitespace.length()));
			return newPaper;
		}
		return paper;
	}
	
	//I was planning on calling the other erase function after identifying if the eraser ran out, but that could cause issues if you look for a String that's technically different due to running out of eraser.
	public String erase(Pencil pencil, String paper, String stringToErase) {
		if (paper.contains(stringToErase)) {
			String oldString = stringToErase;
			int eraserRequired = stringToErase.replace(" ", "").length();
			//This could be simpler if you assumed it was only one word being erased, but alas
			if (pencil.eraserDurability >= eraserRequired) {
				pencil.eraserDurability-=eraserRequired;
			} else {
				stringToErase = "";
				for (int i = oldString.length()-1; i>=0&&pencil.eraserDurability>0; i--) {
					char currentChar = oldString.charAt(i);
					stringToErase = currentChar + stringToErase;
					if (!Character.isWhitespace(currentChar)) {
						pencil.eraserDurability--;
					}
				}
			}
			String whitespace = "";
			for (int i = 0; i<stringToErase.length(); i++) {
				whitespace+=" ";
			}
			//Look for old string
			int beginningOfLastInstance = paper.lastIndexOf(oldString);
			//But make sure you're replacing the right part
			beginningOfLastInstance+=oldString.length()-whitespace.length();
			String newPaper = "";
			newPaper = paper.substring(0, beginningOfLastInstance);
			newPaper = newPaper.concat(whitespace);
			newPaper = newPaper.concat(paper.substring(beginningOfLastInstance+whitespace.length()));
			return newPaper;
		}
		return paper;
	}
}
