import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[][] table = new int[2][N+2];

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            table[0][i] = t;
            table[1][i] = p;
        }

        int[] dp = new int[N+2];

        int max = -1;
        for(int i=1;i<=N+1;i++) {
            max = (max<dp[i])?dp[i]:max;

            int next = i+table[0][i];
            int cost = table[1][i];

            if(next>N+1) continue;

            dp[next] = Math.max(dp[next], max+cost);
        }
        System.out.println(dp[N+1]);
    }
}
