package SSAFY.study.algo.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_골드4_14500_테트로미노 {

    static int[] dirRow = {-1, 1, 0, 0};	// 상 하 좌 우
    static int[] dirCol = {0, 0, -1, 1};

    static int answer;	// 최대값 나올 때마다 갱신시키기
    static int N, M;
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        init();
        boolean[][] isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                isVisited[i][j] = true;
                dfs(i, j, isVisited, 0, 0, 0, 0);
                checkF(i, j);
                isVisited[i][j] = false;
            }
        }
        System.out.println(answer);
    }

    static void init() throws IOException {
        answer = Integer.MIN_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < M)) return false;

        return true;
    }

    static void dfs(int row, int col, boolean[][] isVisited, int depth, int sum, int rowDiff, int colDiff) {	// 테트로미노 5번 경우 잘 고려하기(깊이 우선 탐색이므로)
        if (depth == 4) {	// 4개 골랐으면 최대값 비교
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextR = row + dirRow[i];
            int nextC = col + dirCol[i];
            if (!isOuted(nextR, nextC) && !isVisited[nextR][nextC]) {
                isVisited[nextR][nextC] = true;
                dfs(nextR, nextC, isVisited, depth+1, sum+paper[nextR][nextC], Math.abs(row-nextR), Math.abs(col-nextC));
                isVisited[nextR][nextC] = false;
            }
        }
    }

    static void checkF(int row, int col) {
        int center = paper[row][col];	// 테트로미노 5번 중심 좌표의 값
        // 1번 ㅗ모양 체크
        if (!isOuted(row-1, col) && !isOuted(row, col-1) && !isOuted(row, col+1)) answer = Math.max(answer, center + paper[row-1][col] + paper[row][col-1] + paper[row][col+1]);
        // 2번 ㅏ 모양 체크
        if (!isOuted(row+1, col) && !isOuted(row, col+1) && !isOuted(row-1, col)) answer = Math.max(answer, center + paper[row+1][col] + paper[row][col+1] + paper[row-1][col]);
        // 3번 ㅜ 모양 체크
        if (!isOuted(row+1, col) && !isOuted(row, col-1) && !isOuted(row, col+1)) answer = Math.max(answer, center + paper[row+1][col] + paper[row][col-1] + paper[row][col+1]);
        // 4번 ㅓ 모양 체크
        if (!isOuted(row, col-1) && !isOuted(row-1, col) && !isOuted(row+1, col)) answer = Math.max(answer, center + paper[row][col-1] + paper[row-1][col] + paper[row+1][col]);
    }

}
