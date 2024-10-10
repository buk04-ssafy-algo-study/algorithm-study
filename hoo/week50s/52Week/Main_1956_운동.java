package SSAFY.study.algo.week52;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1956_운동 {

    static int V, E;
    static int INF = 10_000 * 800 * 1;
    static int[][] distArr;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(findMinDistCycle());
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        distArr = new int[V+1][V+1];
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) distArr[i][j] = INF;   // 원래는 자기 자신으로 가는 값은 0이어야 하는데, 여기서는 싸이클 거리 판별할 거라 처리 안함
        }
        int from, to, dist;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());
            distArr[from][to] = Math.min(distArr[from][to], dist);
        }
    }

    static int findMinDistCycle() {
        for (int k = 1; k <= V; k++) {  // 경유지
            for (int i = 1; i <= V; i++) {  // 출발지
                for (int j = 1; j <= V; j++) {  // 도착지
                    distArr[i][j] = Math.min(distArr[i][j], distArr[i][k] + distArr[k][j]);
                }
            }
        }
        int minDist = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) minDist = Math.min(minDist, distArr[i][i]);

        return (minDist != INF)? minDist:-1;
    }

}
