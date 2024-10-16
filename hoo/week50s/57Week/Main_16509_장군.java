package SSAFY.study.algo.week50s.week57;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16509_장군 {

    static int[][] board;   // 10x9 크기 장기판
    static int R1, C1, R2, C2;  // 1: 상, 2: 왕

    public static void main(String[] args) throws IOException {
        init();
        doKoreanChess();
    }

    static void init() throws IOException {
        board = new int[10][9];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R1 = Integer.parseInt(st.nextToken());
        C1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        R2 = Integer.parseInt(st.nextToken());
        C2 = Integer.parseInt(st.nextToken());
    }

    static void doKoreanChess() {
        int[] dirRow = new int[] {-1, -1, -1, 0, 1, 1, 1, 0}; // 좌상 상 우상 우 우하 하 좌하 좌
        int[] dirCol = new int[] {-1, 0, 1, 1, 1, 0, -1, -1};
        int[][] dirSang = new int[][] {{1, 0, 0}, {1, 2, 2}, {3, 2, 2}, {3, 4, 4}, {5, 4, 4}, {5, 6, 6}, {7, 6, 6}, {7, 0, 0}}; // 상이 진행하는 방향

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {R1, C1, 0});
        boolean[][] isVisited = new boolean[10][9];
        isVisited[R1][C1] = true;

        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();
            if (now[0] == R2 && now[1] == C2) { // 상이 왕을 잡은 경우
                System.out.println(now[2]);
                return;
            }

            int nextRow, nextCol;
            boolean isBlocked;
            for (int ds = 0; ds < 8; ds++) {    // 상이 이동할 수 있는 8가지 경로에 대해
                isBlocked = false;
                nextRow = now[0];
                nextCol = now[1];
                for (int d = 0; d < 3; d++) {   // 그 경로로 3칸을 이동
                    nextRow += dirRow[dirSang[ds][d]];
                    nextCol += dirCol[dirSang[ds][d]];
                    if (d != 2 && nextRow == R2 && nextCol == C2) isBlocked = true; // 이동 중간에 왕을 만나면 그 길은 막힌 길임
                }
                if (isBlocked || isOuted(nextRow, nextCol) || isVisited[nextRow][nextCol]) continue;    // 길이 막혔거나 범위 밖으로 나가거나 이미 방문한 곳이면 건너 뜀
                q.offer(new int[] {nextRow, nextCol, now[2]+1});
                isVisited[nextRow][nextCol] = true;
            }
        }

        System.out.println(-1);
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < 10) && (0 <= col && col < 9)) return false;

        return true;
    }

}
