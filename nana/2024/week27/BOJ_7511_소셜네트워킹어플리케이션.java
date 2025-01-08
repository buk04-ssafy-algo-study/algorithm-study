import java.io.*;
import java.util.*;

public class Main {

  private static StringBuilder sb;
  private static int n, k, m; // 유저 수, 친구 관계 수, 구해야하는 쌍
  private static int[] parent;

  private static void union(int a, int b) {
    a = find(a);
    b = find(b);

    if(a!=b)
      parent[b] = a;
  }

  private static int find(int a) {

    if (a == parent[a]) {
      return a;
    } else {
      return parent[a] = find(parent[a]);
    }

  }

  public static void main(String[] args) throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    sb = new StringBuilder();

    int T = Integer.parseInt(st.nextToken());

    for (int tc = 1; tc <= T; tc++) {
      sb.append("Scenario " + tc + ":").append("\n");

      n = Integer.parseInt(br.readLine());
      parent = new int[n];

      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }

      k = Integer.parseInt(br.readLine());

      for (int i = 0; i < k; i++) {
        st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        union(a, b);
      }

      m = Integer.parseInt(br.readLine());

      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());

        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        if (find(u) == find(v)) {
          sb.append(1).append("\n");
        } else {
          sb.append(0).append("\n");
        }
      }

      sb.append("\n");
    }

    System.out.println(sb);
  }
}