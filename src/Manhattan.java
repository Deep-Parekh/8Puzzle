/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class Manhattan implements HeuristicInterface {

	@Override
	public int getHeuristic(byte[] puzzleState) {
		int distance = 0;
		for(int i  = 0; i < PuzzleState.STATE_LENGTH; ++i) {
			if(puzzleState[i] == 0)
				continue;
			if(puzzleState[i] != i) {
				distance += getDistance(i/3, i %3, puzzleState[i]/3, puzzleState[i]%3);
			}
		}
		return distance;
	}

	private int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x2-x1) + Math.abs(y2-y1);
	}
}
