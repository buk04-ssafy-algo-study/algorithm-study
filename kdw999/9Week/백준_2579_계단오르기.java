package Week9;

import java.util.*;
import java.io.*;

public class 백준_2579_계단오르기 {

	static int[] dp;
	static int[] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 계단 수
		
		dp = new int[N+1];
		num = new int[N+1];
		
		for(int i=1; i<=N; i++) num[i] = Integer.parseInt(br.readLine());
			
		// 초기값
	    dp[1] = num[1];
	    
	    // dp[2]는 1,2 합한게 더 크니까
	    if(N >= 2) dp[2] = num[1] + num[2]; 
	    
	    // 현재 계단에 도달할 수 있는 경우는 2칸 이동해온 경우 -> dp[i-2]
	    // 2칸 이동후 1칸 이동해온 경우 [연속 3칸을 밟지 않아야함] -> dp[i-3] + num[i-1]
	    for(int i=3; i<= N ; i++) {
	    	dp[i] = Math.max(dp[i-2], dp[i-3] + num[i-1]) + num[i];
	    }
	    
	    System.out.println(dp[N]);
	}
}
