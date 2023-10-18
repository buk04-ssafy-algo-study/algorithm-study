package study.week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14500_테트로미노 {
    private static int N, M, result;
    private static int[][] paper;
    private static boolean[][] isVisited;
    private static int[][] delta = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    private static void dfs(int x, int y, int sum, int count) {

        if (count == 4) {
            result = Math.max(result, sum);
            return;
        }

        for (int[] d : delta) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;
            if (isVisited[nx][ny])
                continue;

            if (count == 2) {
                // ㅏ, ㅓ, ㅗ, ㅜ 모양의 경우를 검사해야한다
                isVisited[nx][ny] = true;
                dfs(x, y, sum + paper[nx][ny], count + 1);
                isVisited[nx][ny] = false;
            }

            isVisited[nx][ny] = true;
            dfs(nx, ny, sum + paper[nx][ny], count + 1);
            isVisited[nx][ny] = false;

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N][M];
        isVisited = new boolean[N][M];
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                isVisited[i][j] = true;
                dfs(i, j, paper[i][j], 1);
                isVisited[i][j] = false;
            }
        }

        System.out.println(result);
    }
}