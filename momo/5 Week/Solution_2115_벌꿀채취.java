import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_2115_벌꿀채취 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case < T; test_case++) {
			sb.append("#" + test_case + " ");
			
			String[] split = br.readLine().split(" ");
			int N = Integer.parseInt(split[0]);
			int M = Integer.parseInt(split[1]);
			int C = Integer.parseInt(split[2]);
			
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < M; i++) {
				
			}
		}
	}
}
