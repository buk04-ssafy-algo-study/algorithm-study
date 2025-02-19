import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
    int a, b, c;

    public Edge(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public int compareTo(Edge o) {
        return this.c - o.c;
    }
}

public class BOJ_1647 {
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Edge> edgeList = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(a, b, c));
        }

        Collections.sort(edgeList); // 유지 비용 오름차순 정렬

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }

        int res = 0;
        int maxCost = 0;

        // 싸이클 생기지 않도록 노드 모두 연결
        for (int i = 0; i < edgeList.size(); i++) {
            Edge cur = edgeList.get(i);

            if (find(cur.a) != find(cur.b)) { // 싸이클 되는 경우 제외
                res += cur.c;
                union(cur.a, cur.b);

                maxCost = cur.c;
            }
        }
        System.out.print(res - maxCost); // 모두 연결하는 비용에서 가장 큰 비용 제외
    }

    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent != bParent) {
            if (aParent < bParent) {
                arr[bParent] = aParent;
            } else {
                arr[aParent] = bParent;
            }
        }
    }

    private static int find(int a) {
        if (arr[a] == a) return a;
        return arr[a] = find(arr[a]);
    }
}
