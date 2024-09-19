// 1퍼센트에서 안돌아가는 코드.. 반례를 못찾겠어요
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class helpBOJ_1577 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        long[][] dp = new long[N+1][M+1];
        for(int i=0;i<=N;i++)
            Arrays.fill(dp[i], -1L);
        dp[0][0] = 1L;

        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());

            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            construction(dp,r1,c1,r2,c2);
        }

        for(int i=1;i<=N;i++) {
            if(dp[i][0] == -1 || dp[i][0] == -2)
                dp[i][0] = dp[i - 1][0];
            else if(dp[i][0] == -3 || dp[i][0] == -4)
                dp[i][0] = 0;
        }
        for(int j=1;j<=M;j++) {
            if(dp[0][j] == -1 || dp[0][j] == -3)
                dp[0][j] = dp[0][j - 1];
            else if(dp[0][j] == -2 || dp[0][j] == -4)
                dp[0][j] = 0;
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++) {
                if(dp[i][j] == -1)
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                else if(dp[i][j] == -2)
                    dp[i][j] = dp[i-1][j];
                else if(dp[i][j] == -3)
                    dp[i][j] = dp[i][j-1];
                else if(dp[i][j] == -4)
                    dp[i][j] = 0;
            }
        }
        System.out.print(dp[N][M]);
    }

    private static void construction(long[][] dp, int r1, int c1, int r2, int c2) {
        if(Math.abs(r1-r2)>0){
            if(r1>r2) {
                if(dp[r1][c1] == -2)
                    dp[r1][c1] = -4;
                else
                    dp[r1][c1] = -3;
            }
            else if (r1<r2) {
                if(dp[r2][c2] == -2)
                    dp[r2][c2] = -4;
                else
                    dp[r2][c2] = -3;
            }
        }
        else {
            if(c1>c2) {
                if(dp[r1][c1] == -3)
                    dp[r1][c1] = -4;
                else
                    dp[r1][c1] = -2;
            }
            else if (c1<c2) {
                if(dp[r2][c2] == -2)
                    dp[r2][c2] = -4;
                else
                    dp[r2][c2] = -2;
            }
        }
    }
}
