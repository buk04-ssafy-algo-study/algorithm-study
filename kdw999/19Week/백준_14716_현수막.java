package Week19;

import java.util.*;
import java.io.*;

class Pos{
	int r;
	int c;
	
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class 백준_14716_현수막 {

	static int[] dr = {1, -1, 0, 0, -1, -1, 1, 1};
    static int[] dc = {0, 0, 1, -1, -1, 1, 1, -1};
	static int M, N;
	static int[][] map;
	static boolean[][] visited;
	static int makeWord;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		visited = new boolean[M][N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) bfs(i, j);
			}
		}
		
		System.out.println(makeWord);
	} // main
	
	public static void bfs(int r, int c) {
		
		visited[r][c] = true;
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(r, c));
		
		
		while(!q.isEmpty()) {
			
			Pos p = q.poll();
			
			// 8방향 탐색
			for(int i=0; i<8; i++) {
				
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(nr < 0 || nr >= M || nc < 0 || nc >= N || map[nr][nc] == 0 || visited[nr][nc]) continue; 
				
				q.offer(new Pos(nr, nc));
				visited[nr][nc] = true;
			}
		}
		
		makeWord++;
	}
}
