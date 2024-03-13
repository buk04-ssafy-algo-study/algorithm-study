/*
 * <풀기 전>
 * 1. 인접 행렬
 * 2. i보다 큰 수 dfs
 * 3. i보다 작은 수 dfs
 * 	3-1. 인접행렬에서 특정 열만 검사하면서
 * 
 * <참고 코드>
 * 1. 최단경로 : 플로이드워셜
 * 2. 특정 노드보다 작은 경우 adj[][], 특정 노드보다 큰 경우 reverse_adj[][]
 * 3. adj, reverse_adj에서 모두 1이 아닌 경우 : 도달할 수 없는 노드(무게 관계를 알 수 없는 노드)이므로 카운트
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_10159 {
	static int n, m, adj[][], reverse_adj[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		adj = new int[n+1][n+1];
		reverse_adj = new int[n+1][n+1];

		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adj[from][to] = 1;
			reverse_adj[to][from] = 1;
		}

		for(int k=1;k<=n;k++) {
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					if(adj[i][k] == 1 && adj[k][j] ==1) {
						adj[i][j] = 1;
					}
					if(reverse_adj[i][k] == 1 && reverse_adj[k][j] ==1) {
						reverse_adj[i][j] = 1;
					}
				}
			}
		}
		
		for(int i=1;i<=n;i++) {
			int cnt = 0;
			for(int j=1;j<=n;j++) {
				if(i==j) continue;
				if(adj[i][j] != 1 && reverse_adj[i][j] != 1) 
					cnt++;
			}
			System.out.println(cnt);
		}
	}
}
