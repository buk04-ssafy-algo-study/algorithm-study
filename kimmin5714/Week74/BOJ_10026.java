import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
class Point {
    int r,c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
public class BOJ_10026 {
    private static int N, res1, res2;
    private static char[][] arr, arr2;
    private static boolean[][] visited1, visited2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        arr2 = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);

                if(arr[i][j] == 'G') { // 적록색약용 배열
                    arr2[i][j] = 'R';
                }
                else {
                    arr2[i][j] = arr[i][j];
                }
            }
        }

        res1 = 0; res2 = 0;
        visited1 = new boolean[N][N];
        visited2 = new boolean[N][N];

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(!visited1[i][j]) {
                    bfs(arr, visited1, i, j);
                    res1++;
                }
                if(!visited2[i][j]) {
                    bfs(arr2,visited2, i,j);
                    res2++;
                }
            }
        }

        System.out.print(res1 + " " + res2);
    }

    private static void bfs(char[][] arr, boolean[][] visited, int r, int c) {
        int[] delr = new int[]{-1,1,0,0};
        int[] delc = new int[]{0,0,-1,1};

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r,c));
        visited[r][c] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            for(int i=0;i<4;i++) {
                int nr = cur.r + delr[i];
                int nc = cur.c + delc[i];
                if(nr<0 || nr>=N || nc<0 || nc>= N
                        || visited[nr][nc] || arr[nr][nc] != arr[r][c]) // 다음 칸이 다른 알파벳이면 가지 않음
                    continue;

                visited[nr][nc] = true;
                q.offer(new Point(nr,nc));
            }
        }
    }
}
