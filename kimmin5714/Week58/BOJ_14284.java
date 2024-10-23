/*
다익스트라 : 하나의 정점에서 다른 모든 정점으로 가는 최단 경로, 음의 간선x
1. 출발 노드, 도착 노드 설정
2. 출발 노드 기준 각 노드의 최소 비용 저장
3. 현재 노드의 인접 노드 중, 방문하지 않은 노드 중, 가장 적은 비용 노드 선택
4. 해당 노드 거쳐서 특정 노드로 가는 경우를 고려하여 최소 비용 갱신
5. 3번 4번 반복
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Node implements Comparable<Node>{
    int v, cost;

    public Node(int v, int cost) {
        this.v = v;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Node{" +
                "v=" + v +
                ", cost=" + cost +
                '}';
    }

    @Override
    public int compareTo(Node o) { // 비용이 작은 노드부터 확인하기 위해서
        return this.cost - o.cost;
    }
}
public class BOJ_14284 {
    static int dist[]; // 최소 가중치 저장 배열
    static List<List<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dist = new int[n+1];
        for(int i=0;i<n+1;i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b,c));
            graph.get(b).add(new Node(a,c));
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        System.out.print(dijkstra(s,t));
    }

    private static int dijkstra(int s, int t) {
        Arrays.fill(dist, Integer.MAX_VALUE); // 최소 가중치 배열 무한대로 초기화
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(s, 0));

        dist[s] = 0;

        while(!q.isEmpty()) {
            Node cur = q.poll(); // 비용이 가장 작은 노드 꺼내기

            if(cur.cost > dist[cur.v]) continue; // 이미 처리된 노드
            for(Node next : graph.get(cur.v)) { // 현재 노드의 인접 노드 확인
                if(dist[next.v] > dist[cur.v] + next.cost) { // 더 가중치가 적은 경우 갱신
                    dist[next.v] = dist[cur.v] + next.cost;
                    q.add(new Node(next.v, dist[next.v])); // 큐에 추가
                }
            }
        }
        return dist[t]; // t 노드까지 최소 가중치 반환
    }
}