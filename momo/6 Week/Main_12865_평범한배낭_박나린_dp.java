package 스터디_6주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class Main_12865_평범한배낭_박나린_dp {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken()); // 무게
			arr[i][1] = Integer.parseInt(st.nextToken()); // 가중치
		}
		
		int[][] dp = new int[N+1][K+1]; // N : 물품을 나타냄 , K : 무게를 나타냄
		for (int i = 1; i <= N; i++) {
			int weight = arr[i][0];
			int value = arr[i][1];
			for (int j = 1; j <= K; j++) {
				if(weight <= j)
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight] + value);
				else 
					dp[i][j] = dp[i-1][j];
			}
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if(dp[i][j] > max) max = dp[i][j];
			}
		}
		System.out.println(max);
	}
}










