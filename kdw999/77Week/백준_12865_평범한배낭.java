package Week77;

import java.io.*;
import java.util.*;

public class 백준_12865_평범한배낭 {
	
	static int N, K;
	static int[] DP;
	static int[] W, V;
	
	public static void main(String[] args) throws IOException {
		init();
		solve();
	}
	
	private static void solve() {
		
		for(int i=1; i<=N; i++) {
			for (int j = K; j >= W[i]; j--) {
				
				DP[j] = Math.max(DP[j], DP[j - W[i]] + V[i]);
				
			}
		}
		
		System.out.println(DP[K]);
	}
	
	private static void init () throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		DP = new int[K+1];
		W = new int[N+1];
		V = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
			
		}
		
	}
}
