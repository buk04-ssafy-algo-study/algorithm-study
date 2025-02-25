import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2240 {
    private static int res, T, W, fallingInfo[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        fallingInfo = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            fallingInfo[i] = Integer.parseInt(br.readLine());
        }

        res = 0; // 받을 수 있는 자두 최대 갯수

        int[][][] dp = new int[3][T + 1][W + 1];

        if (fallingInfo[1] == 1) {
            dp[1][1][0]++;
        } else {
            dp[2][1][1]++;
        }

        for (int i = 2; i <= T; i++) {
            if (fallingInfo[i] == 1) { // i초에 자두가 1번 나무에서 떨어질 때
                // 안움직였을 때,
                dp[1][i][0] = dp[1][i - 1][0] + 1; // 1번에 있을 때
                dp[2][i][0] = dp[2][i - 1][0]; // 2번에 있을 때

                // 1~W 회 움직였을 때,
                for (int w = 1; w <= W; w++) {
                    dp[1][i][w] = Math.max(dp[1][i - 1][w], dp[2][i - 1][w - 1]) + 1; // 1번에 있을 떄
                    dp[2][i][w] = Math.max(dp[2][i - 1][w], dp[1][i - 1][w - 1]); // 2번에 있을 때
                }
            } else { // i초에 자두가 2번 나무에서 떨어질 때
                // 안움직였을 때,
                dp[1][i][0] = dp[1][i - 1][0]; // 1번에 있을 때
                dp[2][i][0] = dp[2][i - 1][0] + 1; // 2번에 있을 때

                // 1~W 회 움직였을 때,
                for (int w = 1; w <= W; w++) {
                    dp[1][i][w] = Math.max(dp[1][i - 1][w], dp[2][i - 1][w - 1]); // 1번에 있을 때
                    dp[2][i][w] = Math.max(dp[2][i - 1][w], dp[1][i - 1][w - 1]) + 1; // 2번에 있을 때
                }
            }
        }

        for (int w = 0; w <= W; w++) {
            res = Math.max(res, Math.max(dp[1][T][w], dp[2][T][w]));
        }
        System.out.print(res);
    }
}
