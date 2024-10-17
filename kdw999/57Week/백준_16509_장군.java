package Week57;

import java.io.*;
import java.util.*;

public class 백준_16509_장군 {
	
	static class Pos {
		int r;
		int c;
		int cnt;
		
		public Pos(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int[][] dr = {
			{0, -1, -1}, {0, 1, 1},
			{1, 1, 1}, {1, 1, 1},
			{0, 1, 1}, {0, -1, -1},
			{-1, -1, -1}, {-1, -1, -1}
	};
	static int[][] dc = {
			{-1, -1, -1}, {-1, -1, -1},
			{0, -1, -1}, {0, 1, 1},
			{1, 1, 1}, {1, 1, 1},
			{0, 1, 1}, {0, -1, -1}
	};
	static boolean[][] visited = new boolean[10][9];
	static int R1, C1, R2, C2;
	static int minMove = 987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 상
		R1 = Integer.parseInt(st.nextToken());
		C1 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		// 왕
		R2 = Integer.parseInt(st.nextToken());
		C2 = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs());
	}
	
	public static int bfs() {
		
		Queue<Pos> q = new ArrayDeque<>();
		
		visited[R1][C1] = true;
		q.offer(new Pos(R1, C1, 0));
		
		while(!q.isEmpty()) {
			
			Pos p = q.poll();
			
		for(int i=0; i<8; i++) {
			
			int nr = p.r;
			int nc = p.c;
			int cnt = p.cnt;
			
			for(int j=0; j<3; j++) {
				
				nr += dr[i][j];
				nc += dc[i][j];
					
				if(nr < 0 || nr >= 10 || nc < 0 || nc >= 9) continue;
				
				// 해당 지점을 먼저 방문한 상이 있으면 이후에 도착한 상은 최소횟수가 될 수 없음
				if(j == 2 && visited[nr][nc]) continue;
				
				// 도차 지점에서 왕 만나면
				if(j == 2 && nr == R2 && nc == C2) {return cnt+1;}
				
				// 도착 지점에 도달 시
				if(j == 2) {
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc, cnt+1));
					continue;
				}
				
				// 도착 지점이 아닌 이동 진행 중에 왕 만나면 다음 상 움직임 탐색
				if(nr == R2 && nc == C2) {
					break;
				}
				
			}
		  }
		}
		return -1;
	}
}
