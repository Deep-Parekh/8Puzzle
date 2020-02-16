import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Random;
/**
 * 
 */
import java.util.Set;

/**
 * @author dparekh
 *
 */
public class PuzzleState {
	
	private static final byte[] GOAL = new byte[] {0,1,2,3,4,5,6,7,8};
	private static final byte STATE_LENGTH = 9;
	private byte[] puzzle;
	private int pathCost;		
	private int totalCost;
	private List<PuzzleState> path;
	
	public PuzzleState() {
		
	};
	
	public PuzzleState(byte[] initState) {
		this.puzzle = initState;
		this.pathCost = 0;
		this.totalCost = 0;
		this.path = new LinkedList<PuzzleState>();
		path.add(this);
	}
	
	public PuzzleState(byte[] initState, int pathCost, int totalCost, List<PuzzleState> path) {
		this.puzzle = initState;
		this.pathCost = pathCost;
		this.totalCost = totalCost;
		this.path = path;
	}
	
	public PuzzleState(PuzzleState parentState) {
		this.puzzle = Arrays.copyOf(parentState.getPuzzle(), STATE_LENGTH);
		this.pathCost = parentState.getPathCost() + 1;
		this.totalCost = 0;
		this.path = new LinkedList(parentState.getPath());
		this.path.add(this);
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
	
	public List<PuzzleState> getPath(){
		return this.path;
	}
	
	public boolean isGoal() {
		return Arrays.equals(this.puzzle, GOAL);
	}
	
	public static boolean isValidState(byte[] curState) {
		int inversions = 0;
		if (curState.length != STATE_LENGTH)
			return false;
		boolean[] duplicates = new boolean[STATE_LENGTH];
		for(int i  = 0; i < STATE_LENGTH; ++i) {
			int b = curState[i];
			if (b > 8 || duplicates[b] == true)
				return false;
			duplicates[b] = true;
		}
		for(int i = 0; i < curState.length; ++i) {
			if(curState[i] == 0)
				++i;
			for(int j = i+1; j < curState.length; ++j) {
				if(curState[j] == 0)
					continue;
				if(curState[j] < curState[i])
					++inversions;
			}
		}
		return (inversions % 2 == 0);
	}
	
	@Override
	public String toString() {
//		String state = "";
//		for(int i = 0; i < STATE_LENGTH; ++i) {
//			if(puzzle[i] == 0)
//				state += " ";
//			else
//				state += puzzle[i];
//			if(i % 3 == 2)
//				state += "\n";
//		}
//		return state;
		return (Arrays.toString(this.puzzle) + "\t" + "Path cost = " + this.pathCost + "\t" + "Total cost = " + this.totalCost);
	}
	
	@Override
	public boolean equals(Object o) {
		if(!o.getClass().equals(this.getClass()))
			return false;
		PuzzleState obj = (PuzzleState) o;
		return Arrays.equals(this.puzzle, obj.getPuzzle());
	}

	/*
	 * Returns a randomly generated state of the puzzle that is valid
	 */
	public static byte[] getRandomValidState() {
		// TODO Auto-generated method stub
		byte[] randomState = getRandomState();
		while(true) {
			if(isValidState(randomState))
				return randomState;
			else
				randomState = getRandomState();
		}
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
	
	
	// TODO
	public List<PuzzleState> getNextStates(){
		// All state related calculations should be done in here
		List<PuzzleState> nextStates = new LinkedList<PuzzleState>();
		int index = findSpace();
		if(index+1%3 != 0 && index+1 < STATE_LENGTH){
			PuzzleState child = new PuzzleState(this);
			child.swap(index, index+1);
			nextStates.add(child);
		}
		if(index-1%3 != 2 && index-1 >= 0) {
			PuzzleState child = new PuzzleState(this);
			child.swap(index, index-1);
			nextStates.add(child);
		}
		if(index+3 < STATE_LENGTH) {
			PuzzleState child = new PuzzleState(this);
			child.swap(index, index+3);
			nextStates.add(child);
		}
		if(index-3 >= 0) {
			PuzzleState child = new PuzzleState(this);
			child.swap(index, index-3);
			nextStates.add(child);
		}
		return nextStates; 
	}
	
	private int findSpace() {
		for (int i = 0; i < STATE_LENGTH; ++i) 
			if(this.puzzle[i] == 0)
				return i;
		return -1;
	}
	
	public void swap(int i, int j) {
		byte temp = this.puzzle[i];
		this.puzzle[i] = this.puzzle[j];
		this.puzzle[j] = temp;
	}

	public int getHammingDistance() {
		int misplaced = 0;
		for(int i = 0; i < 9; ++i) {
			if(this.puzzle[i] == 0)
				continue;
			if(this.puzzle[i] != i)
				misplaced++;
		}
		return misplaced;
	}
	
	// TODO
	public int getManhattanDistance() {
		int distance = 0;
		for(int i  = 0; i < STATE_LENGTH; ++i) {
			if(this.puzzle[i] == 0)
				continue;
			if(this.puzzle[i] != i) {
				distance += getDistance(i/3, i %3, this.puzzle[i]/3, this.puzzle[i]%3);
			}
		}
		return distance;
	}
	
	private int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x2-x1) + Math.abs(y2-y1);
	}
	
	public void solveGraphHamming() {
		System.out.println("Solving puzzle using Graph Search and Hamming Distance as heuristic...");
		System.out.println("Initial State: ");
		PriorityQueue<PuzzleState> frontier = new PriorityQueue<PuzzleState>(new PuzzleComparator());
		Set<PuzzleState> explored = new HashSet<PuzzleState>();
		this.setTotalCost(this.getHammingDistance());
		frontier.add(this);
		int nodesSearched = 0;
		PuzzleState bestState = null;
		while(!frontier.isEmpty()) {
			bestState = frontier.remove();
			//System.out.println(bestState);
			++nodesSearched;
			if(bestState.isGoal()) {
				break;
			}
			explored.add(bestState);
			List<PuzzleState> nextStates = bestState.getNextStates();
			for(PuzzleState state : nextStates)
				state.setTotalCost(state.getPathCost() + state.getHammingDistance());
			nextStates.forEach(state -> {
				if(!explored.contains(state))
					frontier.add(state);
				});
		}
		System.out.println("Reached the goal state: ");
		System.out.print(bestState + "\t");
		System.out.println("Total nodes searched: " + nodesSearched);
		System.out.println("The path was :");
		for(PuzzleState ps : bestState.getPath())
			System.out.println(ps);
	}

	public void solveGraphManhattan() {
		// TODO Auto-generated method stub
		System.out.println("Solving puzzle using Graph Search and Manhattan Distance as heuristic...");
		System.out.println("Initial State: ");
		PriorityQueue<PuzzleState> frontier = new PriorityQueue<PuzzleState>(new PuzzleComparator());
		Set<PuzzleState> explored = new HashSet<PuzzleState>();
		this.setTotalCost(this.getHammingDistance());
		frontier.add(this);
		int nodesSearched = 0;
		PuzzleState bestState = null;
		while(!frontier.isEmpty()) {
			bestState = frontier.remove();
			//System.out.println(bestState);
			++nodesSearched;
			if(bestState.isGoal()) {
				break;
			}
			explored.add(bestState);
			List<PuzzleState> nextStates = bestState.getNextStates();
			for(PuzzleState state : nextStates)
				state.setTotalCost(state.getPathCost() + state.getManhattanDistance());
			nextStates.forEach(state -> {
				if(!explored.contains(state))
					frontier.add(state);
				});
		}
		System.out.println("Reached the goal state: ");
		System.out.print(bestState + "\t");
		System.out.println("Total nodes searched: " + nodesSearched);
		System.out.println("The path was :");
		for(PuzzleState ps : bestState.getPath())
			System.out.println(ps);
	}

	public void solveTreeHamming() {
		System.out.println("Solving puzzle using Tree Search and Hamming Distance as heuristic...");
		System.out.println("Initial State: ");
		PriorityQueue<PuzzleState> frontier = new PriorityQueue<PuzzleState>(new PuzzleComparator());
		frontier.add(this);
		int nodesSearched = 0;
		PuzzleState bestState = null;
		while(!frontier.isEmpty()) {
			bestState = frontier.remove();
			//System.out.println(bestState);
			++nodesSearched;
			if(bestState.isGoal()) {
				break;
			}
			List<PuzzleState> nextStates = bestState.getNextStates();
			for(PuzzleState state : nextStates)
				state.setTotalCost(state.getPathCost() + state.getHammingDistance());
			frontier.addAll(nextStates);
		}
		System.out.println("Reached the goal state: ");
		System.out.print(bestState + "\t");
		System.out.println("Total nodes searched: " + nodesSearched);
		System.out.println("The path was :");
		for(PuzzleState ps : bestState.getPath())
			System.out.println(ps);
	}

	public void solveTreeManhattan() {
		System.out.println("Solving puzzle using Tree Search and Manhattan Distance as heuristic...");
		System.out.println("Initial State: ");
		PriorityQueue<PuzzleState> frontier = new PriorityQueue<PuzzleState>(new PuzzleComparator());
		frontier.add(this);
		int nodesSearched = 0;
		PuzzleState bestState = null;
		while(!frontier.isEmpty()) {
			bestState = frontier.remove();
			//System.out.println(bestState);
			++nodesSearched;
			if(bestState.isGoal()) {
				break;
			}
			List<PuzzleState> nextStates = bestState.getNextStates();
			for(PuzzleState state : nextStates)
				state.setTotalCost(state.getPathCost() + state.getManhattanDistance());
			frontier.addAll(nextStates);
		}
		System.out.println("Reached the goal state: ");
		System.out.print(bestState + "\t");
		System.out.println("Total nodes searched: " + nodesSearched);
		System.out.println("The path was :");
		for(PuzzleState ps : bestState.getPath())
			System.out.println(ps);
	}

	public void solve() {
		solveGraphHamming();
		solveGraphManhattan();
		solveTreeHamming();
		solveTreeManhattan();
	}
	
}
