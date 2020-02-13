import java.util.Scanner;

/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class PuzzlaSolver {
	
	public static byte[] createByteArray(String userInput) {
		byte[] userState = new byte[9];
		userInput = userInput.trim();
		int j = 0;
		for(int i  = 0; i < userInput.length(); ++i) {
			char c = userInput.charAt(i);
			if (c != ' ') {
				byte b = Byte.parseByte(userInput.charAt(i) + "");
				userState[j++] = (byte) c;
				if (j >= 9)
					break;
			}
		}
		return userState;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		System.out.print("Would you like to enter your own puzzle state? (Y/N)");
		String input = kb.nextLine();
		byte[] initState;
		if(input.trim().toUpperCase().charAt(0) == 'N')
			initState = EightPuzzle.getRandomValidState();
		else {
			String inputState = "";
			while(true) {
				System.out.print("Enter a valid state: ");
				inputState = kb.nextLine();
				initState = createByteArray(inputState);
				if(EightPuzzle.isValidState(initState))
					break;
			}
		}
		
		EightPuzzle puzzle = new EightPuzzle(initState);
		puzzle.solveGraphHamming();
		puzzle.solveGraphManhattan();
		puzzle.solveTreeHamming();
		puzzle.solveTreeManhattan();
	}

}
