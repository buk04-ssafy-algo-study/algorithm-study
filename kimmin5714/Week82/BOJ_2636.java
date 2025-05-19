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

public class BOJ_2636 {
    static int arr[][], r, c, remainCheeseCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[r][c];
        remainCheeseCnt = 0;

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==1) remainCheeseCnt++;
            }
        }

        int cheeseCnt = -1;
        int cnt = 0;
        while (remainCheeseCnt > 0) {
            cheeseCnt = remainCheeseCnt;
            bfs(0,0);
            cnt++;
        }

        System.out.println(cnt);
        System.out.println(cheeseCnt);
    }

    private static void bfs(int sr, int sc) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[r][c];
        int[] delr = new int[]{-1, 1, 0, 0};
        int[] delc = new int[]{0, 0, -1, 1};
        int meltCnt = 0;

        q.offer(new Point(sr, sc));
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + delr[i];
                int nc = cur.c + delc[i];

                if(nr<0 || nr>=r || nc<0 || nc>=c || visited[nr][nc]) continue;

                if(arr[nr][nc] == 1) {
                    meltCnt++;
                    arr[nr][nc] = 0;
                }else {
                    q.offer(new Point(nr, nc));
                }
                visited[nr][nc] = true;
            }
        }
        remainCheeseCnt -= meltCnt;
    }
}