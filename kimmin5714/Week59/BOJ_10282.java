import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int node;
    long time;

    Edge(int node, long time) {
        this.node = node;
        this.time = time;
    }
}
public class BOJ_10282 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            List<Edge>[] adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) 
                adj[i] = new ArrayList<>();

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                adj[b].add(new Edge(a, s));
            }

            long[] time = new long[n + 1]; // 최소 시간 저장하는 배열
            Arrays.fill(time, Long.MAX_VALUE);
            time[c] = 0;

            Queue<Edge> q = new ArrayDeque<>();
            q.offer(new Edge(c,0));

            while (!q.isEmpty()) {
                Edge cur = q.poll();

                if(cur.time>time[cur.node]) continue; // 원래 시간보다 더 오래 걸리는 경우

                for(int i=0;i<adj[cur.node].size();i++ ){ // 인접 노드 탐색
                    Edge nextEdge = adj[cur.node].get(i);
                    long newTime = cur.time + nextEdge.time; // cur 노드 거쳐서 다음 노드 갈 때 걸리는 시간 계산
                    if(newTime < time[nextEdge.node]) { // 원래 시간이랑 cur 노드 거쳐서 가는 시간이랑 비교
                        time[nextEdge.node] = newTime;
                        q.offer(new Edge(nextEdge.node, newTime));
                    }
                }
            }

            int cnt = 0;
            long sumTime = 0;
            for(int i=1;i<=n;i++){
                if(time[i] == Long.MAX_VALUE) continue;
                cnt++;
                sumTime = Math.max(sumTime, time[i]); // time 배열 중 가장 큰 시간 저장
            }
            sb.append(cnt+" "+sumTime+"\n");
        }
        System.out.print(sb);
    }
}
