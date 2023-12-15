package study.week19;

import java.io.*;
import java.util.*;

public class Main_14716_현수막 {

    private static int M, N, res;
    private static int[][] board;
    private static boolean[][] isVisited;
    private static int[][] delta = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    private static void dfs(int x, int y) {

        isVisited[x][y] = true;

        for (int[] d : delta) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
            if (isVisited[nx][ny] || board[nx][ny] == 0) continue;

            dfs(nx, ny);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new int[M][N];
        isVisited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1 && !isVisited[i][j]) {
                    dfs(i, j);
                    res++;
                }
            }
        }

        System.out.println(res);
    }
}
