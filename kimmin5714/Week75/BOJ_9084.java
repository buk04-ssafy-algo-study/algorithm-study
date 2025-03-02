import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1];
            dp[0] = 1;  // 0원 만드는 방법은 1가지

            // 각 동전을 하나씩 처리
            for (int i = 0; i < N; i++) {
                for (int j = arr[i]; j <= M; j++) {
                    dp[j] += dp[j - arr[i]];  // 현재 동전을 사용할 경우
                }
            }

            sb.append(dp[M] + "\n");
        }
        System.out.print(sb);
    }
}
