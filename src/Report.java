import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 
 */

/**
 * @author dparekh
 *
 */
public class Report {

	public static void main(String args[]) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
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

}
