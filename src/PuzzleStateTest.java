import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class PuzzleStateTest {

	@Test
	void testHashCode() {
		byte[] b = new byte[] {1,2,3,4,0,5,6,7,8};
		PuzzleState p1 = new PuzzleState(b);
		p1.setTotalCost(10);
		p1.setPathCost(0);
		PuzzleState p2 = new PuzzleState(b);
		p1.setTotalCost(20);
		p1.setPathCost(19);
		assertEquals(true, p1.hashCode() == p2.hashCode());
	}

	@Test
	void testEqualsObject() {
		byte[] b = new byte[] {1,2,3,4,0,5,6,7,8};
		PuzzleState p1 = new PuzzleState(b);
		p1.setTotalCost(10);
		p1.setPathCost(0);
		PuzzleState p2 = new PuzzleState(b);
		p1.setTotalCost(20);
		p1.setPathCost(19);
		assertEquals(true, p1.equals(p2));
	}
	
	@Test
	void testSet() {
		byte[] b1 = new byte[] {1,2,3,4,0,5,6,7,8};
		PuzzleState p1 = new PuzzleState(b1);
		p1.setTotalCost(10);
		p1.setPathCost(0);
		PuzzleState p2 = new PuzzleState(b1);
		p1.setTotalCost(20);
		p1.setPathCost(19);
		byte[] b2 = new byte[] {1,2,3,0,4,5,6,7,8};
		PuzzleState p3 = new PuzzleState(b2);
		p1.setTotalCost(30);
		p1.setPathCost(20);
		PuzzleState p4 = new PuzzleState(b2);
		p1.setTotalCost(20);
		p1.setPathCost(19);
		HashSet<PuzzleState> set = new HashSet<PuzzleState>();
		set.add(p1);
		set.add(p3);
		assertEquals(false, set.add(p2));
		assertEquals(false, set.add(p4));
	}

}
