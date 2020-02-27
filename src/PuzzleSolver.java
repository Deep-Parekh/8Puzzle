import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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
public class PuzzleSolver {

	public static List<byte[]> getStates(String fileName) {
		List<byte[]> initStates = new LinkedList<byte[]>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			while(line != null) {
				//System.out.println(line);
				if(line.trim().charAt(0) == 'D')
					continue;
				initStates.add(parseByteArray(line));
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return initStates;
	}
	
	private static byte[] parseByteArray(String userInput) {
		byte[] userState = new byte[9];
		userInput = userInput.replaceAll(" ", "");
		if(userInput.length() != 9)
			return null;
		for(int i  = 0; i < userInput.length(); ++i) {
			byte b = Byte.parseByte(userInput.charAt(i) + "");
			userState[i] = b;
		}
		Arrays.toString(userState);
		return userState;
	}
	
//	public static void solveStates(List<byte[]> states, String resultFileName, String reportFileName){
//		long[] values = new long[35];
//		int[] freq = new int[35];
//		double[] time = new double[35];
//		File file = new File(resultFileName);
//		for(byte[] state: states) {
//			PuzzleState puzzle = new PuzzleState(state);
//			puzzle.solveGraph(new Hamming(), values, freq, time);
//		}
//		writeToFile(file, values, freq, time, "Graph + Hamming");
//		values = new long[35];
//		freq = new int[35];
//		time = new double[35];
//		for(byte[] state: states) {
//			PuzzleState puzzle = new PuzzleState(state);
//			puzzle.solveGraph(new Manhattan(), values, freq, time);
//		}
//		writeToFile(file, values, freq, time, "Graph + Manhattan");
//		values = new long[35];
//		freq = new int[35];
//		time = new double[35];
//		for(byte[] state: states) {
//			PuzzleState puzzle = new PuzzleState(state);
//			puzzle.solveTree(new Hamming(), values, freq, time);
//		}
//		writeToFile(file, values, freq, time, "Tree + Hamming");
//		values = new long[35];
//		freq = new int[35];
//		time = new double[35];
//		for(byte[] state: states) {
//			PuzzleState puzzle = new PuzzleState(state);
//			puzzle.solveTree(new Manhattan(), values, freq, time);
//		}
//		writeToFile(file, values, freq, time, "Tree + Manhattan");
//		values = new long[35];
//		freq = new int[35];
//		time = new double[35];
//		try {
//			FileReader reader = new FileReader(file);
//			createReport(reader, reportFileName);
//		} catch (FileNotFoundException e){
//			e.printStackTrace();
//		}
//	}
	
	public static void writeToFile(File file, long[] values, int[] freq, double[] time, String alg) {
		try {
			BufferedWriter fw = new BufferedWriter(new FileWriter(file, true));
			fw.write(alg + "\n");
			fw.write(Arrays.toString(freq)+ "\n");
			fw.write(Arrays.toString(values)+ "\n");
			fw.write(Arrays.toString(time)+ "\n");
			fw.write("\n");
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createReport(FileReader reader, String reportFile) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(reader);
			BufferedWriter bw = new BufferedWriter(new FileWriter(reportFile));
			double[] time = new double[35];
			long[] nodesSearched = new long[35];
			int[] freq = new int[35];
			String line = br.readLine();
			int i = 0;
			while(line != null) {
				if (i % 5 == 1) {
					String[] nums = formatLine(line);
					for(int j = 1; j < nums.length; ++j) {
						freq[j] = Integer.parseInt(nums[j]);
					}
					bw.write("Frequency of Path Cost in Random Sample:\n");
					for(int j = 1; j < freq.length; ++j) {
						bw.write(j + "\t" + freq[j] + "\n");
					}
					bw.newLine();
				}else if (i % 5 == 2) {
					String[] nums = formatLine(line);
					for(int j = 1; j < nums.length; ++j) {
						nodesSearched[j] = Long.parseLong(nums[j]);
					}
					bw.write("Average Nodes Searched:\n");
					for(int j = 1; j < nodesSearched.length; ++j) {
						if(freq[j] == 0)
							continue;
						bw.write(j + "\t" + (long)nodesSearched[j]/freq[j] + "\n");
					}
					bw.newLine();
				}else if (i % 5 == 3) {
					String[] nums = formatLine(line);
					for(int j = 1; j < nums.length; ++j) {
						time[j] = Double.parseDouble(nums[j]);
					}
					bw.write("Average Time Taken:\n");
					for(int j = 1; j < time.length; ++j) {
						if(freq[j] == 0)
							continue;
						bw.write(j + "\t" + (double)time[j]/freq[j] + "\n");
					}
					bw.newLine();
				} else
					bw.write(line + "\n");
				line = br.readLine();
				++i;
				continue;
			}
			bw.close();
			br.close();
			System.out.println("Success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String[] formatLine(String line) {
		line = line.replace('[', ' ');
		line = line.replace(']', ' ');
		line = line.replaceAll(" ", "");
		return line.split(",");
	}
	
	public static byte[] getStateFromUser(Scanner kb) {
		String inputState = "";
		byte[] state;
		while(true) {
			System.out.print("Enter a valid state: ");
			try {
				inputState = kb.nextLine();
				state = parseByteArray(inputState);
				if(PuzzleState.isValidState(state))
					break;
			} catch (Exception e) {
				System.out.println("The state you entered is not valid, try again ");
			}
		}
		return state;
	}
	
	public static void testRandom(int tests) {
		int i = 0;
		byte[] initState = PuzzleState.getRandomValidState();
		while(i < tests) {
			PuzzleState puzzle = new PuzzleState(initState);
			puzzle.solve();
			++i;
			initState = PuzzleState.getRandomValidState();
		}
	} 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		
		if(args.length != 0) { 
			if(args[0].endsWith(".txt")) {
				System.out.println("Reading from File" + args[0]);
				List<byte[]> states = getStates(args[0]);
				//solveStates(states, args[1], args[2]); 
				for(byte[] state : states) {
					PuzzleState puzzle = new PuzzleState(state);
					puzzle.solve();
				}
			}
			else {
			int i = 0;
			byte[] initState = null;
			while(i <= Integer.parseInt(args[0])) {
				 initState = PuzzleState.getRandomValidState();
				 PuzzleState puzzle = new PuzzleState(initState);
				 puzzle.solve();
				 ++i;
				}
			}
			System.exit(0);
		}

		byte[] initState = null;
		while (initState == null) {
			System.out.print("Would you like to enter your own puzzle state? (Y/N)");
			String input = kb.nextLine();
			char in = input.trim().toUpperCase().charAt(0);
			while(input != null) {
				if(in == 'N') {
					initState = PuzzleState.getRandomValidState();
					input = null;
				}
				else if(in == 'Y'){
					initState = getStateFromUser(kb);
					input = null;
				}else {
					System.out.print("Would you like to enter your own puzzle state? (Y/N)");
					input = kb.nextLine();
					in = input.trim().toUpperCase().charAt(0);
				}
			}
		}
		PuzzleState puzzle = new PuzzleState(initState);
		puzzle.solve();
	}

}
