package Week15;

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

public class 백준_4179_불 {

	static int R, C, minTime=Integer.MAX_VALUE;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] map;
	static Queue<Pos> jq = new ArrayDeque<>();
	static Queue<Pos> fq = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'J') jq.offer(new Pos(i, j)); // 지훈이 시작 위치
				if(map[i][j] == 'F') fq.offer(new Pos(i, j)); // 불 초기 위치
			}
		}
		
		bfs();
	} // main
	
	public static void bfs() {
		
		boolean[][] visited = new boolean[R][C];
		
		int time=0;
		while(true) {
			time++;
			
			int jqSize = jq.size();
			int fqSize = fq.size();
			
			// 불 먼저 퍼져야 불이 지훈이를 덮치는 경우 배제 가능
			while(fqSize > 0) {
				fqSize--;
				
				Pos p = fq.poll();
				
				for(int i=0; i<4; i++) {
					int nr = p.r + dr[i];
					int nc = p.c + dc[i];
					
					if(nr < 0 || nr >= R || nc < 0 || nc >= C || 
							map[nr][nc] == '#' || map[nr][nc] == 'F') continue;
					
					// 벽이나 불이 아니라면 다 불로 지지기
					map[nr][nc] = 'F';
					fq.offer(new Pos(nr, nc));
				}
			}
			
			// 지후이 움직이기
			while(jqSize > 0) {
				jqSize--;
				
				Pos p = jq.poll();
				visited[p.r][p.c] = true;
				
				for(int i=0; i<4; i++) {
					int nr = p.r + dr[i];
					int nc = p.c + dc[i];
					
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
						System.out.println(time);
						return;
					};
					// 불, 벽, 방문했던 곳이면 다음 탐색
					if(map[nr][nc] == '#' || map[nr][nc] == 'F' || visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					jq.offer(new Pos(nr, nc));
				}
			}
			
			// 지훈이 탐색 후 큐가 비어있다면 탈출 불가능
			if(jq.isEmpty()) {
				System.out.println("IMPOSSIBLE");
				return;
			}
			
		}
		
	}

}
