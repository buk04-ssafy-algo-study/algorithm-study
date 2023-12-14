import java.util.*;
import java.io.*;
public class Main_인내의도미노장인호석 {
    static class Node {
        int x;
        int y;
        int height;
        boolean state; // true : 넘어진거
        public Node(int x, int y, int height, boolean state) {
            this.x = x;
            this.y = y;
            this.height = height;
            this.state = state;
        }
    }
    static int N, M, R;
    static Node[][] arr;
    // 동서남북 좌표
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int ans = 0;
    static Queue<Node> queue;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new Node[N+1][M+1];

        for(int i =1; i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j =1; j<=M;j++) {
                int height = Integer.parseInt(st.nextToken());
                arr[i][j] = new Node(i,j,height, false);
            }
        }
        // 로직 시작
        for(int i =0; i<R*2;i++) {
            st = new StringTokenizer(br.readLine());
            // 공격
            if(st.countTokens() == 3)  {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                char d = st.nextToken().charAt(0);
                // 이미 넘어져 있는 도미노면 너멍감
                if(arr[x][y].state)continue;

                if(d == 'E')  func(0,x,y);
                else if(d == 'W')  func(1,x,y);
                else if(d == 'S')  func(2,x,y);
                else func(3,x,y);
            }
            // 수비
            else {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                if(!arr[x][y].state)continue;
                arr[x][y].state = false;
            }
        }
        // System.out.println(ans);
        sb.append(ans + "\n");
        for(int i =1; i<=N;i++) {
            for(int j =1; j<=M;j++) {
                sb.append((arr[i][j].state ? 'F' : 'S') + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void func(int i, int x, int y) {
        queue = new ArrayDeque<>();
        queue.offer(arr[x][y]);
        ans++;
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int k = node.height - 1;
            int nx = node.x;
            int ny = node.y;
            node.state = true;
            while(k-- > 0) {
                nx += dx[i];
                ny += dy[i];

                // 경계 체크
                if(nx < 1 || nx > N || ny < 1 || ny >M) break;
                Node next = arr[nx][ny];
                if(next.state) continue;
                queue.offer(arr[nx][ny]);
                arr[nx][ny].state = true;
                ans++;
            }
        }
    }

}
