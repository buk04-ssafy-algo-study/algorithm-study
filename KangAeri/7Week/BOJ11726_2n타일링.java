package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ11726 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=0,i=0;
		int[] dp;
		n = Integer.parseInt(br.readLine());
		dp= new int[1001];
		dp[1]=1;
		dp[2]=2;
		for(i=3; i<=n; i++) {
			dp[i]=dp[i-1]%10007+dp[i-2]%10007;	
		}
		System.out.println(dp[n]%10007);
	}

}
