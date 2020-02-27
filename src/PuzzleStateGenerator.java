import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class PuzzleStateGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File stateFile = new File("/Users/dparekh/Desktop/Spring_20/AI/Projects/Parekh_Deep_P1/bin/TestFiles/Unique5000.txt");
		try{
			if(stateFile.createNewFile()) {
				System.out.println("Generating random states");
				generateStates(stateFile);
			}
			generateStates(stateFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void generateStates(File file) throws IOException{
		FileWriter fw = new FileWriter(file);
		String bytes = "";
		byte[] state = null;
		int i = 0;
		HashSet<PuzzleState> states = new HashSet<PuzzleState>();
		while(i < 5000) {
			state = PuzzleState.getRandomValidState();
			PuzzleState ps = new PuzzleState(state);
			if(states.contains(ps))
				continue;
			states.add(ps);
			System.out.println(Arrays.toString(state));
			bytes = Arrays.toString(state);
			bytes = bytes.substring(1, bytes.length()-1);
			bytes = bytes.replaceAll(",", "");
			fw.write(bytes +"\n");
			++i;
		}
		fw.close();
		System.out.println("Closed file.");
	}
}
