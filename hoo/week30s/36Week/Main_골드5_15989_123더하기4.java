package SSAFY.study.algo.week36;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_골드5_15989_123더하기4 {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n;
        int[] dp = new int[10001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            for (int i = 4; i < n+1; i++) {
                dp[i] = dp[i-3] + i/2 + 1;
            }
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb);
    }

}
