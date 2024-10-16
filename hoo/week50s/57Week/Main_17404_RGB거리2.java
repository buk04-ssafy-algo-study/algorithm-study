package SSAFY.study.algo.week50s.week57;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17404_RGB거리2 {

    static int N;
    static int[][] houses;

    public static void main(String[] args) throws IOException {
        init();
        calcMinCost();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        houses = new int[N][3];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) houses[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void calcMinCost() {
        int INF = 1_000 * 1_000;
        int minCost = INF;
        int[][] dpArr;
        for (int i = 0; i < 3; i++) {   // 첫 번째 집 R, G, B인 경우로 나눠서 계산
            dpArr = new int[N][3];
            dpArr[0][i] = houses[0][i];
            for (int j = 0; j < 3; j++) if (i != j) dpArr[0][j] = INF;    // 선택된 색깔 아니면 INF로 초기화

            dpArr = calcCost(dpArr);
            dpArr[N-1][i] = INF;    // 1번 집과 N번 집은 같은 색으로 칠하면 안됨.
            for (int j = 0; j < 3; j++) minCost = Math.min(minCost, dpArr[N-1][j]);
        }
        System.out.println(minCost);
    }

    static int[][] calcCost(int[][] dpArr) {
        for (int i = 1; i < N; i++) {
            dpArr[i][0] = Math.min(dpArr[i-1][1], dpArr[i-1][2]) + houses[i][0];
            dpArr[i][1] = Math.min(dpArr[i-1][0], dpArr[i-1][2]) + houses[i][1];
            dpArr[i][2] = Math.min(dpArr[i-1][0], dpArr[i-1][1]) + houses[i][2];
        }

        return dpArr;
    }

}
