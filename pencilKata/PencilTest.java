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
	
	@Test
	void testLeadDegredation() {
		Pencil pencil2 = new Pencil(25);
		String paper = "";
		paper = pencil2.write(pencil2, paper, "This is a test. TestING 123! I'm going to run out of lead.");
		assertEquals("This is a test. TestING ", paper);
	}
	
	@Test
	void testSharpenPencil() {
		Pencil pencil3 = new Pencil(40);
		pencil3 = pencil3.sharpenPencil(pencil3);
		assertEquals(pencil3.leadDurability, 500);
	}
	
	@Test
	void testSharpenPencilWithProvidedValue() {
		Pencil pencil3 = new Pencil(60);
		pencil3 = pencil3.sharpenPencil(pencil3, 400);
		assertEquals(pencil3.leadDurability, 400);
	}
}
