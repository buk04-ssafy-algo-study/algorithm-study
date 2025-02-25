package SSAFY.study.algo.week70s.week75;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9084_동전 {

    static int N, M;
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            init(br);
            sb.append(calcPossibleCount()).append("\n");
        }
        System.out.println(sb);
    }

    static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());

        coins = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) coins[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
    }

    static int calcPossibleCount() {
        int[][] caseArr = new int[N+1][M+1]; // N개의 동전으로 만들 수 있는 경우 저장하는 배열
        for (int i = 0; i <= N; i++) caseArr[i][0] = 1; // 0원은 만들 수 있는 경우 무조건 1개이므로 1로 초기화

        for (int i = 1; i <= N; i++) { // 모든 동전에 대해
            for (int j = 1; j <= M; j++) { // 모든 금액을 체크
                if (j >= coins[i-1]) { // 만들려고 하는 금액이 현재 동전보다 큰 경우, 현재 동전 사용
                    caseArr[i][j] = caseArr[i-1][j] + caseArr[i][j-coins[i-1]];
                } else caseArr[i][j] = caseArr[i-1][j]; // 아닌 경우 해당 동전 이전 동전으로 만든 경우의 수를 저장
            }
        }

        return caseArr[N][M];
    }

}
