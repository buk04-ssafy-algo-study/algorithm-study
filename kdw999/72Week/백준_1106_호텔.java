package Week72;

import java.io.*;
import java.util.*;

public class 백준_1106_호텔 {

	static class Promotion  {
		
		int cost;
		int customer;
		
		public Promotion(int cost, int customer) {
			this.cost = cost;
			this.customer = customer;
		}
 
	}
	
	static int C, N;
	static final int INF = 987654321;
	static int[] DP;
	static Promotion[] arr; 
	
	public static void main(String[] args) throws IOException {
		 
		init();
		solve();
	}
	
	private static void solve() {
		
		// 도시 홍보비용, 고객
		for(int i=0; i<N; i++) {
			
			int cost = arr[i].cost;
			int customer = arr[i].customer;
			 
			
			// DP 채우기, 특정 무게를 정확히 충족하는 게 아니라 특정 무게 이상의 모든 범위를 탐색해줘야함
			for(int j=customer; j<= C+100; j++) {
				DP[j] = Math.min(DP[j], DP[j - customer] + cost);
			}
		}
		
		
		int answer = INF;
		for(int i=C; i<=C+100; i++) answer = Math.min(answer, DP[i]);
		
		System.out.println(answer);
	}
	
	private static void init() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken()); // 목표 인원
		N = Integer.parseInt(st.nextToken()); // 도시 수
		
		DP = new int[C+101];
		arr = new Promotion[N];
		
		Arrays.fill(DP, INF);
		DP[0] = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cost = Integer.parseInt(st.nextToken());
			int customer = Integer.parseInt(st.nextToken());
			
			arr[i] = new Promotion(cost, customer);
		}
	}
}
