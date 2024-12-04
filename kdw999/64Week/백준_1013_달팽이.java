package Week64;

import java.io.*;

public class 백준_1013_달팽이 {

//	static int[] dir = {0, 1, 2, 3};
	static int[][] map;
	static boolean[][] visited;
	static int N, point, start, r, c, dir, gr, gc;
	
	public static void main(String[] args) throws IOException {
		
		init();
		solve();
		print();
	}
	
	static void solve() {
		
		// 0하, 1우, 2상, 3좌
		while(true) {
			
			if(start==1) {
				map[r][c] = 1;
				if(point == 1) {
					gr = r;
					gc = c;
				}
				return;
			}
			
			if(start==point) {
				gr = r;
				gc = c;
			}
			
			// 맵에 값 저장
			map[r][c] = start;
			visited[r][c] = true;
			
			// 방향에 따른 달팽이 이동
			move();
			 
		}
	}
	
	static void move() {
		
		if(dir==0) {
			if(r+1 > N || visited[r+1][c]) {
				dir++;
				return;
			}
			r++;
			start--;
		}
		else if(dir==1) {
			if(c+1 > N || visited[r][c+1]) {
				dir++;
				return;
			}
			c++;
			start--;
		}
		else if(dir==2) {
			if(r-1 <= 0 || visited[r-1][c]) {
				dir++;
				return;
			}
			r--;
			start--;
		}
		else {
			if(c-1 <= 0|| visited[r][c-1]) {
				dir=0;
				return;
			}
			c--;
			start--;
		}
 
	}
	
	static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		point = Integer.parseInt(br.readLine());
		
		start = N*N;
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
		
		r = 1;
		c = 1;
	}
	
	static void print() {
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println(gr+" "+gc);
	}
}
