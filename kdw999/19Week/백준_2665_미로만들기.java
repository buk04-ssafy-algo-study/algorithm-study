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

public class 백준_2665_미로만들기 {

	static int N;
	static int[][] map;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int[][] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		dist = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			
			String line = br.readLine();
			
			for(int j=1; j<=N; j++) {
				map[i][j] = line.charAt(j-1)-48;
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
        
		dist[1][1] = 0;
        bfs(1, 1);
        System.out.println(dist[N][N]);
        
	} // main
	
	public static void bfs(int r, int c) {
		
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(r, c));
		
		while(!q.isEmpty()) {
			
			Pos p = q.poll();
			
		    for(int i=0; i<4; i++) {
		    	
		    	int nr = p.r + dr[i];
		    	int nc = p.c + dc[i];
		    	
		    	// 범위 벗어나면
		    	if(nr < 1 || nr > N || nc < 1 || nc > N) continue;
		    	
		    	if(dist[p.r][p.c] < dist[nr][nc]) {
		    		
		    		// 다음 칸이 벽이면
		    		if(map[nr][nc] == 0) dist[nr][nc] = dist[p.r][p.c]+1;
		    		
		    		// 다음 칸이 벽 아니면
		    		else dist[nr][nc] = dist[p.r][p.c];
		    		
		    		q.offer(new Pos(nr, nc));
		    	}
		    	
		    }
		}
	}
}
