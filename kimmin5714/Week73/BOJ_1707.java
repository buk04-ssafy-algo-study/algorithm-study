import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1707 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int[] vColor = new int[V + 1];

            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                adj.add(new ArrayList<>());
            }

            for (int e = 0; e < E; e++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                adj.get(u).add(v); // 인접 그래프
                adj.get(v).add(u);
            }

            boolean res = true;
            for (int i = 1; i <= V; i++) { // 모든 정점 탐색
                if (!res) break;
                if (vColor[i] != 0) continue; // 이미 방문한 곳이면 넘어감

                Queue<Integer> q = new ArrayDeque<>();
                q.offer(i);
                vColor[i] = 1;

                while (!q.isEmpty()) {
                    int cur = q.poll();

                    for (int j = 0; j < adj.get(cur).size(); j++) {
                        int next = adj.get(cur).get(j);
                        if (vColor[next] == vColor[cur]) { // 인접한 곳이 현재와 같은 수라면 이분 그래프가 아님
                            res = false;
                            break;
                        }
                        if (vColor[next] == 0) { // 인접한 곳이 방문하지 않은 곳일 때 업데이트
                            vColor[next] = vColor[cur] * (-1); // 현재 수와 반대인 수 저장
                            q.offer(next);
                        }
                    }
                    if (!res) break;
                }
                if (!res) break;
            }
            if (res) {
                sb.append("YES" + "\n");
            } else {
                sb.append("NO" + "\n");
            }
        }
        System.out.print(sb);
    }
}
