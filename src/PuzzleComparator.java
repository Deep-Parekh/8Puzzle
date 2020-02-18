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
		int rtn = o1.getTotalCost() - o2.getTotalCost();
		//if(rtn == 0)
		//	return o2.getPathCost() - o1.getPathCost();
		return rtn;
	}

}
