import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2146 {
    private static int n;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int num = 1;
        for (int i = 0; i < n; i++) { // 섬 번호 부여
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    island(i, j, num);
                    num++;
                }
            }
        }

        int res = Integer.MAX_VALUE;
        visited = new boolean[n][n]; // 방문 배열 초기화

        for (int i = 0; i < n; i++) { // 섬과 섬 사이 길 찾기
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0 && !visited[i][j])
                    res = Math.min(findPath(i, j), res);
            }
        }
        System.out.print(res);
    }

    private static int findPath(int r, int c) {
        int ret = Integer.MAX_VALUE;
        int[] delr = {-1, 1, 0, 0};
        int[] delc = {0, 0, -1, 1};
        visited = new boolean[n][n]; // 같은 방문 배열 사용으로 초기화 해주어야 함

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c, 0});
        visited[r][c] = true;
        int curNum = map[r][c];

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + delr[i];
                int nc = cur[1] + delc[i];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc]) continue;

                if(map[nr][nc] == 0){ // 다음 칸이 바다이면 큐에 추가
                    q.offer(new int[]{nr, nc, cur[2]+1});
                    visited[nr][nc]= true;
                }
                else if(map[nr][nc] != 0 && map[nr][nc] != curNum) { // 다음 칸이 다른 섬이면 거리 return
                    ret = cur[2];
                    return ret;
                }
            }
        }
        return ret;
    }

    private static void island(int r, int c, int num) {
        int[] delr = {-1, 1, 0, 0};
        int[] delc = {0, 0, -1, 1};

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        visited[r][c] = true;
        map[r][c] = num;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + delr[i];
                int nc = cur[1] + delc[i];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n
                        || map[nr][nc] == 0 || visited[nr][nc]) continue;
                q.offer(new int[]{nr, nc}); // 다음 칸이 1인 경우에 큐에 추가
                visited[nr][nc] = true;
                map[nr][nc] = num; // 섬 번호 부여
            }
        }
    }
}