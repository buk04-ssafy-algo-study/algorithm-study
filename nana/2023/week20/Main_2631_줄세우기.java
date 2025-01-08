package study.week20;

import java.util.*;

public class Main_2631_줄세우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] num = new int[N];
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            dp[i] = 1;  // 배열을 모두 1로 초기화
            num[i] = sc.nextInt();
        }

        int len = 0;

        for (int i = 1; i < N; i++) {
            // 현재 칸보다 앞 칸들 중에 작은 수가 있다면 dp 갱신
            for (int j = 0; j < i; j++) {
                if (num[i] > num[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            len = Math.max(len, dp[i]);
        }

        System.out.println(N - len);
    }
}
