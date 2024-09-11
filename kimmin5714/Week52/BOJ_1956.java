import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1956 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        long[][] adj = new long[V][V]; // 인접 행렬
        for(int i=0;i<V;i++)
            Arrays.fill(adj[i], 10000*400*399); // 최댓값으로 초기화 (최대 가중치 * 간선 개수)

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());

            adj[a][b] = c;
        }

        // 플로이드 워셜 : 두 노드 사이에 존재하는 노드를 통해 모든 노드의 최소 가중치를 구하는 알고리즘
        for(int k=0;k<V;k++){
            for(int i=0;i<V;i++){
                for(int j=0;j<V;j++){
                    long sum = adj[i][k] + adj[k][j];
                    if(Math.abs(sum) > 10000*400*399)
                        sum = 10000*400*399;
                    adj[i][j] = Math.min(adj[i][j], sum);
                }
            }
        }
        long res = 10000*400*399;
        for(int i=0;i<V;i++) 
            res = Math.min(res,adj[i][i]); // 싸이클이 되어야 하므로 자기 자신만 탐색

        if(res == 10000*400*399) // 경로 못찾는 경우
            res = -1;
        System.out.print(res);
    }
}
