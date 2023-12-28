package Week20;

import java.util.*;
import java.io.*;

public class 백준_2631_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(br.readLine());
		
		// 최장 증가 수열, 연속으로 커지는 수 찾기
		// 연속으로 커지는 수 중 최댓값은 이동하지 않아도 되는 최대의 수
		for(int i=0; i<N; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(arr[i] > arr[j]) {
					if(dp[i] < dp[j] + 1) dp[i] = dp[j]+1;
				}
			}
		}
		
		Arrays.sort(dp);
		System.out.println(N-dp[N-1]);
	}

}
