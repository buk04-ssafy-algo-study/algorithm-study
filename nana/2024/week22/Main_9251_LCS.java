package study.week22;

import java.io.*;

public class Main_9251_LCS {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int len1 = str1.length();
        int len2 = str2.length();

        char[] arr1 = new char[len1 + 1];
        char[] arr2 = new char[len2 + 1];

        for (int i = 1; i <= len1; i++) {
            arr1[i] = str1.charAt(i - 1);
        }
        for (int i = 1; i <= len2; i++) {
            arr2[i] = str2.charAt(i - 1);
        }

        int[][] dp = new int[len2 + 1][len1 + 1];

        for (int i = 1; i <= len2; i++) {
            for (int j = 1; j <= len1; j++) {

                if (arr2[i] == arr1[j]) {
                    // 같은 문자인 경우 대각선 값을 기준으로 +1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 다른 문자인 경우 위 또는 왼쪽 수 중 큰 값
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[len2][len1]);
    }
}
