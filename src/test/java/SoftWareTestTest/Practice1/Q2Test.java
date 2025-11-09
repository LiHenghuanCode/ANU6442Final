package SoftWareTestTest.Practice1;

import static org.junit.Assert.*;

import java.util.Arrays;

import SoftwareTest.Practice1.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;


/**
 * A parameterized test class for HashingBoxes.
 */
@RunWith(Parameterized.class)
public class Q2Test {
	/**
	 * Return a list of parameters which are different implementation of
	 * abstract {@linkplain HashingBox}.
	 *
	 * Do NOT Modify this part.
	 */
	@Parameters
	public static Iterable<HashingBox> getImplementations() {
		return Arrays.asList(
				new HashingBox0(),
				new HashingBox1(),
				new HashingBox2(),
				new HashingBox3(),
				new HashingBox4(),
				new HashingBox5(),
				new HashingBox6(),
				new HashingBox7(),
				new HashingBox8(),
				new HashingBox9(),
				new HashingBox10(),
				new HashingBox11(),
				new HashingBox12()
		);
	}

	@Parameter
	public HashingBox hashingBox;


	// TODO: ########## YOUR CODE STARTS HERE ##########
	// TODO: You are encouraged to write multiple test methods for different scenarios.
	// You do not need to write one test case per assertion, but some edge cases (e.g., when an Exception happens)
	// may preferably be better
	// ---------------- Helper for expected result ----------------
	private int expectedHash(Thing t) {
		int size = t.getSize();
		int sizeHash = (size <= 1) ? 2 : largestPrime(size);

		int lettersTotal = 0;
		for (int i = 0; i < t.getName().length() && i < 6; i++) {
			char c = t.getName().charAt(i);
			if (Character.isLetter(c)) lettersTotal += c;
		}

		int nameHash = sizeHash * lettersTotal + t.getName().length();
		int materialShapeHash = t.getMaterial().getValue() * t.getShape().getValue();
		return nameHash + materialShapeHash;
	}

	private int largestPrime(int n) {
		for (int i = n; i >= 2; i--) {
			boolean prime = true;
			for (int j = 2; j * j <= i; j++) {
				if (i % j == 0) { prime = false; break; }
			}
			if (prime) return i;
		}
		return 2;
	}

	// ---------------- Actual Tests ----------------
	@Test(timeout = 1000)
	public void testExampleCase() throws ComponentOutOfRangeException {
		Thing t = new Thing(Material.SILVER, 3, Shape.SQUARE, "Comp2100");
		assertEquals(expectedHash(t), hashingBox.hash(t));
	}

	@Test(timeout = 1000)
	public void testSizeEdgeLow() throws ComponentOutOfRangeException {
		Thing t = new Thing(Material.GOLD, 1, Shape.CIRCLE, "Aaa");
		assertEquals(expectedHash(t), hashingBox.hash(t));
	}

	@Test(timeout = 1000)
	public void testSizeEdgeHigh() throws ComponentOutOfRangeException {
		Thing t = new Thing(Material.STEEL, 31, Shape.PENTAGON, "Goldie");
		assertEquals(expectedHash(t), hashingBox.hash(t));
	}

	@Test(timeout = 1000)
	public void testNameLengthShort() throws ComponentOutOfRangeException {
		Thing t = new Thing(Material.SILVER, 7, Shape.TRIANGLE, "Ab1!");
		assertEquals(expectedHash(t), hashingBox.hash(t));
	}

	@Test(timeout = 1000)
	public void testNameLengthLong() throws ComponentOutOfRangeException {
		Thing t = new Thing(Material.GOLD, 10, Shape.SEMICIRCLE, "abcdefghiJKL");
		assertEquals(expectedHash(t), hashingBox.hash(t));
	}

	@Test(timeout = 1000)
	public void testThrowException() {
		Thing t = new Thing(Material.GOLD, 40, Shape.SQUARE, "Test");
		try {
			hashingBox.hash(t);
			fail("Expected ComponentOutOfRangeException not thrown");
		} catch (ComponentOutOfRangeException e) {
			// expected
		} catch (Exception e) {
			fail("Unexpected exception type: " + e);
		}
	}


	// TODO ########## YOUR CODE ENDS HERE ##########

	/**
	 * This is an example test case for references about the SYNTAX of tests only.
	 * If you believe that the assertion written is WRONG, leave the test commented.
	 * Otherwise, you may uncomment the test to have it as a part of your solutions.
	 *
	 * -  This test is for reference only. i.e., when writing your own tests,
	 *    you do not need to follow the syntax from this example.
	 */
//	@Test(timeout = 1000)
//	public void exampleTestCase() throws ComponentOutOfRangeException {
//		// This is a test that uses the example from the README.
//		Thing thing = new Thing(Material.SILVER, 3, Shape.SQUARE, "Comp2100");
//		int expectedHash = 4905;
//		int actualHash = hashingBox.hash(thing);
//		assertEquals(expectedHash, actualHash);
//	}

}
