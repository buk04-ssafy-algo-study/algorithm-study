package Week14;

import java.util.*;
import java.io.*;

class Pos {
	int r;
	int c;
	int dist;
	
	public Pos(int r, int c, int dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}
}

public class 백준_17836_공주님을구해라 {
	
	static int[] dr = {1, 0, 0, -1};
	static int[] dc = {0, 1, -1, 0};
	static int[][] map;
	static boolean[][] visited;
	static int N, M, T;
	static int arriveTime = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
	    N = Integer.parseInt(st.nextToken()); // 행
	    M = Integer.parseInt(st.nextToken()); // 열
	    T = Integer.parseInt(st.nextToken()); // 시간
	    
	    map = new int[N+1][M+1];
	    visited = new boolean[N+1][M+1];
	    
	    for(int i=1; i<=N; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=1; j<=M; j++) {
	    		map[i][j] = Integer.parseInt(st.nextToken());
	    	}
	    }
	    
	    bfs();
	    
	    if(arriveTime > T) System.out.println("Fail");
	    else System.out.println(arriveTime);

	}// main
	
	public static void bfs() {
		
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(1, 1, 0));
		visited[1][1] = true;
		
		while(!q.isEmpty()) {
			
			Pos p = q.poll();
			
			// 현재 칸에 성검 그람을 찾았다면 현재 지점에서 도착 지점까지의 거리 재기
			if(map[p.r][p.c] == 2) {
				
				arriveTime = Math.abs(N-p.r) + Math.abs(M-p.c); // 도착점까지 남은 거리
				arriveTime += p.dist; // 검 만나기 전까지의 거리
				
			}
			
			// 검 없이 공주 만나면
			if(p.r == N && p.c == M) {
				visited[p.r][p.c]= true;
				
				if(arriveTime > p.dist) arriveTime = p.dist; // 이전 도착시간 보다 현재 도착시간이 더 빠르다면
				break;
			}
			
			// 4방 탐색
			for(int i=0; i<4; i++) {
				
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
					if(nr < 1 || nr > N || nc < 1 || nc > M 
							|| visited[nr][nc] || map[nr][nc] == 1) continue;
					
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc, p.dist+1));
			}
			
			
		}
	}
}
