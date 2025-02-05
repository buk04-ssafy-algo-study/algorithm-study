package Week72;

import java.io.*;
import java.util.*;

public class 백준_2616_소형기관차 {

	static int N, M;
	static int[] train, sum;
	static int[][] DP;
	
	public static void main(String[] args) throws IOException {
	
		init();
		solve();
		
	}
	
	private static void solve() {
		
	   for(int j=1; j<=3; j++) { // 사용한 소형 기관차
		   for(int i= j*M; i<=N; i++) { // 앞선 소형 기관차가 끈 객차 이후부터
			   
			   DP[i][j] = Math.max(
					   DP[i-1][j], // 이전 값 유지
					   DP[i-M][j-1] + (sum[i] - sum[i-M])); // 새로운 기관차 하나를 추가하여 i번째 객차까지 포함
			   // 둘 중 더 큰 값으로 갱신
		   }
	   }
	   
	   System.out.println(DP[N][3]);
	}
	
	private static void init() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 객차 수
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		train = new int[N+1];
		sum = new int[N+1];
		
	    DP = new int[N+1][4]; // 
		
		for(int i=1; i<=N; i++) train[i] = Integer.parseInt(st.nextToken()); // 객차마다 승객 수
		for(int i=1; i<=N; i++) sum[i] += sum[i-1] + train[i]; // 객차마다 승객 수 누적합
		
		M = Integer.parseInt(br.readLine()); // 소형 기관차 마다 끌 수 있는 최대 객차
		
	}
}
