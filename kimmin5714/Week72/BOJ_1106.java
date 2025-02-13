import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1106 {
    private static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] dp = new int[C + 100 + 1]; // 고객이 i명일 때 최소 비용 저장

        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());

            // 최소 비용 갱신
            for (int j = customer; j < dp.length; j++) {
                dp[j] = Math.min(dp[j], money + dp[j - customer]);
            }
        }

        int res = INF;
        for (int i = C; i < dp.length; i++) { // C명 ~ (C명+100) 중 가장 적은 비용 찾기
            res = Math.min(res, dp[i]);
        }
        System.out.print(res);
    }
}