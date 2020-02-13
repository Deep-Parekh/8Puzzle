import java.util.Comparator;

/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class PuzzleComparator implements Comparator<EightPuzzle> {

	@Override
	public int compare(EightPuzzle o1, EightPuzzle o2) {
		// TODO Auto-generated method stub
		return o1.getTotalCost() - o2.getTotalCost();
	}

}
