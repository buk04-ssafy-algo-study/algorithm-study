package Week60;

import java.io.*;
import java.util.*;

public class 백준_2206_벽부수고이동하기_2차원 {

	static class Point {
		int r, c, dist;
		boolean key;
		
		public Point(int r, int c, int dist, boolean key) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.key = key;
		}
	}
	
	static int[][] map;
	static int[][] break_wall, no_break_wall; 
	static boolean[][] break_visited, no_break_visited;
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		break_wall = new int[N+1][M+1];
		no_break_wall = new int[N+1][M+1];
		break_visited = new boolean[N+1][M+1];
		no_break_visited = new boolean[N+1][M+1];
 
		for(int i=1; i<=N; i++) {
			String line = br.readLine();
			for(int j=1; j<=M; j++) {
				map[i][j] = line.charAt(j-1)-48;
			}
		}
		
		bfs();
		 
		if(break_wall[N][M] == 0 && no_break_wall[N][M] == 0) System.out.println(-1);
		else if(break_wall[N][M] == 0 || no_break_wall[N][M] == 0) System.out.println(Math.max(break_wall[N][M], no_break_wall[N][M]));
		else System.out.println(Math.min(break_wall[N][M], no_break_wall[N][M]));
	}
	
	static void bfs() {
		
		Queue<Point> q = new ArrayDeque<>();
		
		q.offer(new Point(1, 1, 1, true));
		break_wall[1][1] = 1;
		no_break_wall[1][1] = 1;
		break_visited[1][1] = true;
		no_break_visited[1][1] = true;
		
		while(!q.isEmpty()) {
			
			Point p = q.poll();
	
			for(int i=0; i<4; i++) {
				
				int nr = dr[i] + p.r;
				int nc = dc[i] + p.c;
				
				int pdist = p.dist;
				boolean key = p.key;
				
				if(nr < 1 || nr > N || nc < 1 || nc > M) continue;
				
				if(map[nr][nc] == 0) {
					
					// 벽을 부순 상태
					if(!key) {
						if(!break_visited[nr][nc]) {
							
							break_visited[nr][nc] = true;
							break_wall[nr][nc] = pdist+1;
							q.offer(new Point(nr, nc, pdist+1, false));
						}
					}
					
					// 벽을 안부순 상태
					else {
                         if(!no_break_visited[nr][nc]) {
							
							no_break_visited[nr][nc] = true;
							no_break_wall[nr][nc] = pdist+1;
							q.offer(new Point(nr, nc, pdist+1, true));
						}
					}
				}
				
				else if(map[nr][nc] == 1 && key) {
					
					if(!break_visited[nr][nc] || break_wall[nr][nc] > pdist+1) {
						
						break_visited[nr][nc] = true;
						break_wall[nr][nc] = pdist+1;
						q.offer(new Point(nr, nc, pdist+1, false));
					}
				}
			}
		}
	}
}