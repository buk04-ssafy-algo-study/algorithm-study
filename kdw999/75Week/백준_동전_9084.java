package Week75;

import java.io.*;
import java.util.*;

public class 백준_동전_9084 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] coin = new int[N];

			for(int i=0; i<N; i++) coin[i] = Integer.parseInt(st.nextToken());
		
			int M = Integer.parseInt(br.readLine());
			int[] DP = new int[M+1]; // 목표 금액 M원까지
			
			DP[0] = 1; // 0원이 되는 경우 초기값 설정
			
			// 가진 동전 전부
		    for(int i=0; i<N; i++) {
		    	for(int j=coin[i]; j<=M; j++) {
		    		DP[j] += DP[j - coin[i]];
//		    		System.out.println("DP["+j+"] += DP["+j+" - coin["+i+"]] / " + DP[j] + " / " + DP[j-coin[i]]);
		    	}
		    }
		    
		    sb.append(DP[M]+"\n");
		}

		System.out.println(sb);
	}
}
