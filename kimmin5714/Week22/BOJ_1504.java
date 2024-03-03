import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    int num, weight;

    Node(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;
    }
}
public class BOJ_1504 {
    static int n,e,v1,v2,dist[], res;
    static ArrayList<ArrayList<Node>> adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        adj = new ArrayList<ArrayList<Node>>();
        for(int i=0;i<n+1;i++)
            adj.add(new ArrayList<Node>());

        dist = new int[n+1];

        for(int i=0;i<e;i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj.get(a).add(new Node(b,c));
            adj.get(b).add(new Node(a,c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int res1 = 0;
        res1 += dijkstra(1,v1);
        res1 += dijkstra(v1,v2);
        res1 += dijkstra(v2,n);

        int res2 = 0;
        res2 += dijkstra(1,v2);
        res2 += dijkstra(v2,v1);
        res2 += dijkstra(v1,n);

        if(res1 > 200000000 && res2 > 200000000) res = -1;
        else
            res = Math.min(res1, res2);

        System.out.print(res);
    }
    private static int dijkstra(int start, int end){
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist,200000000+1);
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0));
        dist[start] = 0;
        visited[start] = true;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            for (Node next : adj.get(cur.num)){
                if(!visited[next.num]
                        && dist[next.num]>dist[cur.num]+next.weight){
                    dist[next.num] = dist[cur.num]+next.weight;
                    q.offer(new Node(next.num, dist[next.num]));
                }
            }
        }
        return dist[end];
    }
}