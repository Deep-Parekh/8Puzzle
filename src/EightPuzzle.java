import java.util.Arrays;
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
	
	public EightPuzzle() {
		
	};
	
	public EightPuzzle(byte[] initState) {
		this.puzzle = initState;
	}
	
	public static boolean isGoal(byte[] curState) {
		return Arrays.equals(curState, GOAL);
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
	
	public void solveGraphHamming() {
		// TODO Auto-generated method stub
		
	}

	public void solveGraphManhattan() {
		// TODO Auto-generated method stub
		
	}

	public void solveTreeHamming() {
		// TODO Auto-generated method stub
		
	}

	public void solveTreeManhattan() {
		// TODO Auto-generated method stub
		
	}
}
