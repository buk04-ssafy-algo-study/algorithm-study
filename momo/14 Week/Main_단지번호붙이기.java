import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Main_단지번호붙이기 {
	static int N;
	static int[][] map;
	static boolean[][] visit;
	
	static int cnt;
	static List<Integer> list = new ArrayList<>();
	
	static int[] dr = new int[] {-1,1,0,0};
	static int[] dc = new int[] {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visit[i][j] || map[i][j] == 0) continue;
				bfs(new int[] {i,j});
				cnt++;
			}
		}
		sb.append(cnt + "\n");
		Collections.sort(list);
		for (int i : list) {
			sb.append(i + "\n");
		}
		System.out.println(sb);
		
	}
	
	static void bfs(int[] pos) {
		Queue<int[]> q = new ArrayDeque<>();
		int ans = 1;
		q.offer(new int[] {pos[0],pos[1]});
		visit[pos[0]][pos[1]] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				// 경계체크
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				// 0인지 체크
				if(map[nr][nc] == 0) continue;
				
				// 방문 했는지 체크
				if(visit[nr][nc]) continue;
				
				q.offer(new int[] {nr, nc});
				visit[nr][nc] = true;
				ans++;
			}
		}
		list.add(ans);
	}
}
