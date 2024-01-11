// https://www.acmicpc.net/problem/1504
import java.io.*;
import java.util.*;

public class Main {

    static int N, E, v1, v2;
    static int INF = 987654321;
    static PriorityQueue<Node> pq;
    static List<Node>[] adjList;
    static boolean[] visited;
    static int[] dist;
    static class Node implements Comparable<Node> {
        int vertex, cost;
        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // # of vertex
        E = Integer.parseInt(st.nextToken()); // # of edge

        dist = new int[N+1];
        adjList = new List[N+1];
        for(int i=1;i<=N;i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // a <-> b
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()); // dist
            adjList[a].add(new Node(b, c));
            adjList[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        // case1: 1->v1->v2->N
        // case2: 1->v2->v1->N
        int case1 = dijkstra(1, v1)+dijkstra(v1, v2)+dijkstra(v2, N);
        int case2 = dijkstra(1, v2)+dijkstra(v2, v1)+dijkstra(v1, N);

        int answer = Math.min(case1, case2);
        answer = (answer >= INF || answer<0) ? -1 : answer;
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    static int dijkstra(int start, int end) {
        visited = new boolean[N+1];
        Arrays.fill(dist, INF);

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            int v = now.vertex;
            
            if(visited[v]) continue;
            visited[v] = true;

            for(Node connected : adjList[v]) {
                if(dist[v]+connected.cost < dist[connected.vertex]) {
                    dist[connected.vertex] = dist[v]+connected.cost;
                }
                pq.offer(new Node(connected.vertex, dist[connected.vertex]));
            }
        }

        int result = dist[end];
        return result;
    }
}
