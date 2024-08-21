package SSAFY.study.algo.week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드3_11562_백양로브레이크 {

    static int INF = 999999999; // 9억9천만...

    static int n, m;
    static int[][] linkInfo;  // 연결 정보 저장할 배열
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        floydWarshall();
        solve(br);
    }

    static void init(BufferedReader br) throws IOException { // 길 정보까지만 입력 받음
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        linkInfo = new int[n+1][n+1];
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                if (i != j) linkInfo[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int isTwoWay = Integer.parseInt(st.nextToken());
            linkInfo[from][to] = 0; // 직접 연결된 경우, 여기서는 비용 처리가 아닌 양방향 처리를 해야 하나(0), 처리를 안해도 되나(1) 여부를 저장
            if (isTwoWay == 0) linkInfo[to][from] = 1;  // 단방향일 경우, 양방향 처리를 해야 하므로 1
            else if (isTwoWay == 1) linkInfo[to][from] = 0; // 양방향일 경우, 처리를 안해도 되므로 0
        }
    }

    static void floydWarshall() {
        for (int k = 1; k < n+1; k++) {
            for (int i = 1; i < n+1; i++) {
                for (int j = 1; j < n+1; j++) linkInfo[i][j] = Math.min(linkInfo[i][j], linkInfo[i][k] + linkInfo[k][j]);
            }
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int questionCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < questionCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            if (start == destination) { // 출발지 도착지 같으면 바로 출력 후 종료
                sb.append(0).append("\n");
                continue;
            }
            sb.append(linkInfo[start][destination]).append("\n");
        }
        System.out.println(sb);
    }

}
