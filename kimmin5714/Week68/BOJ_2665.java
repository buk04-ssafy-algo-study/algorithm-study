import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2665 {
    private static int n;
    private static int[][] arr, dist;
    private static int[] delr = {-1,1,0,0}, delc = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        dist = new int[n][n]; // 최소 교환 수 저장하는 배열
        for(int i=0;i<n;i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);

        for(int i=0;i<n;i++) {
            char[] ch = br.readLine().toCharArray();
            for(int j=0;j<n;j++)
                arr[i][j] = ch[j]-'0';
        }

        bfs();
        System.out.print(dist[n-1][n-1]);
    }

    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0});
        dist[0][0] = 0;

        while(!q.isEmpty()) {

            int[] cur = q.poll();

            for(int i=0;i<4;i++) {
                int nr = cur[0] + delr[i];
                int nc = cur[1] + delc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                int cost = 0;
                if(arr[nr][nc] == 0) { // 가려는 칸이 검은 방이면 교환 횟수 추가
                    cost = 1;
                }

                if(dist[nr][nc] > dist[cur[0]][cur[1]] + cost) { // 다음 칸 교환 횟수가 현재보다 더 크면 치환
                    dist[nr][nc] = dist[cur[0]][cur[1]] + cost;
                    q.offer(new int[]{nr,nc});
                }
            }
        }
    }
}