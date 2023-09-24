package Week10;

import java.util.*;
import java.io.*;
 
class Pos {
	int r;
	int c;
	
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class 백준_7576_토마토 {

	static int M, N;
	static int[][] map;
	static int cnt;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static Queue<Pos> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        
        int greenTomato =0;
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<M; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		if(map[i][j] == 0) greenTomato++;
        		if(map[i][j] == 1) {
        			q.offer(new Pos(i, j));
        		}
        	}
        }
        
        // 존재하는 토마토가 모두 익었을 경우
        if(greenTomato == 0) {
        	System.out.println(0);
        	return;
        }
        
        bfs();
        for(int i=0; i<N; i++) {
        	for(int j=0; j<M; j++) {
        		if(map[i][j] == 0) {
        			System.out.println(-1);
        			return;
        		}
        	}
        }
        
        // bfs메서드 안에 첫 번째 while문이 한 번더 돌아서 날짜가 1더 크게 나옴
        System.out.println(cnt-1);
    }
	
	public static void bfs() {
		
		while(!q.isEmpty()) {
			
			int qs = q.size();
			
			// 현재 담겨있는 익은 토마토들로만 4방향 안익은 토마토를 익게하기 위함
			while(qs > 0) {
				qs--;
				Pos p = q.poll();
			for(int i=0; i<4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				// 범위 체크
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == -1 ) continue;
				if(map[nr][nc] == 0) {
					map[nr][nc] = 1;
					q.offer(new Pos(nr, nc));
				}
					
			}
		  }
		   cnt++;
		}
		
	}
}