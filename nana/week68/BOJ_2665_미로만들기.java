import java.util.*;
import java.io.*;

class Main {

    private static int n;
    private static int[][] rooms, dist;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private static void bfs() {

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        dist[0][0] = 0;

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int[] d : delta) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                // 흰 방 처리
                if (rooms[nx][ny] == 1) {
                    if (dist[nx][ny] > dist[p.x][p.y]) {
                        dist[nx][ny] = dist[p.x][p.y];
                        q.add(new Point(nx, ny));
                    }
                }
                // 검은 방 처리
                else {
                    if (dist[nx][ny] > dist[p.x][p.y] + 1) {
                        dist[nx][ny] = dist[p.x][p.y] + 1;
                        q.add(new Point(nx, ny));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        rooms = new int[n][n];
        dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < n; j++) {
                rooms[i][j] = str.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();

        System.out.println(dist[n - 1][n - 1]);
    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
