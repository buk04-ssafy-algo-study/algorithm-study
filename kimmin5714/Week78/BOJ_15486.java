import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] dp = new int[N+2]; // N일까지 일했을 때 최대 수익 N+1에 저장

        int max = 0;
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            max = Math.max(max, dp[i]); // 이전까지 최댓값 유지

            if(i + t <= N + 1) {
                dp[i + t] = Math.max(dp[i + t], max + p); // 기존 i+t일 수익, 지금까지 최댓값+현재 수익 비교해서 최댓값 저장
            }
        }
        max = Math.max(max, dp[N+1]);
        System.out.println(max);
    }
}