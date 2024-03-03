import java.io.*;
import java.util.*;

public class Main_4803_트리 {
    private static int n, m;
    private static int treeCount;
    private static boolean end;
    private static List<Integer>[] tree;
    private static boolean[] isVisited;

    private static void dfs(int index, int pre) {

        isVisited[index] = true;

        for (int i = 0; i < tree[index].size(); i++) {
            if (!isVisited[tree[index].get(i)]) {
                // 방문하지 않은 곳이라면 재귀 (현재 위치를 pre 매개변수로 준다)
                dfs(tree[index].get(i), index);
            } else{
                // 트리가 단방향인 경우 이 조건문까지 닿지 못한다.
                // 방문을 한 정점인데 바로 이전 정점과 다르다면
                if(tree[index].get(i) != pre)
                    end= true;  // 끝까지 도착한 것
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int testCase = 1;

        while (true) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            sb.append("Case " + testCase++ + ": ");

            // Test Case
            treeCount = 0;
            tree = new ArrayList[n + 1];
            isVisited = new boolean[n + 1];

            // Make List
            for (int i = 1; i <= n; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                tree[a].add(b);
                tree[b].add(a);
            }

            for (int i = 1; i <= n; i++) {
                end = false;
                if (!isVisited[i]) {
                    dfs(i, 0);
                    if (!end)   // 끝까지 가서 트리가 되었다면
                        treeCount++;
                }
            }

            if (treeCount == 0) {
                sb.append("No trees.");
            } else if (treeCount == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("A forest of " + treeCount + " trees.");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
