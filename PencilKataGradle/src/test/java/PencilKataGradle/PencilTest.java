package PencilKataGradle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PencilTest {

	Pencil pencil = new Pencil();
	String paper = "";
	
	@Test
	public void testWrite() {
		paper = pencil.write(paper, "this is a test.");
		assertEquals("this is a test.", paper);
	}
	
	@Test
	public void testLeadDegredation() {
		Pencil pencil = new Pencil(25);
		paper = pencil.write(paper, "This is a test. TestING 123! I'm going to run out of lead.");
		System.out.print(paper);
		assertEquals("This is a test. TestING ", paper);
	}
	
	@Test
	public void testSharpenPencil() {
		Pencil pencil = new Pencil(40);
		pencil.sharpen();
		assertEquals(pencil.getLeadDurability(), 500);
	}
	
	@Test
	public void testSharpenPencilWithProvidedValue() {
		Pencil pencil = new Pencil(60);
		pencil.sharpen(400);
		assertEquals(pencil.getLeadDurability(), 400);
	}
	
	@Test
	public void testErase() {
		paper = pencil.write(paper, "this is a test.");
		paper = pencil.erase(paper, "test");
		assertEquals("this is a     .", paper);
	}
	
	@Test
	public void testEraseWithMultipleInstancesOfTheSameWord() {
		paper = pencil.write(paper, "this is a test. testtest tes");
		paper = pencil.erase(paper, "test");
		assertEquals("this is a test. test     tes", paper);
		paper = pencil.erase(paper, "test");
		assertEquals("this is a test.          tes", paper);
	}
	
	@Test
	public void testEraseUsingDegradation() {
		Pencil pencil = new Pencil(500, 5);
		paper = pencil.write(paper, "this is a test. this is a test. this is a test.");
		paper = pencil.erase(paper, "test. th");
		assertEquals("this is a test. this is a te      is is a test.", paper);
	}
	
	@Test
	public void testEraseUsingDegradationAndMultipleInstancesOfTheSameWord() {
		Pencil pencil = new Pencil(500, 10);
		paper = pencil.write(paper, "this is a test. this is a test. this is a test.");
		paper = pencil.erase(paper, "test. th");
		assertEquals("this is a test. this is a         is is a test.", paper);
		paper = pencil.erase(paper, "test. th");
		assertEquals("this is a test    is is a         is is a test.", paper);
	}
}
