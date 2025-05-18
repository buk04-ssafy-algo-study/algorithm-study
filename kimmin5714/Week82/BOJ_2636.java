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
    static int arr[][], r, c;
    static List<Point> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 테두리 체크
        for (int i = 0; i < c; i++) {
            arr[0][i] = 2;
            arr[r - 1][i] = 2;
        }
        for (int j = 0; j < r; j++) {
            arr[j][0] = 2;
            arr[j][c - 1] = 2;
        }

        int cheeseCnt = -1;
        int cnt = 0;
        while (true) {
            list = new ArrayList<>();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (arr[i][j] == 1) {
                        bfs(i, j); // 해당 칸이 외부랑 연결되면(녹는 치즈) 리스트에 추가
                    }
                }
            }
            if (list.size() == 0) break;
            cheeseCnt = list.size();
            for (int i = 0; i < list.size(); i++) { // 치즈 녹음
                arr[list.get(i).r][list.get(i).c] = 0;
            }
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

        q.offer(new Point(sr, sc));
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + delr[i];
                int nc = cur.c + delc[i];

                if(nr<0 || nr>=r || nc<0 || nc>=c || visited[nr][nc]
                || arr[nr][nc] == 1) continue;

                if(arr[nr][nc] == 2) {
                    list.add(new Point(sr,sc));
                    return;
                }

                q.offer(new Point(nr, nc));
                visited[nr][nc] = true;
            }
        }

    }
}
