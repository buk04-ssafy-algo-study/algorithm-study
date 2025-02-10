import java.util.*;
import java.io.*;

class Main {

    private static int[] colors;    // 이분탐색: 두가지 색상을 저장할 배열(-1, 1)
    private static List<Integer>[] graph;
    private static boolean flag; // 이분그래프 여부를 저장

    private static void dfs(int vertex, int color) {
        colors[vertex] = color;

        for (int v : graph[vertex]) {
            if (colors[v] == color) {
                // 시작정점의 색(color)와 인접 정점의 색이 같으면 이분 그래프가 아님
                flag = false;
                return;
            }

            if (colors[v] == 0) {   // 아직 방문하지 않은 정점이면 dfs
                dfs(v, color * -1);   // 현재 색과 다른 색을 넣어준다
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            colors = new int[V + 1];    // 방문하지 않은 정점의 색 = 0
            graph = new List[V + 1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            flag = true;    // 이분그래프 여부 판별을 하기 때문에 초기값은 true

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            }   // 양방향

            for (int i = 1; i <= V; i++) {

                if (!flag) break;    // 이분그래프가 아니면 반복문 그만

                if (colors[i] == 0) {   // 아직 방문하지 않은 정점
                    dfs(i, -1); // 시작점을 -1 색깔로 넣는다
                }
            }

            sb.append(flag ? "YES" : "NO").append("\n");
        }

        System.out.println(sb);
    }
}
