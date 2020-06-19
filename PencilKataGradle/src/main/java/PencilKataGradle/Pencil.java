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
		this.sharpen(LEAD_DEFAULT);
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
		return paper.contains(stringToErase) ? getNewPaper(paper, stringToErase, getErasableString(stringToErase)) : paper;
	}

	private String getNewPaper(String paper, String stringToErase, String erasableString) {
		String whitespace = " ".repeat(erasableString.length());
		int beginningOfLastInstance = paper.lastIndexOf(stringToErase);
		beginningOfLastInstance += stringToErase.length() - whitespace.length();
		String charsBeforeErasedSection = paper.substring(0, beginningOfLastInstance);
		String charsAfterErasedSection = paper.substring(beginningOfLastInstance+whitespace.length());
		return new String(charsBeforeErasedSection + whitespace + charsAfterErasedSection);
	}

	private String getErasableString(String oldString) {
		String erasableString = "";
		int eraserRequired = oldString.replace(" ", "").length();
		if (this.eraserDurability >= eraserRequired) {
			erasableString = new String(oldString);
			this.eraserDurability -= eraserRequired;
		} else {
			for (int i = oldString.length()-1; i>=0 && this.eraserDurability>0; i--) {
				char currentChar = oldString.charAt(i);
				erasableString = currentChar + erasableString;
				if (!Character.isWhitespace(currentChar)) {
					this.eraserDurability--;
				}
			}
		}
		return erasableString;
	}
}
