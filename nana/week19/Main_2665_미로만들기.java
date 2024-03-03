import java.io.*;
import java.util.*;

public class Main_2665_미로만들기 {

    private static int n;
    private static int[][] room, dist;
    private static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static void dijkstra() {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0));
        // 첫번째 칸에서는 칸을 바꿀 필요가 없기 때문에 0
        dist[0][0] = 0;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int[] d : delta) {
                int nx = cur.x + d[0];
                int ny = cur.y + d[1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                if (dist[nx][ny] > dist[cur.x][cur.y]) {
                    // 다음 값이 현재칸보다 클 때
                    if (room[nx][ny] == 1) {
                        // 다음 방이 흰 방이라면 그대로
                        dist[nx][ny] = dist[cur.x][cur.y];
                    } else {
                        // 다음 방이 검은 칸이라면? 현재칸 + 1을 넣어준다
                        dist[nx][ny] = dist[cur.x][cur.y] + 1;
                    }

                    q.add(new Point(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        room = new int[n][n];
        dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                room[i][j] = str.charAt(j) - '0';
                // 벽을 최소한으로 부수는 것을 계산하기 위해 다익스트라 사용
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        dijkstra();
        System.out.println(dist[n - 1][n - 1]);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
