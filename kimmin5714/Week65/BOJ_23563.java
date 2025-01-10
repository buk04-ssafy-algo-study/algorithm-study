import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
    int cost, r, c;

    public Point(int cost, int r, int c) {
        this.cost = cost;
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Point o) { // 시간 오름차순
        return this.cost - o.cost;
    }
}

public class BOJ_23563 {
    private static int H, W, dist[][], delr[] = {-1, 1, 0, 0}, delc[] = {0, 0, -1, 1};
    private static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new char[H][W];

        int sR = 0, sC = 0, eR = 0, eC = 0;
        for (int i = 0; i < H; i++) {
            String row = br.readLine();
            for (int j = 0; j < W; j++) {
                arr[i][j] = row.charAt(j);
                if (arr[i][j] == 'S') {
                    sR = i;
                    sC = j;
                }
                if (arr[i][j] == 'E') {
                    eR = i;
                    eC = j;
                }
            }
        }

        dist = new int[H][W]; // 최소 시간 저장 배열

        for (int i = 0; i < H; i++) {
            Arrays.fill(dist[i], 987654321);
        }
        dijkstra(sR, sC);
        System.out.print(dist[eR][eC]);
    }

    private static void bfs(int y, int x, PriorityQueue<Point> pq) {
        // 인접한 곳에 벽이 있는 칸이면 갱신
        for (int i = 0; i < 4; i++) {
            int nr = y + delr[i];
            int nc = x + delc[i];

            if (nr < 0 || nr >= H || nc < 0 || nc >= W || arr[nr][nc] == '#') continue;

            if (isNearWall(nr, nc)) { // 옆에 벽이 있어야 계속 전파 가능
                if (dist[nr][nc] > dist[y][x]) { // 가중치 더 작은 것으로
                    dist[nr][nc] = dist[y][x];
                    pq.add(new Point(dist[nr][nc], nr, nc));
                    bfs(nr, nc, pq);
                }
            }
        }
    }

    private static void dijkstra(int sR, int sC) {
        PriorityQueue<Point> pq = new PriorityQueue<>(); // 다익스트라를 위한 우선순위큐
        dist[sR][sC] = 0;
        pq.add(new Point(0, sR, sC));

        if (isNearWall(sR, sC)) {
            bfs(sR, sC, pq);
        }

        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            int cost = cur.cost;
            int r = cur.r;
            int c = cur.c;

            if (dist[r][c] < cost) continue;

            for (int i = 0; i < 4; i++) {
                int nr = r + delr[i];
                int nc = c + delc[i];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W || arr[nr][nc] == '#') continue;

                if (dist[nr][nc] > cost + 1) {
                    dist[nr][nc] = cost + 1;
                    if (isNearWall(nr, nc)) bfs(nr, nc, pq); // 인접한 곳에 벽이 있으면 전파
                    pq.add(new Point(cost + 1, nr, nc));
                }
            }
        }
    }

    private static boolean isNearWall(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + delr[i];
            int nc = c + delc[i];
            if (arr[nr][nc] == '#') return true;
        }
        return false;
    }
}