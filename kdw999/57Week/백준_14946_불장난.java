package Week57;

import java.io.*;
import java.util.*;

public class 백준_14946_불장난 {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        
        int[][] dp = new int[N+1][N+1];
        dp[2][1] = 2;
        
        for (int i = 3; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = (dp[i-1][j] * 2 + dp[i-1][j-1] + dp[i-1][j+1]) % 10_007;
            }
        }
        
        for (int i = 1; i < N; i++) {
        	result = (result + dp[N][i]) % 10_007;
        }
        
        System.out.println(result % 10_007);
    }
}