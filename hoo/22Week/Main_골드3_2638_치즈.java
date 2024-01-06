package SSAFY.study.algo.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_골드3_2638_치즈 {

    static int[] dirRow = new int[] {-1, 1, 0, 0};
    static int[] dirCol = new int[] {0, 0, -1, 1};
    static int N, M;
    static int[][] cheese;
    static int melted;  // 녹은 개수 카운트, 0개가 되면 다 녹은 것
    static int hour;    // 걸린 시간 카운트

    public static void main(String[] args) throws IOException {
        init();
        while (melted != 0) {
            bfs();
            hour += 1;
        }
        System.out.println(hour);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheese = new int[N][M];
        melted = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info == 1) melted += 1;
                cheese[i][j] = info;
            }
        }
        hour = 0;
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < M)) return false;

        return true;
    }

    private static void cheeseMelt(int[][] contactInfo) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (contactInfo[i][j] < 2) continue;
                cheese[i][j] = 0;
                melted -= 1;
            }
        }
    }

    static void bfs() {
        int[][] contactInfo = new int[N][M]; // "외부" 공기와 치즈가 몇 번 맞닿았는지 체크할 배열
        boolean[][] isVisited = new boolean[N][M];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});  // 항상 시작은 맨 왼쪽 위부터
        isVisited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] nowAxis = queue.poll();

            for (int d = 0; d < 4; d++) {   // 4방에 대해
                int nextRow = nowAxis[0] + dirRow[d];
                int nextCol = nowAxis[1] + dirCol[d];
                if (isOuted(nextRow, nextCol)) continue;
                // 1. 치즈에 접촉시키기
                if (cheese[nextRow][nextCol] == 1) contactInfo[nextRow][nextCol] += 1;

                // 2. 다음 외부 공기 큐에 넣기
                if (isVisited[nextRow][nextCol] || cheese[nextRow][nextCol] != 0) continue;
                queue.offer(new int[]{nextRow, nextCol});
                isVisited[nextRow][nextCol] = true;
            }
        }

        cheeseMelt(contactInfo);
    }

}
