import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_6497_전력난 {
    private static int answer, parents[], total, mstSum;

    public static void main(String[] args) throws IOException {
        // 최소 신장 트리 : 간선 가중치 합이 최소가 되는 스패닝 트리

        // 1. 선택되지 않은 간선 중 최소 가중치 간선 선택
        // 2. 그 간선 선택했을 때 사이클 없는 경우에 선택 -> 유니온파인드로 판단
        // 3. 총 V-1개 선택될 때까지 반복

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            List<int[]> graph = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                graph.add(new int[]{x, y, z});
            }

            // 가중치 오름차순 정렬
            Collections.sort(graph, (o1, o2) -> o1[2] - o2[2]);

            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
            total = 0;
            mstSum = 0;

            kruskal(graph);

            // 가중치 전체 합에서 MST 가중치 값 빼기
            System.out.println(total - mstSum);
        }
    }

    private static void kruskal(List<int[]> graph) {
        for (int i = 0; i < graph.size(); i++) {
            int[] cur = graph.get(i);
            total += cur[2];

            // 싸이클 생기는 경우 -> 제외
            if (find(cur[0]) == find(cur[1])) continue;

            // 싸이클 아닌 경우 -> MST에 포함
            mstSum += cur[2];
            union(cur[0], cur[1]);
        }
    }

    private static int find(int n) {
        if (parents[n] == n)
            return n;
        return find(parents[n]);
    }

    private static void union(int n1, int n2) {
        int n1Parent = find(n1);
        int n2Parent = find(n2);

        if (n1Parent > n2Parent)
            parents[n1Parent] = n2Parent;
        else
            parents[n2Parent] = n1Parent;
    }
}
