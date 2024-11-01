package SSAFY.study.algo.week50s.week60;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2206_벽부수고이동하기 {

    static class Player {
        int row;
        int col;
        int dist;   // 움직인 거리
        boolean broke;  // 벽 부쉈는 지 여부, 0은 안부심 1은 부심

        public Player(int row, int col, int dist, boolean broke) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.broke = broke;
        }
    }

    static int N, M;
    static int[][] map;
    static int shortestDist;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
        }
        shortestDist = N*M + 1;
    }

    static void bfs() {
        Queue<Player> q = new ArrayDeque<>();
        boolean[][][] isVisited = new boolean[N][M][2]; // 0: 벽 안부순 경우, 1: 벽 부순 경우
        q.offer(new Player(0, 0, 0, false));
        isVisited[0][0][0] = true;;

        int[] dirRow = new int[] {-1, 0, 1, 0}; // 상 우 하 좌
        int[] dirCol = new int[] {0, 1, 0, -1};
        Player now;
        int INF = N*M + 1;
        int answer = INF;
        while (!q.isEmpty()) {
            now = q.poll();
            if (now.row == N-1 && now.col == M-1) { // 도착점 도달했을 경우
                answer = Math.min(answer, now.dist);
                continue;
            }

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now.row + dirRow[d];
                nextCol = now.col + dirCol[d];
                if (isOuted(nextRow, nextCol)) continue;    // 범위 밖이면 건너 뜀
                if (isVisited[nextRow][nextCol][now.broke? 1:0]) continue;    // 아무 경우나 이미 방문했다면 건너 뜀
                if (map[nextRow][nextCol] == 1) {   // 다음 칸이 벽인 경우
                    if (now.broke) continue;    // 벽 이미 한 번 부순 경우는 건너 뜀
                    q.offer(new Player(nextRow, nextCol, now.dist+1, true));
                    isVisited[nextRow][nextCol][1] = true;
                } else {    // 다음 칸 벽 아닌 경우
                    q.offer(new Player(nextRow, nextCol, now.dist+1, now.broke));
                    isVisited[nextRow][nextCol][now.broke? 1:0] = true;
                }
            }
        }

        System.out.println((answer == INF)? -1:answer+1);
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < M)) return false;
        return true;
    }

}
