package study.week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1753_최단경로 {

    static class Node {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    private static int V, E, K; // V 정점의 개수, E 간선의 개수, K 시작 정점의 번호
    private static List<Node>[] list;   // 노드 연결 리스트
    private static boolean[] isVisited;
    private static int[] dist;  // 최단거리 저장
    private static int INF = 100_000_000;   // 최댓값

    private static void dijkstra(int start) {
        // 가중치를 기준으로 노드 오름차순 정렬
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        q.add(new Node(start, 0));  // 시작 노드(K)의 가중치 = 0
        dist[start] = 0;    // 시작 노드의 거리 = 0

        while (!q.isEmpty()) {
            Node now = q.poll();    // 현재 노드를 꺼냄

            if (!isVisited[now.vertex]) {
                isVisited[now.vertex] = true;   // 현재 정점은 방문처리
            }

            for (Node next : list[now.vertex]) {    // 현재 점점과 연결된 노드를 검사
                // 다음 노드가 방문하지 않은 정점이고
                // 다음 노드로 가는 최단거리를 (현재 가중치 + 다음 가중치) 와 비교
                // 현재 노드를 거치는 거리가 더 빠르면 갱신 + q에 추가
                if (!isVisited[next.vertex] && dist[next.vertex] > now.weight + next.weight) {
                    dist[next.vertex] = now.weight + next.weight;
                    q.add(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        list = new ArrayList[V + 1];
        dist = new int[V + 1];
        isVisited = new boolean[V + 1];

        Arrays.fill(dist, INF);

        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // u -> v 로 가는 가중치가 w
            list[u].add(new Node(v, w));
        }

        dijkstra(K);

        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) // 가중치가 최댓값, 즉 경로가 존재하지 않으면 INF
                sb.append("INF").append("\n");
            else
                sb.append(dist[i]).append("\n");
        }

        System.out.println(sb);
    }
}
