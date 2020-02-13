import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Random;
/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class EightPuzzle {
	
	private static final byte[] GOAL = new byte[] {0,1,2,3,4,5,6,7,8};
	private static final byte STATE_LENGTH = 9;
	private byte[] puzzle;
	private int pathCost;
	private int totalCost;
	private List<EightPuzzle> path;
	
	public EightPuzzle() {
		
	};
	
	public EightPuzzle(byte[] initState) {
		this.puzzle = initState;
		this.pathCost = 0;
		this.totalCost = 0;
		this.path = new LinkedList<EightPuzzle>();
		path.add(this);
	}
	
	public EightPuzzle(byte[] initState, int pathCost, int totalCost, List<EightPuzzle> path) {
		this.puzzle = initState;
		this.pathCost = pathCost;
		this.totalCost = totalCost;
		this.path = path;
	}
	
	public byte[] getPuzzle() {
		return this.puzzle;
	}
	
	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}
	
	public int getPathCost() {
		return this.pathCost;
	}
	
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	
	public int getTotalCost() {
		return this.totalCost;
	}
	
	public List<EightPuzzle> getPath(){
		return this.getPath();
	}
	
	public static boolean isGoal(EightPuzzle curState) {
		return Arrays.equals(curState.getPuzzle(), GOAL);
	}
	
	public static boolean isValidState(byte[] curState) {
		int inversions = 0;
		if (curState.length != STATE_LENGTH)
			return false;
		for(int i = 0; i < curState.length; ++i) {
			for(int j = i+1; j < curState.length; ++j) {
				if(curState[j] < curState[i])
					++inversions;
			}
		}
		return (inversions % 2 == 0);
	}
	
	@Override
	public String toString() {
		String state = "";
		for(int i = 0; i < STATE_LENGTH; ++i) {
			if(puzzle[i] == 0)
				state += " ";
			else
				state += puzzle[i];
			if(i % 3 == 2)
				state += "\n";
		}
		return state;
	}

	/*
	 * Returns a randomly generated state of the puzzle that is valid
	 */
	public static byte[] getRandomValidState() {
		// TODO Auto-generated method stub
		byte[] randomState = getRandomState();
		while(!isValidState(randomState)) {
			randomState = getRandomState();
		}
		return randomState;
	}

	public static byte[] getRandomState() {
		byte[] randomState = new byte[STATE_LENGTH];
		Arrays.fill(randomState, (byte)-1);
		boolean[] values = new boolean[STATE_LENGTH];
		Random generator = new Random();
		for(int i = 0; i < STATE_LENGTH; ++i) {
			byte b = (byte) generator.nextInt(STATE_LENGTH);
			while(randomState[i] == -1) {
				if(!values[b]) {
					randomState[i] = b;
					values[b] = true;
				}
				else
					b = (byte) generator.nextInt(STATE_LENGTH);
			}
		}
		return randomState;
	}
	
	public List<EightPuzzle> getNextStates(){
		return null;
	}
	
	public void solveGraphHamming() {
		// TODO Auto-generated method stub
		
	}

	public void solveGraphManhattan() {
		// TODO Auto-generated method stub
		
	}

	public void solveTreeHamming() {
		// TODO Auto-generated method stub
		PriorityQueue<EightPuzzle> frontier = new PriorityQueue<EightPuzzle>(new PuzzleComparator());
		frontier.add(this);
		while(!frontier.isEmpty()) {
			EightPuzzle bestState = frontier.remove();
			if(isGoal(bestState)) {
				break;
			}
			List<EightPuzzle> nextStates = bestState.getNextStates();
			
		}
	}

	public void solveTreeManhattan() {
		// TODO Auto-generated method stub
		
	}

	public void solve() {
		solveGraphHamming();
		solveGraphManhattan();
		solveTreeHamming();
		solveTreeManhattan();
	}
	
}
