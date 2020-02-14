import java.util.Comparator;

/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class PuzzleComparator implements Comparator<PuzzleState> {

	@Override
	public int compare(PuzzleState o1, PuzzleState o2) {
		
		return o1.getTotalCost() - o2.getTotalCost();
	}

}
