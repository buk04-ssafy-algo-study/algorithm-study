import java.util.*;
import java.io.*;

class Main {

    private static int N, res;
    private static int[][] map; // 바다: 0, 육지: 1
    private static boolean[][] isVisited;
    private static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static void dfs(int i, int j, int index) {

        isVisited[i][j] = true;
        map[i][j] = index;

        for (int[] d : delta) {
            int nx = i + d[0];
            int ny = j + d[1];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

            if (!isVisited[nx][ny] && map[nx][ny] == 1) {
                dfs(nx, ny, index);
            }
        }
    }

    private static void bfs(int x, int y, int num) {
        Queue<Point> q = new ArrayDeque<>();
        isVisited = new boolean[N][N];

        isVisited[x][y] = true;
        q.add(new Point(x, y, 0));

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int[] d : delta) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                // 같은 섬이거나 방문했던 곳이면 넘어감
                if (map[nx][ny] == num || isVisited[nx][ny]) continue;

                if (map[nx][ny] == 0) {
                    // 바다인 경우에만 이동 가능
                    q.add(new Point(nx, ny, p.count + 1));
                    isVisited[nx][ny] = true;
                } else {
                    // 다른 섬을 만났으면 갱신 + 더이상 그 섬에 도착해도 최단거리가 아니니 리턴
                    res = Math.min(res, p.count);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        res = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬끼리 넘버링 해주기
        int index = 2;  // 1은 육지인 경우이기 때문에 2부터 시작
        isVisited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    dfs(i, j, index++);
                }
            }
        }

        // 각 섬에서 다른 섬과의 거리 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 1) {
                    bfs(i, j, map[i][j]);
                }
            }
        }

        System.out.println(res);
    }

    public static class Point {
        int x, y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
