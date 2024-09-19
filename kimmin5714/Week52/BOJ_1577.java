import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1577 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        long[][] dp = new long[N + 1][M + 1]; // 경우의 수
        boolean[][] horizontal = new boolean[N+1][M+1]; // 행(가로) 공사 여부
        boolean[][] vertical = new boolean[N+1][M+1]; // 열(세로) 공사 여부

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());

            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            if(c1==c2) // 같은 열
                horizontal[Math.min(r1,r2)][c1] = true; // 더 작은 행에 체크
            else // 같은 행
                vertical[r1][Math.min(c1,c2)] = true; // 더 작은 열에 체크
        }

        for(int i=1;i<=M;i++){ // 행은 0으로 고정, 가장자리 열 초기화
            if(vertical[0][i-1]) break;
            dp[0][i] = 1L;
        }
        for(int i=1;i<=N;i++){ // 열은 0으로 고정, 가장자리 행 초기화
            if(horizontal[i-1][0]) break;
            dp[i][0] = 1L;
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                dp[i][j] = dp[i][j-1] + dp[i-1][j]; // 경우의 수 계산

                if(horizontal[i-1][j]) dp[i][j] -= dp[i-1][j]; // 행 공사 확인
                if(vertical[i][j-1]) dp[i][j] -= dp[i][j-1]; // 열 공사 확인
            }
        }
        System.out.print(dp[N][M]);
    }
}
