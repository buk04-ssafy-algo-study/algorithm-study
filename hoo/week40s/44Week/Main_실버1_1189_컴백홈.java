package SSAFY.study.algo.week44;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_실버1_1189_컴백홈 {

    static int[] dirRow = new int[] {-1, 1, 0, 0};  // 상 하 좌 우
    static int[] dirCol = new int[] {0, 0, -1, 1};

    static class Hansu {
        int row;
        int col;
        int dist;

        public Hansu(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    static int R, C, K;
    static String[][] map;
    static int answer;

    public static void main(String[] args) throws IOException {
        init();
        boolean[][] isVisited = new boolean[R][C];
        isVisited[R-1][0] = true;
        dfs(new Hansu(R-1, 0, 1), isVisited);
        System.out.println(answer);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new String[R][C];

        String inputLine;
        for (int i = 0; i < R; i++) {
            inputLine = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = String.valueOf(inputLine.charAt(j));
            }
        }
        answer = 0;
    }

    static void dfs(Hansu nowHansu, boolean[][] isVisited) {
        if (nowHansu.row == 0 && nowHansu.col == C-1) { // 기저, 한수가 집에 도착한 경우
            if (nowHansu.dist == K) answer += 1;
            return;
        }

        int nextRow, nextCol;
        for (int d = 0; d < 4; d++) {
            nextRow = nowHansu.row + dirRow[d];
            nextCol = nowHansu.col + dirCol[d];
            if (isOuted(nextRow, nextCol) || isVisited[nextRow][nextCol] || map[nextRow][nextCol].equals("T")) continue;    // 갈 수 없는 경우는 건너 뜀
            isVisited[nextRow][nextCol] = true;
            dfs(new Hansu(nextRow, nextCol, nowHansu.dist + 1), isVisited);
            isVisited[nextRow][nextCol] = false;
        }
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < R) && (0 <= col && col < C)) return false;
        return true;
    }

}
