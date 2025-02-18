import java.util.*;
import java.io.*;

class Main {

    private static int N, K;    // N: 전체 객차의 수, K: 소형기관차가 끌 수 있는 최대 객차 수
    private static int[] train;
    private static int[] prefixSum; // 누적합: 1~n번째 객차까지 손님의 합
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        train = new int[N + 1];
        prefixSum = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            train[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + train[i];
        }

        K = Integer.parseInt(br.readLine());

        dp = new int[N + 1][4];

        for (int i = 1; i <= 3; i++) {
            for (int j = K; j <= N; j++) {
                dp[j][i] = Math.max(
                        dp[j - 1][i], // i대의 기차를 이용, j번째 객차를 끌지 않았을 때
                        // 마지막 기관차가 K대의 객차를 끄는 경우
                        dp[j - K][i - 1] + prefixSum[j] - prefixSum[j - K]
                );
            }
        }

        System.out.println(dp[N][3]);
    }
}
