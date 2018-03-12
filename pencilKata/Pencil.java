package pencilKata;

public class Pencil {

	int leadDurability = 0;
	public static final int leadDefault = 500;
	
	public Pencil() {
		leadDurability = leadDefault;
	}
	
	public Pencil(int instLeadDurability) {
		leadDurability = instLeadDurability;
		
	}
	
	public String write(String paper, String writing) {
		return paper+=writing;
	}
	
	public String write(Pencil pencil, String paper, String writing) {
		for (int i = 0; i<writing.length(); i++) {
			char character = writing.charAt(i);
			int leadRequired = 0;
			if (Character.isUpperCase(character) || Character.isDigit(character)) {
				leadRequired = 2;
			} else if (!Character.isWhitespace(character)) {
				//Assume any special characters use the same amount of lead as a lower-case letter
				leadRequired = 1;
			}
			if (pencil.leadDurability >= leadRequired) {
				paper+=character;
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
}
