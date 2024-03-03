import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_테트로미노 {
	static int N, M;
	static int[][] arr;
	
	static int max = Integer.MIN_VALUE;
	static boolean[][] visit;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visit = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = true;
				dfs(i,j,arr[i][j],1);
				visit[i][j] = false;
				
			}
		}
		System.out.println(max);
	}
	static void dfs(int r, int c, int sum, int cnt) {
		if(cnt == 4) {
			max = Math.max(sum, max);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr < 0 || nr >=N || nc <0 || nc >=M) continue;
			if(!visit[nr][nc]) {
				
				if(cnt == 2) {
					visit[nr][nc] = true;
					dfs(r, c, sum + arr[nr][nc], cnt + 1);
					visit[nr][nc] = false;
				}
				
				visit[nr][nc] = true;
				dfs(nr, nc, sum + arr[nr][nc], cnt + 1);
				visit[nr][nc] = false;
			}
		}
	}
}