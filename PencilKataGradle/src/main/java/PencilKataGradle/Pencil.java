package PencilKataGradle;

public class Pencil {
	private int leadDurability = 0;
	private int eraserDurability = 0;
	private static final int LEAD_DEFAULT = 500;
	private static final int ERASER_DEFAULT = 200;
	
	public Pencil() {
		this(LEAD_DEFAULT, ERASER_DEFAULT);
	}

	public Pencil(int lead) {
		this(lead, ERASER_DEFAULT);
	}
	
	public Pencil(int instLeadDurability, int instEraserDurability) {
		this.leadDurability = instLeadDurability;
		this.eraserDurability = instEraserDurability;
	}

	public int getLeadDurability() {
		return leadDurability;
	}

	public void setLeadDurability(int leadDurability) {
		this.leadDurability = leadDurability;
	}

	public int getEraserDurability() {
		return eraserDurability;
	}

	public void setEraserDurability(int eraserDurability) {
		this.eraserDurability = eraserDurability;
	}

	public void sharpen() {
		this.leadDurability = LEAD_DEFAULT;
	}
	
	public void sharpen(int newLeadDurability) {
		this.leadDurability = newLeadDurability;
	}
	
	public String write(String paper, String writing) {
		for (int i = 0; i<writing.length(); i++) {
			char currentChar = writing.charAt(i);
			int leadRequired = 0;
			if (Character.isUpperCase(currentChar) || Character.isDigit(currentChar)) {
				leadRequired = 2;
			} else if (!Character.isWhitespace(currentChar)) {
				//Assume any special characters use the same amount of lead as a lower-case letter
				leadRequired = 1;
			}
			if (this.leadDurability >= leadRequired) {
				paper+=currentChar;
				this.leadDurability-=leadRequired;
			} else {
				break;
			}
		}
		return paper;
	}
	
	public String erase(String paper, String stringToErase) {
		if (paper.contains(stringToErase)) {
			String oldString = stringToErase;
			int eraserRequired = stringToErase.replace(" ", "").length();
			if (this.eraserDurability >= eraserRequired) {
				this.eraserDurability-=eraserRequired;
			} else {
				stringToErase = "";
				for (int i = oldString.length()-1; i>=0 && this.eraserDurability>0; i--) {
					char currentChar = oldString.charAt(i);
					stringToErase = currentChar + stringToErase;
					if (!Character.isWhitespace(currentChar)) {
						this.eraserDurability--;
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
			paper = newPaper;
		}
		return paper;
	}
}
