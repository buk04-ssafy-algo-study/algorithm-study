import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 동전 종류 수
        int k = sc.nextInt(); // 목표 금액

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        int[] dp = new int[k + 1];  // k 원을 만들기 위해 필요한 최소 동전 수
        Arrays.fill(dp, 10001); // 1 ≤ k ≤ 10,000
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);  // 동전 수 vs 현재 coin을 하나 추가한 것
            }
        }

        System.out.println(dp[k] == 10001 ? -1 : dp[k]);
    }
}
