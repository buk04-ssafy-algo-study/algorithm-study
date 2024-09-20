import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18430 {
    static int N, M, res;
    static int[][] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++)
                tree[i][j] = Integer.parseInt(st.nextToken());
        }

        boolean[][] visited = new boolean[N][M];
        dfs(0,0, visited );
        System.out.print(res);
    }

    private static void dfs(int index, int sum, boolean[][] visited) {
        if(index == N*M){ // tree배열 끝까지 탐색한 경우
            res = Math.max(res, sum); // 최댓값 갱신
            return;
        }

        int r = index / M; // 행
        int c = index % M; // 열

        dfs(index+1, sum, visited); // 해당 index 칸을 방문하지 않을 경우

        // 해당 index 칸을 방문할 경우
        if(!visited[r][c]) { // 현재 칸에 방문할 수 있는지 확인
            if(c-1>=0 && r+1<N && !visited[r+1][c] && !visited[r][c-1]){
                visited[r][c-1] = true;
                visited[r+1][c] = true;
                visited[r][c] = true;
                dfs(index+1, sum+tree[r][c-1]+tree[r+1][c]+tree[r][c]*2, visited);
                visited[r][c-1] = false;
                visited[r+1][c] = false;
                visited[r][c] = false;
            }
            if(c-1>=0 && r-1>=0 && !visited[r][c-1] && !visited[r-1][c]){
                visited[r][c-1] = true;
                visited[r-1][c] = true;
                visited[r][c] = true;
                dfs(index+1, sum+tree[r][c-1]+tree[r-1][c]+tree[r][c]*2, visited);
                visited[r][c-1] = false;
                visited[r-1][c] = false;
                visited[r][c] = false;
            }
            if(r-1>=0 && c+1<M && !visited[r-1][c] && !visited[r][c+1]){
                visited[r][c+1] = true;
                visited[r-1][c] = true;
                visited[r][c] = true;
                dfs(index+1, sum+tree[r][c+1]+tree[r-1][c]+tree[r][c]*2, visited);
                visited[r][c+1] = false;
                visited[r-1][c] = false;
                visited[r][c] = false;
            }
            if(r+1<N && c+1<M && !visited[r+1][c] && !visited[r][c+1]){
                visited[r][c+1] = true;
                visited[r+1][c] = true;
                visited[r][c] = true;
                dfs(index+1, sum+tree[r][c+1]+tree[r+1][c]+tree[r][c]*2, visited);
                visited[r][c+1] = false;
                visited[r+1][c] = false;
                visited[r][c] = false;
            }
        }
    }
}
