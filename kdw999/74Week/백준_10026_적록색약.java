package Week74;

import java.io.*;
import java.util.*;

public class 백준_10026_적록색약 {

	static int N;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static char[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {

		init();
		solve();
	 }
	
	private static void solve() {
	
		int notBlind = 0;
		int blind = 0;
	
		// 적록색약 아닌 사람 구역
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					notBlind++;
					bfs(i, j, false);
				}
			}
		}
		
		visited = new boolean[N][N];
		
		// 적록색약인 사람 구역
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					blind++;
					bfs(i, j, true);
				}
			}
		}
		
		System.out.println(notBlind+" "+blind);
	}
	
	private static void bfs(int r, int c, boolean blind) {
		
		// 한 색의 이어지는 영역을 방문 탐색해주면서 몇 개의 구역이 있는지 찾음
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			
			int[] qp = q.poll();
			
			for(int i=0; i<4; i++) {
				
			int nr = qp[0] + dr[i];
			int nc = qp[1] + dc[i];
			
			char curColor = map[qp[0]][qp[1]];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if(visited[nr][nc]) continue;
			
			// 색약이 아닌 경우 다음 칸이 현재 칸과 다른 색이면 같은 구역이 아님
			if(!blind && map[nr][nc] != curColor) continue;
			
			// 색약인 경우 현재 칸이 파랑이냐 빨강 초록이냐에 따라 구역 판별해줌
			if(blind) {
				if(curColor == 'B' && curColor != map[nr][nc] ) continue;
				else if((curColor == 'R' || curColor == 'G') && map[nr][nc] == 'B') continue;
			}
			
			q.offer(new int[] {nr, nc});
			visited[nr][nc] = true;
			
			}
		}
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = line.charAt(j);
			}
		}
	}
}
