package pencilKata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PencilTest {

	Pencil pencil = new Pencil();
	String paper = "";
	
	@Test
	void testWrite() {
		paper = pencil.write(paper, "this is a test.");
		assertEquals("this is a test.", paper);
	}

}
