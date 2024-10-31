import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {
    static int n, m;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];

        for (int i = 0; i < n; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < m; j++)
                arr[i][j] = ch[j];
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'L') { // L인 경우에 bfs 탐색
                    int time = move(i, j, new boolean[n][m]);
                    res = Math.max(res, time); // 최대 시간 구하기
                }
            }
        }
        System.out.print(res);
    }

    private static int move(int r, int c, boolean[][] visited) {
        int maxTime = 0;
        int[] delr = {-1, 1, 0, 0};
        int[] delc = {0, 0, -1, 1};

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c, 0});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + delr[i];
                int nc = cur[1] + delc[i];
                int nt = cur[2] + 1;

                if (nr < 0 || nr >= n || nc < 0 || nc >= m
                        || visited[nr][nc] || arr[nr][nc] == 'W') continue;

                visited[nr][nc] = true;
                maxTime = Math.max(maxTime, nt); // 최대 시간 갱신
                q.offer(new int[]{nr, nc, nt});
            }
        }
        return maxTime;
    }
}
