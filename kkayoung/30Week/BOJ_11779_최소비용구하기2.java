// https://www.acmicpc.net/problem/11779
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<Node>[] adjList;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static boolean[] visited;
    static int[] dist;
    static PriorityQueue<PathInfo> paths = new PriorityQueue<>();

    static class Node implements Comparable<Node> {
        int v, cost;
        List<Integer> path = new ArrayList<>();
        Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
        public void addNode(int node) {
            this.path.add(node);
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static class PathInfo implements Comparable<PathInfo> {
        Object[] path;
        int dist;
        PathInfo(Object[] path, int dist) {
            this.path = path;
            this.dist = dist;
        }
        @Override
        public int compareTo(PathInfo o) {
            return Integer.compare(this.dist, o.dist);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        visited = new boolean[N+1];
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        adjList = new List[N+1];
        for(int i=1;i<=N;i++) {
            adjList[i] = new ArrayList<>();
        }
        for(int b=0;b<M;b++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[src].add(new Node(dst, cost));
        }
        st = new StringTokenizer(br.readLine());
        int src = Integer.parseInt(st.nextToken());
        int dst = Integer.parseInt(st.nextToken());
        
        dijkstra(src, dst);
        PathInfo pathInfo = paths.poll();

        StringBuilder sb = new StringBuilder();
        sb.append(dist[dst]).append("\n");
        sb.append(pathInfo.path.length).append("\n");
        for(int i=0;i<pathInfo.path.length;i++) {
            sb.append(pathInfo.path[i]).append(' ');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    static void dijkstra(int src, int dst) {
        dist[src] = 0;
        Node node = new Node(src, 0);
        node.addNode(src);
        pq.offer(node);

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(visited[now.v]) continue;
            visited[now.v] = true;
            for(Node connected:adjList[now.v]) {
                if(dist[now.v] + connected.cost < dist[connected.v]) {
                    dist[connected.v] = dist[now.v] + connected.cost;

                    Node newNode = new Node(connected.v, dist[connected.v]);
                    for(int nodeInPath : now.path) {
                        newNode.addNode(nodeInPath);
                    }
                    newNode.addNode(connected.v);
                    if(connected.v==dst) {
                        PathInfo info = new PathInfo(newNode.path.toArray(), dist[dst]);
                        paths.add(info);
                    }
                    pq.offer(newNode);
                }
            }
        }
    }
}
