/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class Hamming implements HeuristicInterface {

	@Override
	public int getHeuristic(byte[] puzzleState) {
		int misplaced = 0;
		for(int i = 0; i < 9; ++i) {
			if(puzzleState[i] == 0)
				continue;
			if(puzzleState[i] != i)
				misplaced++;
		}
		return misplaced;
	}

}
