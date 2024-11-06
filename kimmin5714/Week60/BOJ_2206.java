import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
    static int N, M, res, arr[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        res = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < M; j++)
                arr[i][j] = Integer.parseInt(str[j]);
        }

        res = bfs();

        if (res == Integer.MAX_VALUE) res = -1;
        System.out.print(res);
    }

    private static int bfs() {
        int min = Integer.MAX_VALUE;
        int[] delr = {-1, 1, 0, 0};
        int[] delc = {0, 0, -1, 1};

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2]; // 0은 벽을 부수지 않은 경우, 1은 벽을 부순 경우

        q.offer(new int[]{0, 0, 1, 0});
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int cnt = cur[2];
            int broken = cur[3];

            if (r == N - 1 && c == M - 1) {
                min = Math.min(min, cnt);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + delr[i];
                int nc = c + delc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                if (arr[nr][nc] == 0 && !visited[nr][nc][broken]) { // 벽이 아니라면 그냥 이동
                    visited[nr][nc][broken] = true;
                    q.offer(new int[]{nr, nc, cnt + 1, broken});
                } else if (arr[nr][nc] == 1 && broken == 0 && !visited[nr][nc][1]) { // 벽이라면 벽을 부수지 않은 경우만 이동
                    visited[nr][nc][1] = true; // 벽 부쉈다(1)
                    q.offer(new int[]{nr, nc, cnt + 1, 1});
                }
            }
        }
        return min;
    }
}