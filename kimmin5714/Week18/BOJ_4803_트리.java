import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_4803_트리 {
	static int N, M, res;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int T = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            if (N == 0 && M == 0) break;
            
            tree = new ArrayList[N]; //인접리스트 
            visited = new boolean[N];
            
            for (int i = 0; i < N; i++) 
                tree[i] = new ArrayList<>();            

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                tree[a].add(b);
                tree[b].add(a);
            } 
            
            res = 0;
            for (int i = 0; i < N; i++) {
                if (!visited[i]) { //방문하지 않은 노드 트리 확인
                    visited[i] = true;
                    if (dfs(-1, i)) res++; //i의 인접리스트 확인하면서 트리인지 확인
                }
            }

            sb.append("Case ").append(T).append(": ");
            if (res == 0) {
                sb.append("No trees.\n");
            } else if (res == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append("A forest of ").append(res).append(" trees.\n");
            }
            T++;
        }

        System.out.print(sb);
    }
    public static boolean dfs(int root, int num) {
        for (int i : tree[num]) {
            if (i == root) continue;
            if (visited[i]) return false; //이미 방문한 경우 싸이클
            visited[i] = true; //방문
            if (!dfs(num, i)) return false; //num을 루트로하고 i의 인접 리스트 확인
        }
        return true;
    }
}

