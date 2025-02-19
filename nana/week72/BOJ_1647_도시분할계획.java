import java.util.*;
import java.io.*;

class Main {

    private static class Edge implements Comparable<Edge> {
        int u, v;
        int cost;

        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x > y) parents[y] = x;
        else parents[x] = y;
    }

    private static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    private static void kruskal() {
        int total_cost = 0;
        int max_cost = 0;  // 모든 집을 연결한 뒤, 가장 비용이 비싼 길을 없애서 두 마을로 나눔

        for (Edge now : graph) {
            if (find(now.u) != find(now.v)) {
                // 연결되지 않은 집이라면, 연결시키고 유지비를 더해줌
                union(now.u, now.v);
                total_cost += now.cost;
                max_cost = now.cost;    // 가장 마지막에 연결되는 길의 유지비가 가장 비쌈
            }
        }
        System.out.println(total_cost - max_cost);
    }

    private static int N, M;    // 집의 수, 길의 수
    private static List<Edge> graph;
    private static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.add(new Edge(A, B, C));
        }

        Collections.sort(graph);    // 최저비용 기준으로 고정

        kruskal();
    }
}
