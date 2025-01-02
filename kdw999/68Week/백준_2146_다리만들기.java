package Week68;

import java.io.*;
import java.util.*;

public class 백준_2146_다리만들기 {
  
	static class Point {
		
		int r, c;
		int dist;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public Point(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	 
	}
	
	static int N, num, minDist = 987654321;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
	    
		init();
		separateIsland();
		searchContinent();
		System.out.println(minDist);
  }
  
  static private void searchContinent() {
	  
	  for(int i=0; i<N; i++) {
		  for(int j=0; j<N; j++) {
		 
			  if(map[i][j] != 0) {
				  visited = new boolean[N][N];
				  bfs(i, j, map[i][j]);
			  }
		  }
	  }
  }
	
  static private void separateIsland() {
	  
	  for(int i=0; i<N; i++) {
		  for(int j=0; j<N; j++) {
			  if(map[i][j] == -1) {
				  bfs(i, j);
				  num++;
			  }
		  }
	  }
  }
  
  // 다른 섬에 도달하는 거리 계산
  static private void bfs(int r, int c, int curNum) {
	  
	  Queue<Point> q = new ArrayDeque<>();
	  q.offer(new Point(r, c, 0));
	  visited[r][c] = true;
	  while(!q.isEmpty()) {
		  
		  Point p = q.poll();
		  
		  if(map[p.r][p.c] != 0 && map[p.r][p.c] != curNum) {
			  minDist = Math.min(minDist, p.dist-1);
			  return;
		  }
		  
		  for(int i=0; i<4; i++) {
			  
			  int nr = p.r + dr[i];
			  int nc = p.c + dc[i];
			  
			  if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			  if(map[nr][nc] == curNum || visited[nr][nc]) continue;
			 
			  q.offer(new Point(nr, nc, p.dist+1));
			  visited[nr][nc] = true;
		  }
	  }
  }

  // 각 섬 마다 번호 부여
  static private void bfs(int r, int c) {
	  
	  Queue<Point> q = new ArrayDeque<>();
	  q.offer(new Point(r, c));
	  map[r][c] = num;
	  
	  while(!q.isEmpty()) {
		  
		  Point p = q.poll();
		  
		  for(int i=0; i<4; i++) {
			  
			  int nr = p.r + dr[i];
			  int nc = p.c + dc[i];
			  
			  if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			  if(map[nr][nc] == -1) {
				  q.offer(new Point(nr, nc));
				  map[nr][nc] = num;
			  }
              
		  }
	  }
  }
	
  static private void init() throws IOException {
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  N = Integer.parseInt(br.readLine());
	  num = 1;
	  
	  StringTokenizer st;
	  map = new int[N][N];
	  visited = new boolean[N][N];
	  
	  for(int i=0; i<N; i++) {
		  st = new StringTokenizer(br.readLine());
		  for(int j=0; j<N; j++) {
			  if(Integer.parseInt(st.nextToken()) == 1) map[i][j] = -1;
			  else map[i][j] = 0;
		  }
	  }
  }
}