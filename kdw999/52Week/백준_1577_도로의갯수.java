package Week52;

import java.util.*;
import java.io.*;


public class 백준_1577_도로의갯수 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[][] dp = new long[N+1][M+1];
		
		int[][] horizontal = new int[N+1][M+1];
		int[][] vertical = new int[N+1][M+1];
		
		int K = Integer.parseInt(br.readLine());
		
		for(int i=0; i<K; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			if(b == d) horizontal[Math.min(a, c)][b] = 1;
			else vertical[a][Math.min(b, d)] = 1;
			
		}
		
		for(int i=1; i<N+1; i++) {
			if(horizontal[i-1][0] == 1) break;
			dp[i][0] = 1L;
		}
		
		for(int i=1; i<M+1; i++) {
			if(vertical[0][i-1] == 1) break;
			dp[0][i] = 1L;
		}
		
		for(int i=1; i<dp.length; i++) {
			for(int j=1; j<dp[0].length; j++) {
				
				dp[i][j] = dp[i][j-1] + dp[i-1][j];
				
				// 공사중이면 가짓수 빼기
				if(horizontal[i-1][j] == 1) dp[i][j] -= dp[i-1][j];
				if(vertical[i][j-1] == 1) dp[i][j] -= dp[i][j-1];
			}
		}
		
		System.out.println(dp[N][M]);
	}
}
