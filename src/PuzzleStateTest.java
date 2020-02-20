import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author dparekh
 *
 */
class PuzzleStateTest {

	
	PuzzleState state = new PuzzleState(new byte[] {1,2,3,4,0,5,6,7,8});
	@Test 
	void nextState() {
		List<PuzzleState> next = new LinkedList<PuzzleState>();
		next.add(new PuzzleState(new PuzzleState(new byte[] {1,2,3,0,4,5,6,7,8})));
		next.add(new PuzzleState(new PuzzleState(new byte[] {1,2,3,4,5,0,6,7,8})));
		next.add(new PuzzleState(new PuzzleState(new byte[] {1,0,3,4,2,5,6,7,8})));
		next.add(new PuzzleState(new PuzzleState(new byte[] {1,2,3,4,7,5,6,0,8})));
		assertEquals(next, state.getNextStates());
	}

}
