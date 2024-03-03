import java.util.*;
import java.io.*;
public class Main_트리 {
    static int n,m;
    // static int[][] arr;
    static List<List<Integer>> graph;
    // static int ans = 0;
    static boolean[] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=  new StringBuilder();
        StringTokenizer st;
        int tc = 0;
        while(true) {
            tc++;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if(n ==0 && m ==0) break;
            // arr = new int[n+1][n+1];
            graph = new ArrayList<>();
            for(int i=0; i<=n;i++) {
                graph.add(new ArrayList<>());
            }
            for(int i=0; i< m; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                // arr[r][c] = 1;
                // arr[c][r] = 1;
                graph.get(r).add(c);
                graph.get(c).add(r);
            }
            int ans = 0;
            visit = new boolean[n+1];
            for(int i=1;i<=n;i++) {
                if(!visit[i]) {
                    if(bfs(i)) ans++;
                }
            }
            if(ans == 0) {
                sb.append("Case " + tc + ": No trees.");
            } else if(ans == 1) {
                sb.append("Case " + tc + ": There is one tree.");
            } else {
                sb.append("Case " + tc + ": A forest of " + ans + " trees.");
            }
            sb.append("\n");
            
        }
        System.out.println(sb);
    }
    static boolean bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visit[start] = true;
        int node = 0, edge = 0;
        while(!q.isEmpty()) {
            int now = q.poll();
            node += 1;
            visit[now] = true;
            for(int next : graph.get(now) ) {
                edge += 1;
                if(!visit[next]) {
                    q.add(next);
                }
            }
        }
        return 2*(node-1) == edge ? true : false;
    }
}