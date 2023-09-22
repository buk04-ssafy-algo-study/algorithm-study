import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_11726_2xn타일 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long dp[] = new long[1001];
		dp[1] = 1;
		dp[2] = 2;
		int mod = 10_007;
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 3; i <= n; i++) {
//			dp[i] = dp[i-1] % mod  + dp[i - 2] % mod;
			dp[i] = (dp[i-1]  + dp[i - 2])%mod;
		}
		
		System.out.println(dp[n]);
	}
}
