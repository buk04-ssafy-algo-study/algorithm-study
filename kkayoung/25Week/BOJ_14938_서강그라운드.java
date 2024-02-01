// https://www.acmicpc.net/problem/14938
import java.io.*;
import java.util.*;

public class Main {
    
    static int n, m, r;
    static int[] items, dist;
    static List<Node>[] adjList;
    static PriorityQueue<Node> pq;
    static class Node implements Comparable<Node>{
        int v, d;
        Node(int v, int d) {
            this.v = v;
            this.d = d;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.d, o.d);
        }
    }

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
        pq = new PriorityQueue<>();
        int answer = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // # of region
        m = Integer.parseInt(st.nextToken()); // range
        r = Integer.parseInt(st.nextToken()); // # of road

        items = new int[n+1];
        adjList = new List[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) {
            adjList[i] = new ArrayList<>();
            items[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<r;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            adjList[a].add(new Node(b, l));
            adjList[b].add(new Node(a, l));
        }

        for(int city=1;city<=n;city++) {
            answer = Math.max(answer, dijkstra(city));
        }

		bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    static int dijkstra(int start) {
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            int v = now.v;

            if(visited[v]) continue;
            visited[v] = true;

            for(Node adjNode : adjList[v]) {
                if(dist[v] + adjNode.d < dist[adjNode.v]) {
                    dist[adjNode.v] = dist[v] + adjNode.d;
                    pq.offer(new Node(adjNode.v, dist[adjNode.v]));
                }
            }
        }

        int result = 0;
        for(int i=1;i<=n;i++) {
            if(dist[i]<=m) result += items[i];
        }
        return result;
    }
}
