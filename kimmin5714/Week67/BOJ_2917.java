import java.io.*;
import java.util.*;

class Node {
    int r;
    int c;
    int d;

    public Node(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}

public class BOJ_2917 {

    static int R, C;
    static int[][] dist;
    static char[][] map;
    static Queue<Node> treeList;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        dist = new int[R][C];
        map = new char[R][C];
        treeList = new ArrayDeque<>();

        int sr = 0, sc = 0, er = 0, ec = 0;

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                dist[i][j] = Integer.MAX_VALUE;
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'V') { // 현우
                    sr = i;
                    sc = j;
                } else if (map[i][j] == 'J') { // 오두막
                    er = i;
                    ec = j;
                } else if (map[i][j] == '+') { // 나무
                    dist[i][j] = 0; // 나무와 거리 저장
                    treeList.offer(new Node(i, j, 0));
                }
            }
        }

        treeDijkstra(); // 각 칸마다 나무와의 최소 거리 저장
        System.out.println(dijkstra(sr, sc, er, ec)); // 출발 지점부터 끝 지점까지 최단 거리
    }

    private static void treeDijkstra() {
        while (!treeList.isEmpty()) { // 나무 기준으로 BFS
            Node curr = treeList.poll();

            if (curr.d > dist[curr.r][curr.c]) continue; // 현재 칸에 저장된 거리가 더 작으면 처리할 필요x

            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == '+') continue;
                if (dist[nr][nc] > curr.d + 1) { // 지금 거리 +1과 다음 칸 거리 비교
                    dist[nr][nc] = curr.d + 1;
                    treeList.offer(new Node(nr, nc, dist[nr][nc]));
                }
            }
        }
    }

    private static int dijkstra(int sr, int sc, int er, int ec) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> (o2.d - o1.d)); // 거리 작은 순으로 정렬
        boolean[][] visited = new boolean[R][C];
        visited[sr][sc] = true;

        int min = Integer.MAX_VALUE;
        pq.offer(new Node(sr, sc, dist[sr][sc]));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (min > curr.d) min = curr.d; // 최소 안전 거리 갱신
            if (curr.r == er && curr.c == ec) return min; // 도착점에 도착한 경우

            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                pq.offer(new Node(nr, nc, dist[nr][nc]));
            }
        }
        return 0;
    }
}