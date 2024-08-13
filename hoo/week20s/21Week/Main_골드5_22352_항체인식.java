package SSAFY.study.algo.week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_골드5_22352_항체인식 {

    static int[] dirRow = new int[] {-1, 1, 0, 0};
    static int[] dirCol = new int[] {0, 0, -1, 1};

    static int N, M;
    static int[][] before;
    static int[][] after;
    static boolean[][] isVisited;
    static int diffCount;
    static boolean isCPCU; // CPCU인지 저장하는 변수

    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isVisited[i][j]) continue;
                bfs(i, j);
                if (!isCPCU) break;
            }
        }
        if (isCPCU) System.out.println("YES");
        else System.out.println("NO");
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        before = new int[N][M];
        after = new int[N][M];
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                before[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                after[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        diffCount = 0;
        isCPCU = true;
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < M)) return false;
        return true;
    }

    static void bfs(int r, int c) {
        if (before[r][c] != after[r][c]) diffCount += 1;
        if (diffCount >= 2) {   // 변한 섹션이 두 개 이상이면 CPCU가 아님
            isCPCU = false;
            return;
        }
        int afterVaccine = after[r][c];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {r, c});
        isVisited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nextRow = now[0] + dirRow[d];
                int nextCol = now[1] + dirCol[d];
                if (isOuted(nextRow, nextCol) || isVisited[nextRow][nextCol] || before[now[0]][now[1]] != before[nextRow][nextCol]) continue;
                if (after[nextRow][nextCol] != afterVaccine) {  // 한 섹션 내에서 데이터가 두 번 이상 변해도 CPCU가 아님
                    isCPCU = false;
                    return;
                }
                queue.offer(new int[] {nextRow, nextCol});
                isVisited[nextRow][nextCol] = true;
            }
        }
    }

}
