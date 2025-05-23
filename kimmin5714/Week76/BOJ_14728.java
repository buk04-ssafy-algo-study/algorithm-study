import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[] dp = new int[T+1];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            for(int j=T;j>=K;j--) { // 과목 한 번씩만 공부하므로 뒤에서부터
                dp[j] = Math.max(dp[j],dp[j-K]+S);
            }
        }
        System.out.print(dp[T]);
    }
}
