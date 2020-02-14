import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class PuzzlaSolver {
	
	public static byte[] parseByteArray(String userInput) {
		byte[] userState = new byte[9];
		userInput = userInput.replaceAll(" ", "");
		if(userInput.length() != 9)
			return null;
		for(int i  = 0; i < userInput.length(); ++i) {
			byte b = Byte.parseByte(userInput.charAt(i) + "");
			userState[i] = b;
		}
		return userState;
	}
	
	
	public static List<byte[]> getStates(String fileName) {
		BufferedReader br = null;
		List<byte[]> initStates = new LinkedList<byte[]>();
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			while(line != null) {
				initStates.add(parseByteArray(line));
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		return initStates;
	}
	
	public static void solveStates(List<byte[]> states) {
		for(byte[] state: states) {
			EightPuzzle puzzle = new EightPuzzle(state);
			puzzle.solve();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		/*
		 * if(args[0].endsWith(".txt")) { List<byte[]> states = getStates(args[0]);
		 * solveStates(states); System.exit(0); }
		 */
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
				initState = parseByteArray(inputState);
				if(EightPuzzle.isValidState(initState))
					break;
			}
		}
		
		EightPuzzle puzzle = new EightPuzzle(initState);
		puzzle.solve();
	}

}
