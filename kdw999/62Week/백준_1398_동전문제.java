package Week62;

import java.util.*;
import java.io.*;

// 초콜릿 가격 범위 1000조
// 10단위 초콜릿 1, 10, 100, 1000...
// 25단위 초콜릿 25, 2500, 250000...

public class 백준_1398_동전문제 {
	
	static int[] coins = {25, 10};
	static long[] dp = new long[100];
	
	public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   
	   for(int i=1; i<100; i++) {
		   dp[i] = i; // 1원으로 필요한 동전 갯수 초기화
	   }
	   
	   for(int coin : coins) {
		   for(int j=coin; j<100; j++) {
			   dp[j] = Math.min(dp[j], dp[j - coin]+1);  // 필요 최소 동전 갯수 계산
		   }
	   }
	   
       int T = Integer.parseInt(br.readLine());
       
       for(int t=1; t<=T; t++) {

    	   long chocoCost = Long.parseLong(br.readLine());
    	   long result=0;
    	   
    	   while(chocoCost > 0) {
    		   result += dp[(int) (chocoCost % 100)];
    		   chocoCost = chocoCost / 100;
    	   }
    	   
    	   System.out.println(result);
       }
	}
}
