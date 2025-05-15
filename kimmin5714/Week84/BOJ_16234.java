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

public class BOJ_16234 {
    static int N, L, R, sum, arr[][];
    static Map<Integer, int[]> numToSizeAndSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        numToSizeAndSum = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int totalCnt = 0;

        while (true) {
            boolean[][] visited = new boolean[N][N];
            boolean flag = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        List<Point> pointList = new ArrayList<>(); // 합칠 수 있는 좌표 리스트
                        sum = 0; // 합계

                        bfs(visited, i, j, pointList); // 좌표 list와 sum 구하기

                        if (pointList.size() > 1) flag = true; // 합칠 수 있는 게 있는 경우

                        for (int p = 0; p < pointList.size(); p++) { // 새로운 값으로 대체
                            int r = pointList.get(p).r;
                            int c = pointList.get(p).c;

                            arr[r][c] = sum / pointList.size();
                        }
                    }
                }
            }

            if (!flag) break; // 더이상 허물 수 있는 경계선이 없는 경우 종료
            totalCnt++;
        }
        System.out.println(totalCnt);
    }

    private static void bfs(boolean[][] visited, int r, int c, List<Point> list) {
        int[] delr = new int[]{-1, 1, 0, 0};
        int[] delc = new int[]{0, 0, -1, 1};

        Queue<Point> q = new ArrayDeque<>();

        q.offer(new Point(r, c));
        visited[r][c] = true;
        list.add(new Point(r, c));
        sum += arr[r][c];

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + delr[i];
                int nc = cur.c + delc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

                int diff = Math.abs(arr[cur.r][cur.c] - arr[nr][nc]);
                if (diff < L || diff > R) continue;

                q.offer(new Point(nr, nc));
                visited[nr][nc] = true;
                list.add(new Point(nr, nc));
                sum += arr[nr][nc];
            }
        }
    }

}
