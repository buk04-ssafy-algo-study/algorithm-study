import java.util.*;
import java.io.*;

class Main {

    private static int n, k, target;
    private static List<Integer>[] tree;
    private static boolean[] isVisited;

    private static void dfs(int index, int count) {

        if (index == target) {
            System.out.println(count);
            System.exit(0); // 찾으면 프로그램 종료
        }

        for (int edge : tree[index]) {
            if (!isVisited[edge]) {
                isVisited[edge] = true;
                dfs(edge, count + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        tree = new List[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
        }

        // 정점에 부여된 값이 k인 정점의 깊이 찾기
        // k 값과 같은 index를 찾아서 target 변수로 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(st.nextToken());
            if (now == k) {
                target = i;
                break;
            }
        }

        isVisited = new boolean[n];
        isVisited[0] = true;
        dfs(0, 0);  // 모든 루트는 0
    }
}
