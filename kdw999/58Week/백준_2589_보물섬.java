package Week58;

import java.util.*;
import java.io.*;

public class 백준_2589_보물섬 {
	
	static class Node {
		
		int r, c, dist;
		
		public Node(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}

	static int R, C, maxDist;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		maxDist = 0;
		
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'L') {
					visited = new boolean[R][C];
					bfs(i, j);
				}
			}
		}
		System.out.println(maxDist);
	}
	
	public static void bfs(int r, int c) {
		 
		Queue<Node> q = new ArrayDeque<>();
		
		q.offer(new Node(r, c, 0));
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			
			Node n = q.poll();
			if(n.dist > maxDist) maxDist = n.dist;
			
			for(int i=0; i<4; i++) {
				
				int nr = n.r + dr[i];
				int nc = n.c + dc[i];
				
			    if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			    if(visited[nr][nc] || map[nr][nc] == 'W') continue;
			    	
			    visited[nr][nc] = true;
			    q.offer(new Node(nr, nc, n.dist+1));
			}
		}
	}
}
