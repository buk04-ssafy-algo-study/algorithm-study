import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int N = sc.nextInt();   // N가지 동전
            int[] coins = new int[N];

            for (int i = 0; i < N; i++) {
                coins[i] = sc.nextInt();
            }

            int M = sc.nextInt();   // 만들어야 하는 수

            int[] dp = new int[M + 1];
            dp[0] = 1;  // 0원을 만드는 경우의 수 = 하나도 사용하지 않는 경우 (1)

            for (int coin : coins) {
                for (int i = 1; i <= M; i++) {
                    // 예를 들어, coins = {1, 5} 라면
                    // coin = 1 일 때, dp[5] = 0 -> dp[5] += dp[4] -> dp[5] = 1
                    // coin = 5 일 때, dp[5] = 1 -> dp[5] += dp[0] -> dp[5] = 2
                    if (i - coin >= 0)
                        dp[i] += dp[i - coin];
                }
            }

            sb.append(dp[M]).append("\n");
        }

        System.out.println(sb);
    }
}
