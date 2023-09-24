package Week8;

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
public class 백준_2468_안전영역 {
	
	static int N;
	static int[][] map;
	static int maxHeight;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int maxArea=1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > maxHeight) maxHeight = map[i][j]; // 최대 높이 만큼 반복하기 위함
			}
		}
		
		bfs();
		System.out.println(maxArea);
	}
	
	public static void bfs() {
		
		// 모든 높이 돌면서 최대 영역 갯수 구하기
		for(int i=1; i<=maxHeight; i++) {
//			System.out.println("i : "+ i);
			int area = 0;
			boolean[][] visited = new boolean[N][N];
			Queue<Pos> q = new ArrayDeque<>();
			
			for(int x=0; x<N; x++) {
				for(int y=0; y<N; y++) {
					if(map[x][y] > i) {
						q.offer(new Pos(x, y));
//						System.out.println(map[x][y]);
					}
				}
			}
			
			while(!q.isEmpty()) {
				
				Pos p = q.poll();
				
				// 꺼낸 좌표가 이미 방문했던 곳이면 다음 탐색
				if(visited[p.r][p.c]) continue;
				visited[p.r][p.c] = true;
//				area++;
//				System.out.println("i : "+ i + " area : "+ area + " p.r : " + p.r + " / p.c : "+ p.c);
			 
				// 이어지는 영역을 탐색하기 위한 큐
				Queue<Pos> q2 = new ArrayDeque<>();
				q2.offer(p);
				
				while(true) {
					
					Pos p2 = q2.poll();
					
					for(int d=0; d<4; d++) {
						int nr = p2.r + dr[d];
						int nc = p2.c + dc[d];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						if(map[nr][nc] > i && !visited[nr][nc]) {
							visited[nr][nc] = true;
							q2.offer(new Pos(nr, nc)); // 이어지는 부분 계속 담고 탐색
						}
					}
					
					// 이어지는 부분없으면 while탈출하고 영역 갯수 +1
					if(q2.isEmpty()) {
						area++;
						break;
					}
				}
			}
			
			maxArea = Math.max(maxArea, area);
		}
	}
}
