package SSAFY.study.algo.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_실버1_14940_쉬운최단거리 {

    static int[] dirRow = {-1, 1, 0, 0};    // 상, 하, 좌, 우
    static int[] dirCol = {0, 0, -1, 1};

    static class Moving {   // 움직이는 것을 저장하기 위한 객체
        int row;
        int col;
        int moves;

        public Moving(int row, int col, int moves) {
            this.row = row;
            this.col = col;
            this.moves = moves;
        }

        @Override
        public String toString() {
            return this.row + " " + this.col + " " + this.moves;
        }
    }

    static int n, m;
    static int[][] land;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        Moving moving = init();
        move(moving);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {   // 출력하기
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !isVisited[i][j]) sb.append(-1).append(" "); // 원래 갈 수 있는 땅인데(마지막에 1인 곳), 도달하지 못한 경우(visited가 false인 경우)는 -1로 출력
                else sb.append(land[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static Moving init() throws IOException {
        Moving moving = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        land = new int[n][m];
        isVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int input = Integer.parseInt(st.nextToken());
                land[i][j] = input;
                if (input == 2) {
                    land[i][j] = 0;
                    moving = new Moving(i, j, 0);   // 목표 지점 저장
                    isVisited[i][j] = true;
                }
            }
        }

        return moving;
    }

    static boolean isOutedOrBlocked(int nextRow, int nextCol) { // 범위 밖으로 가거나, 0인 곳인지 체크
        if ( ((0 <= nextRow && nextRow < n) && (0 <= nextCol && nextCol < m)) && land[nextRow][nextCol] != 0 ) return false;

        return true;
    }

    static void move(Moving moving) {    // 목표지점부터 bfs를 통해 최단거리 구하기
        Queue<Moving> queue = new ArrayDeque<>();
        queue.offer(moving);

        while (!queue.isEmpty()) {
            Moving now = queue.poll();
            int nowMoves = now.moves;
            for (int r = 0; r < 4; r++) {
                int nextRow = now.row + dirRow[r];
                int nextCol = now.col + dirCol[r];
                if (isOutedOrBlocked(nextRow, nextCol) || isVisited[nextRow][nextCol]) continue;
                land[nextRow][nextCol] = nowMoves + 1;
                queue.offer(new Moving(nextRow, nextCol, nowMoves+1));
                isVisited[nextRow][nextCol] = true; // 방문 처리
            }
        }
    }

}
