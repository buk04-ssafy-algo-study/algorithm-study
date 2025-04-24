import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int r, c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class BOJ_16973 {
    static int[][] arr;
    static int[][] sumArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1][m + 1];
        sumArr = new int[n + 1][m + 1]; // 누적합 저장 배열

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 누적합 배열 생성
        buildSumArr(n, m);

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int er = Integer.parseInt(st.nextToken());
        int ec = Integer.parseInt(st.nextToken());

        int[] delr = {-1, 1, 0, 0};
        int[] delc = {0, 0, -1, 1};

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n + 1][m + 1];
        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;

        int result = -1;

        // BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int cnt = cur[2];

            for (int i = 0; i < 4; i++) {
                int newSr = curR + delr[i];
                int newSc = curC + delc[i];
                int newEr = newSr + r - 1;
                int newEc = newSc + c - 1;

                if (newSr <= 0 || newSc <= 0 || newEr > n || newEc > m) continue;
                if (visited[newSr][newSc]) continue;

                if (newSr == er && newSc == ec) {
                    result = cnt + 1;
                    break;
                }

                if (checkNewArea(newSr, newSc, newEr, newEc)) {
                    visited[newSr][newSc] = true;
                    q.offer(new int[]{newSr, newSc, cnt + 1});
                }
            }
            if (result != -1) break;
        }

        System.out.println(result);
    }

    private static void buildSumArr(int n, int m) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sumArr[i][j] = arr[i][j] + sumArr[i - 1][j] + sumArr[i][j - 1] - sumArr[i - 1][j - 1];
            }
        }
    }

    private static boolean checkNewArea(int r1, int c1, int r2, int c2) {
        int total = sumArr[r2][c2] - sumArr[r1 - 1][c2] - sumArr[r2][c1 - 1] + sumArr[r1 - 1][c1 - 1];
        return total == 0;
    }
}