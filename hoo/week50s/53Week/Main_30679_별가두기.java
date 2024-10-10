package SSAFY.study.algo.week53;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_30679_별가두기 {

    static int[] dirRow = new int[] {0, 1, 0, -1};  // 90도씩 회전하는 방향으로 우, 하, 좌, 상
    static int[] dirCol = new int[] {1, 0, -1, 0};

    static int N, M;
    static int[][] map;
    static PriorityQueue<Integer> closeRows;    // 시작했을 때 가둘 수 있는 칸

    public static void main(String[] args) throws IOException {
        init();
        closeRows = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {   // 각 행에 대해서 수행
            boolean[][][] isVisited = new boolean[N][M][4];
            isVisited[i][0][0] = true;
            dfs(i, i, 0, 0, isVisited);
            isVisited[i][0][0] = false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(closeRows.size()).append("\n");
        while (!closeRows.isEmpty()) sb.append(closeRows.poll()+1).append(" ");
        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void dfs(int startRow, int row, int col, int dir, boolean[][][] isVisited) {
        int nextRow = row + dirRow[dir] * map[row][col];
        int nextCol = col + dirCol[dir] * map[row][col];
        if (isOuted(nextRow, nextCol)) return;    // 이동한 곳이 범위 밖이면 가두는 데 실패이므로 종료
        int nextDir = (dir + 1) % 4;
        if (isVisited[nextRow][nextCol][nextDir]) {  // 기저, 해당 칸에서 dir을 바라본 적이 있다면 별은 갇힌 것임
            closeRows.offer(startRow);
            return;
        }
        isVisited[nextRow][nextCol][nextDir] = true;
        dfs(startRow, nextRow, nextCol, nextDir, isVisited);
        isVisited[nextRow][nextCol][nextDir] = false;
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < M)) return false;
        return true;
    }

}
