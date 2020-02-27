import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author dparekh
 *
 */
class ManhattanTest {

	/**
	 * Test method for {@link Manhattan#getHeuristic(byte[])}.
	 */
	@Test
	void testGetHeuristic() {
		Manhattan m = new Manhattan();
		byte[] puzzleState = new byte[] {8,7,6,5,4,3,2,1,0};
		assertEquals(20, m.getHeuristic(puzzleState));
		puzzleState = new byte[] {0,1,2,3,4,5,6,7,8};
		assertEquals(0, m.getHeuristic(puzzleState));
	}

}
