package SSAFY.study.algo.week53;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_18430_무기공학 {

    static int[][] dirRow = new int[][] {{0, 1}, {-1, 0}, {-1, 0}, {0, 1}};    // 문제에서 준 순서대로 부메랑 네 방향
    static int[][] dirCol = new int[][] {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static int N, M;
    static int[][] wood;
    static int maxStrength;

    public static void main(String[] args) throws IOException {
        init();
        dfs(/*0, 0, */0, 0, new boolean[N][M]);
        System.out.println(maxStrength);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wood = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                wood[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        maxStrength = 0;
    }

    static void dfs(/*int row, int col, */int idx, int strength, boolean[][] isUsed) {
//        if (row == N-1 && col == M-1) { // 기저, 모든 칸을 다 체크해봤을 경우
        if (idx == N*M) {
            maxStrength = Math.max(maxStrength, strength);
            return;
        }

        int row = idx / M;
        int col = idx % M;
//        int nextCenterRow = (col+1 == M)? row+1:row;
//        int nextCenterCol = (col+1 == M)? 0:col+1;
        if (!isUsed[row][col]) {    // 이번 칸 안썼으면 여기서 부메랑 만드는 경우도 고려해봄
            int sideRow1, sideCol1, sideRow2, sideCol2, addStrength;
            for (int d = 0; d < 4; d++) {   // 부메랑을 만들 수 있는 네 방향에 대해서 탐색
                sideRow1 = row + dirRow[d][0];
                sideCol1 = col + dirCol[d][0];
                sideRow2 = row + dirRow[d][1];
                sideCol2 = col + dirCol[d][1];
                if (isOuted(sideRow1, sideCol1) || isOuted(sideRow2, sideCol2) || isUsed[sideRow1][sideCol1] || isUsed[sideRow2][sideCol2]) continue;   // 이 모양으로는 부메랑 만들기 불가
                addStrength = wood[row][col] * 2 + wood[sideRow1][sideCol1] + wood[sideRow2][sideCol2];
                isUsed[row][col] = true;
                isUsed[sideRow1][sideCol1] = true;
                isUsed[sideRow2][sideCol2] = true;
                dfs(/*nextCenterRow, nextCenterCol,*/idx+1, strength+addStrength, isUsed);   // 부메랑 만들고 다음 칸으로
                isUsed[row][col] = false;
                isUsed[sideRow1][sideCol1] = false;
                isUsed[sideRow2][sideCol2] = false;
            }
        }
        dfs(/*nextCenterRow, nextCenterCol,*/idx+1, strength, isUsed);       // 부메랑을 안만들고 다음칸으로 넘어가는 경우
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < M)) return false;
        return true;
    }

}
