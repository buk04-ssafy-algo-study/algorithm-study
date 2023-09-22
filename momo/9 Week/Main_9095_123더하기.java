import java.io.BufferedReader;
import java.io.InputStreamReader;


/* 
 * 솔직히 왜 맞는지 모르겠음 동우가 dfs로 풀린다고 해서 대충 dfs 짜봤는데 맞음;;
 */

public class Main_9095_123더하기 {
	static int n;
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < t; i++) {
			n = Integer.parseInt(br.readLine());
			cnt = 0;
			dfs(0);
			sb.append(cnt+ "\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int sum) {
		if(sum == n) {
			cnt++;
			return;
		}
		if(sum > n) {
			return;
		}
		
		
		for (int i = 1; i <= 3; i++) {
			dfs(sum + i);
		}
	}
}
