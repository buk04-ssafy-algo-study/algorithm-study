package SSAFY.study.algo.week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드4_14938_서강그라운드 {

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int length;

        public Edge(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public int compareTo(Edge e) {
            return this.length - e.length;
        }
    }

    static int n, m, r; // n: 노드 개수, m: 수색 범위, r: 간선 개수
    static List<List<Edge>> edgeList;
    static int[] items; // 각 노드별 가지고 있는 아이템의 개수

    public static void main(String[] args) throws IOException {
        init();
        int maxGet = Integer.MIN_VALUE;
        for (int i = 1; i < n+1; i++) {
            int tempMax = items[i]; // 얻을 수 있는 아이템의 최초 개수는 낙하 지점에 있는 아이템의 개수로
            tempMax += dijkstra(i);   // 수색범위 밖이면 패스
            maxGet = Math.max(maxGet, tempMax);
        }
        System.out.println(maxGet);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        items = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n+1; i++) items[i] = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        for (int i = 0; i < n+1; i++) edgeList.add(new ArrayList<>());
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            edgeList.get(from).add(new Edge(from, to, length));
            edgeList.get(to).add(new Edge(to, from, length));
        }
    }

    static int dijkstra(int start) {   // start부터 end까지 가는 최소 거리 계산
        int l = 0;  // start부터 end까지의 거리
        int[] cost = new int[n+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;    // 시작지점의 거리는 0으로

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        List<Edge> startPointEdges = edgeList.get(start);
        for (int i = 0; i < startPointEdges.size(); i++) {
            Edge now = startPointEdges.get(i);
            cost[now.to] = now.length;
            pq.offer(now);
        }

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            int nowTo = now.to;

            List<Edge> nextEdgeList = edgeList.get(nowTo);
            for (int i = 0; i < nextEdgeList.size(); i++) {
                Edge next = nextEdgeList.get(i);
                int nextFrom = next.from;
                int nextTo = next.to;
                int nextLength = next.length;
                if (cost[nextTo] > cost[nextFrom] + nextLength) {
                    cost[nextTo] = cost[nextFrom] + nextLength;
                    pq.offer(next);
                }
            }
        }

        int itemCount = 0;
        for (int i = 1; i < n+1; i++) {
            if (cost[i] > m || i == start) continue;
            itemCount += items[i];
        }

        return itemCount;
    }

}
