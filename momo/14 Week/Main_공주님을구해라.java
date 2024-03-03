import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_공주님을구해라 {
	static int N,M,T;
	static int[][] map;
	static boolean[][][] visit;
	
	static int cnt = 0;
	static int ans = Integer.MAX_VALUE;
	
	static int[] dr = new int[]{-1,1,0,0};
	static int[] dc = new int[]{0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		visit = new boolean[N+1][M+1][2];
		
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		if(ans == Integer.MAX_VALUE) {
			System.out.println("Fail");
		}else {
			System.out.println(ans);
		}
	}
	
	
	static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {1,1,0,0}); // r, c, cnt, gram
		visit[1][1][0] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				// 공주랑 만났는지 체크
				if(r == N && c == M) {
					if(cur[2] <= T) {
						ans = Math.min(ans, cur[2]);
					}
					return;
				}
				
				// 만약 칼을 먹었으면 gram값을 1로 설정
				if(map[r][c] == 2) cur[3] = 1;
				
				for (int j = 0; j < 4; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];
					
					// 경계 체크
					if(nr < 1 || nr > N  || nc < 1 || nc > M) continue;
					
					// 그램 소유 여부 체크
					if(cur[3] == 1) {
						// 그램 소유했을 때 방문 체크
						if(visit[nr][nc][1]) continue;
							q.offer(new int[] {nr,nc,cur[2] + 1, 1});
							visit[nr][nc][1] = true;
					}else {
						if(visit[nr][nc][0]) continue;
						if(map[nr][nc] == 1) continue;
						q.offer(new int[] {nr,nc,cur[2] + 1, 0});
						visit[nr][nc][0] = true;
					}
				}
			}
		}
		
		
	}
	
	
	
	
}
