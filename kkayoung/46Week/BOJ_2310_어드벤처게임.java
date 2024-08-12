// https://www.acmicpc.net/problem/2310
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N==0) break;
            List<Integer>[] adjList = new List[N+1];
            int[] cost = new int[N+1];

            for(int i=1;i<=N;i++) {
                adjList[i] = new ArrayList<>();

                st = new StringTokenizer(br.readLine());
                char type = st.nextToken().charAt(0);
                int c = Integer.parseInt(st.nextToken());
                
                if(type=='T') cost[i] = -c;
                else cost[i] = c;

                while(st.hasMoreTokens()) {
                    int next = Integer.parseInt(st.nextToken());
                    if(next==0) break;
                    adjList[i].add(next);
                }
            }

            sb.append(bfs(adjList, cost) ? "Yes\n" : "No\n");
        }

        System.out.println(sb.toString());
    }

    static boolean bfs(List<Integer>[] adjList, int[] cost) {
        boolean result = false;
        Queue<int[]> q = new ArrayDeque<>();
        int N = cost.length-1;
        boolean[] visited = new boolean[N+1];
        
        q.offer(new int[]{1, 0});

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int room = now[0];
            int cash = now[1];

            if(cost[room]>0) {
                if(cash<cost[room]) {
                    cash = cost[room];
                }
            } else {
                cash += cost[room];
            }

            if(cash<0) continue;
            if(room == N) return true;
            visited[room] = true;

            for(int next:adjList[room]) {
                if(visited[next]) continue;
                q.offer(new int[]{next, cash});
            }
        }

        return result;
    }

}
