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
}
