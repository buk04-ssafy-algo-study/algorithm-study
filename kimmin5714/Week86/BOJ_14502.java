import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14502 {
    public static List<int[]> blank, virus;
    public static int[][] arr, copy;
    public static int answer, wall[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        copy = new int[N][M];
        blank = new ArrayList<>();
        virus = new ArrayList<>();
        answer = 0;

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                arr[i][j] = copy[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 0) blank.add(new int[]{i,j});
                else if(arr[i][j] == 2) virus.add(new int[]{i,j});
            }
        }

        wall = new int[3];
        makeWall(0,0); // start, cnt
        System.out.println(answer);
    }

    private static void makeWall(int start, int cnt) {
        if(cnt == 3) {
            // 벽 세우기
            for(int i=0;i<3;i++) {
                int[] wallPoint = blank.get(wall[i]);
                arr[wallPoint[0]][wallPoint[1]] = 1;
            }

            // 전염
            for(int i=0;i<virus.size();i++) {
                bfs(virus.get(i));
            }

            // 안전영역 세기
            int safeArea = 0;
            for(int i=0;i<arr.length;i++) {
                for(int j=0;j<arr[0].length;j++) {
                    if(arr[i][j] == 0) safeArea++;
                }
            }
            answer = Math.max(answer,safeArea);

            // arr 초기화
            for(int i=0;i<arr.length;i++) {
                arr[i] = Arrays.copyOf(copy[i], arr[i].length);
            }
            return;
        }
        for(int i=start;i<blank.size();i++) { // 벽 세울 3군데 조합
            wall[cnt] = i;
            makeWall(i+1, cnt+1);
        }
    }

    private static void bfs(int[] start) {
        int[] delr = new int[]{-1,1,0,0};
        int[] delc = new int[]{0,0,-1,1};
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[arr.length][arr[0].length];

        q.offer(start);
        visited[start[0]][start[1]] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i=0;i<4;i++) {
                int nr = cur[0]+delr[i];
                int nc = cur[1]+delc[i];

                if(nr<0 || nr>=arr.length || nc<0 || nc>=arr[0].length
                    || visited[nr][nc] || arr[nr][nc] !=0) continue;

                visited[nr][nc] = true;
                arr[nr][nc] = 2;
                q.offer(new int[]{nr,nc});
            }
        }
    }
}
