package SSAFY.study.algo.week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_실버1_14716_현수막 {

    static int[] dirRow = new int[] {-1, -1, -1, 1, 1, 1, 0, 0};  // 좌상 상 우상 좌하 하 우하 좌 우
    static int[] dirCol = new int[] {-1, 0, 1, -1, 0, 1, -1, 1};

    static int M, N;
    static int[][] card;
    static boolean[][] isVisited;
    static int count;   // 글자 개수

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (card[i][j] == 1 && !isVisited[i][j]) {
                    bfs(i, j);
                    count += 1;
                }
            }
        }

        System.out.println(count);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        card = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                card[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        isVisited = new boolean[M][N];
        count = 0;
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < M) && (0 <= col && col < N)) return false;

        return true;
    }

    static void bfs(int i, int j) { // 탐색하며 이어진 글자들 방문처리
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {i, j});
        isVisited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowR = now[0];
            int nowC = now[1];

            for (int d = 0; d < 8; d++) {
                int nextR = nowR + dirRow[d];
                int nextC = nowC + dirCol[d];

                if (!isOuted(nextR, nextC) && !isVisited[nextR][nextC] && card[nextR][nextC] == 1) {
                    queue.offer(new int[] {nextR, nextC});
                    isVisited[nextR][nextC] = true;
                }
            }
        }
    }

}
