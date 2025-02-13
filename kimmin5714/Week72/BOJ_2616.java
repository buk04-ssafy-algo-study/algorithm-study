import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2616 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] sum = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sum[i] += sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int[][] dp = new int[4][N + 1]; // i번째 기관차가 (j-K+1)~(j)번째 객차 가져갈 때 최대 손님 수

        for (int i = 1; i <= 3; i++) {
            for (int j = i * K; j <= N; j++) { // 3대 모두 사용하므로 최소 i*K번째부터

                // 1. i번째 기차가 j-1번째 객차까지 가져갈 때 손님 수
                // 2. i-1번째 기차가 j-K번째 객차까지 가져갈 때 손님 수 + i번째 객차가 j번째 객차까지 가져갈 때 손님 수
                // 둘 중 더 큰 값을 저장
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - K] + (sum[j] - sum[j - K]));
            }
        }
        System.out.print(dp[3][N]);
    }
}
