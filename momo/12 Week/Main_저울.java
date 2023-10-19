import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_저울 {
    static int N, M;
    
    static int cnt[];
    static boolean[][] check;
    static List<Integer>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        check = new boolean[N+1][N+1];
        list = new ArrayList[N + 1];
        cnt = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
        }
        
      
        for (int i = 1; i <= N; i++) {
            check[i][i] = true;
            dfs(i, i);
        }

        for (int i = 1; i <= N; i++) {
            for(int j=1;j<=N;j++){
                if(!check[i][j]) cnt[i]++;
            }
            sb.append(cnt[i] + "\n"); 
        }

        System.out.println(sb);
    }
    private static void dfs(int start, int i) {
       
        if(list[i].size() == 0) {
            return;
        }

        for(int j:list[i]) {
            if(check[start][j]) continue;
            check[start][j] = true;
            check[j][start] = true;
            dfs(start, j);
        }
    }
}