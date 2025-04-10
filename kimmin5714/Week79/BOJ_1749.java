import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1749 {
    private static int N, M, dp[][],res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N+1][M+1];
        res = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // (1,1)~(i,j) 누적합 저장
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=M;j++) {
                dp[i][j] = dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1]+dp[i][j];
            }
        }

        // (a,b)~(c,d) 합 계산
        for(int a=1;a<=N;a++) {
            for(int b=1;b<=M;b++) {
                for(int c=a;c<=N;c++) {
                    for(int d=b;d<=M;d++) {
                        res = Math.max(res, dp[c][d]-dp[c][b-1]-dp[a-1][d]+dp[a-1][b-1]);
                    }
                }
            }
        }

        System.out.println(res);
    }
}
