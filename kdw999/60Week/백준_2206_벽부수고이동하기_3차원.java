package Week60;

import java.io.*;
import java.util.*;

public class 백준_2206_벽부수고이동하기_3차원 {

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
	static int[][][] dist; 
	static boolean[][][] visited;
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		dist = new int[N+1][M+1][2];
	    visited = new boolean[N+1][M+1][2];
 
		for(int i=1; i<=N; i++) {
			String line = br.readLine();
			for(int j=1; j<=M; j++) {
				map[i][j] = line.charAt(j-1)-48;
			}
		}
		
		bfs();
		 
		if(dist[N][M][0] == 0 && dist[N][M][1] == 0) System.out.println(-1);
		else if(dist[N][M][0] == 0 || dist[N][M][1] == 0) System.out.println(Math.max(dist[N][M][0], dist[N][M][1]));
		else System.out.println(Math.min(dist[N][M][0], dist[N][M][1]));
	}
	
	static void bfs() {
		
		Queue<Point> q = new ArrayDeque<>();
		
		q.offer(new Point(1, 1, 1, true));
		dist[1][1][0] = 1;
		visited[1][1][0] = true;
		visited[1][1][1] = true;
		
		while(!q.isEmpty()) {
			
			Point p = q.poll();
	
			for(int i=0; i<4; i++) {
				
				int nr = dr[i] + p.r;
				int nc = dc[i] + p.c;
				
				int pdist = p.dist;
				boolean key = p.key;
				
				if(nr < 1 || nr > N || nc < 1 || nc > M) continue;
				
				if(map[nr][nc] == 0) {
					
				  // 벽을 부술 수 있는 상태, 벽 안부순 상태에 거리 저장
				  if(!visited[nr][nc][0] && key) {
					  
					  visited[nr][nc][0] = true;
					  dist[nr][nc][0] = pdist+1;
					  q.offer(new Point(nr, nc, pdist+1, true));
				  }
				  
				  // 벽을 부술 수 없는 상태, 벽 부순 상태에 거리 저장
				  else if(!visited[nr][nc][1] && !key) {
					  
					  visited[nr][nc][1] = true;
					  dist[nr][nc][1] = pdist+1;
					  q.offer(new Point(nr, nc, pdist+1, false));
				  }
				}
				
				// 벽을 부술 수 있고 다음 칸 벽을 부술 때
				else if(map[nr][nc] == 1 && key) {
					 
					if(!visited[nr][nc][1]) {
						
						visited[nr][nc][1] = true;
						dist[nr][nc][1] = pdist+1;
						q.offer(new Point(nr, nc, pdist+1, false));
					}
				}
			}
		}
	}
}
