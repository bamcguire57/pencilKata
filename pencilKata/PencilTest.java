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
		Pencil pencil = new Pencil(25);
		paper = pencil.write(pencil, paper, "This is a test. TestING 123! I'm going to run out of lead.");
		assertEquals("This is a test. TestING ", paper);
	}
	
	@Test
	void testSharpenPencil() {
		Pencil pencil = new Pencil(40);
		pencil = pencil.sharpenPencil(pencil);
		assertEquals(pencil.leadDurability, 500);
	}
	
	@Test
	void testSharpenPencilWithProvidedValue() {
		Pencil pencil = new Pencil(60);
		pencil = pencil.sharpenPencil(pencil, 400);
		assertEquals(pencil.leadDurability, 400);
	}
	
	@Test
	void testErase() {
		paper = pencil.write(pencil, paper, "this is a test.");
		paper = pencil.erase(paper, "test");
		assertEquals("this is a     .", paper);
	}
	
	@Test
	void testEraseWithMultipleInstancesOfTheSameWord() {
		paper = pencil.write(pencil, paper, "this is a test. testtest tes");
		paper = pencil.erase(paper, "test");
		assertEquals("this is a test. test     tes", paper);
		paper = pencil.erase(paper, "test");
		assertEquals("this is a test.          tes", paper);
	}
}
