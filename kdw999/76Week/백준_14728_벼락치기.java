package Week76;

import java.io.*;
import java.util.*;

public class 백준_14728_벼락치기 {
	
	static int N, T;
	static int[] DP, K, S;
	public static void main(String[] args) throws IOException {
	
		init();
		solve();
	}
	
	private static void solve() {
		
		for(int i=1; i<=N; i++) {
			for(int j=T; j>=K[i]; j--) {
			    DP[j] = Math.max(DP[j], DP[j - K[i]] + S[i]);
			}
		}
		System.out.println(DP[T]);
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		DP = new int[10001];
		K = new int[N+1];
		S = new int[N+1];
		
		DP[0] = 0;
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int k = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			K[i] = k;
			S[i] = s;
		}
	}
}
