package SSAFY.study.algo.week50s.week58;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main_14284_간선이어가기2 {

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e1) { // 가중치 오름차순 정렬
            return this.weight - e1.weight;
        }

        @Override
        public String toString() { return this.from + " " + this.to + " " + this.weight; }
    }

    static int n, m, s, t;
    static List<List<Edge>> edgeList;

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        for (int i = 0; i < n+1; i++) edgeList.add(new ArrayList<>());

        int from, to, weight;
        for (int i =0; i < m ;i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            edgeList.get(from).add(new Edge(from, to, weight)); // 무방향 그래프이므로 양방향 다 추가
            edgeList.get(to).add(new Edge(to, from, weight));
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
    }

    static void dijkstra() {
        int[] weights = new int[n+1];   // s에서 1~n까지 가는 가장 적은 가중치 저장
        Arrays.fill(weights, 100_000 * 100);    // 간선 수 최댓값 * 가중치 최댓값으로 초기화
        weights[s] = 0; // 자기 자신으로 가는 가중치는 0

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Edge initEdge;
        for (int i = 0; i < edgeList.get(s).size(); i++) {  // pq에 시작점의 간선들 넣어주며 연결된 정점까지 가중치 값 갱신
            initEdge = edgeList.get(s).get(i);
            pq.offer(initEdge);
            weights[initEdge.to] = initEdge.weight;
        }

        Edge now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            List<Edge> nextEdgeList = edgeList.get(now.to);
            for (Edge next : nextEdgeList) {
                if (weights[next.to] > weights[next.from] + next.weight) {
                    weights[next.to] = weights[next.from] + next.weight;
                    pq.offer(next);
                }
            }
        }

        System.out.println(weights[t]);
    }

}
