package pencilKata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PencilTest {

	Pencil pencil = new Pencil();
	String paper = "";
	
	@Test
	void testWrite() {
		Pencil pencil2 = new Pencil(25);
		paper = pencil2.write(pencil2, paper, "This is a test. TestING 123! I'm going to run out of lead.");
		assertEquals("This is a test. TestING ", paper);
	}

}
