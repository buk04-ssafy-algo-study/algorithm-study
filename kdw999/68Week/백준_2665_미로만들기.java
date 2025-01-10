package Week68;

import java.io.*;
import java.util.*;

public class 백준_2665_미로만들기 {

	static class Room {
		int r, c, num, change;
		
		public Room (int r, int c, int num, int change) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.change = change;
		}
	}
	
	static int n;
	static Room[][] map;
	static int[][] dist;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		
	   init(); // 초기화 
	   BFS(); // 맵 탐색
	   System.out.println(dist[n-1][n-1]); // 도착 지점 횟수 출력
	}
	
	private static void BFS() {
		
		Queue<Room> q = new ArrayDeque<>();
		
		q.offer(new Room(0, 0, 1, 0));
		dist[0][0] = 0;
		
		while(!q.isEmpty()) {
			
			Room r = q.poll();
			
			for(int i=0; i<4; i++) {
				
				int nr = dr[i] + r.r;
				int nc = dc[i] + r.c;
				
				if(nr < 0 || nr >=n || nc < 0 || nc >= n) continue;
				
				// 다음 칸이 검은 방
				if(map[nr][nc].num == 0) {
					if(r.change+1 < dist[nr][nc]) {
						q.offer(new Room(nr, nc, 0, r.change+1));
						dist[nr][nc] = r.change+1;
					}
					
				}
				
				// 다음 칸이 흰 방 
				else {
					if(r.change < dist[nr][nc]) {
						q.offer(new Room(nr, nc, 1, r.change));
						dist[nr][nc] = r.change;
					}
				}
				
			}
		}
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new Room[n][n];
		dist = new int[n][n];
		
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			for(int j=0; j<n; j++) {
			   map[i][j] = new Room(i, j, line.charAt(j)-48, 0);
			}
		}
		
		for(int i=0; i<dist.length; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
	}
}
