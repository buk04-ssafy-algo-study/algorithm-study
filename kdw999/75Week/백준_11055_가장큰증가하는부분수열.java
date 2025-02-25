package Week75;

import java.io.*;
import java.util.*;

public class 백준_11055_가장큰증가하는부분수열 {

	static int N;
	static int[] A, sum;
	public static void main(String[] args) throws IOException {

		init();
		solve();
	}
	
	private static void solve() {
		
		
		// 0부터 배열 끝까지 탐색하면서 증가하는 부분 수열 찾기
		for(int i=0; i<A.length; i++) {
		 
			for(int j=0; j<i; j++) {
				
				if (A[j] < A[i]) { // 증가하는 부분 수열인 경우
					
					// 앞선 탐색으로 합해놓은 sum을 활용
                    sum[i] = Math.max(sum[i], sum[j] + A[i]);
                }
			}
		}
		
		Arrays.sort(sum);
		System.out.println(sum[sum.length-1]);
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = new int[N];
		sum = new int[N];
		
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			sum[i] = A[i]; // 자기 자신 값 초기화
		}
	}
}
