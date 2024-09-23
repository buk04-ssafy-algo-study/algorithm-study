import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_18427 {
    static int N, M, H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        List<Integer>[] list = new ArrayList[N+1]; // 학생마다 가진 블록
        for(int i=1;i<=N;i++) {
            list[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            while(st.countTokens()!=0) // 다음 토큰이 없을 때까지
                list[i].add(Integer.parseInt(st.nextToken()));
        }

        int[][] dp = new int[N+1][H+1]; // 경우의 수 저장

        for(int i=1;i<=N;i++){
            for(int j=1;j<=H;j++) { // j 높이 기준
                for(int height : list[i]) {
                    if (height == j) dp[i][j]++; // 현재 학생이 가지고 있는 j높이 블럭 개수
                    if (height < j) dp[i][j] += dp[i - 1][j - height]; // 현재 학생이 가진 블록 중에서 j보다 작은 블록을 선택했을 때, 이전 학생이 나머지 높이를 가진 경우
                }
                dp[i][j] += dp[i-1][j]; // 이전 학생(i-1)이 가지고 있는 j높이 블럭 개수
                dp[i][j] %= 10007; // 나머지 계산
                // 계산 도중에 모듈러 연산을 하지 않으면 값이 매우 커져서 최종 결과가 잘못될 수 있음
            }
        }
        System.out.print(dp[N][H]);
    }
}
