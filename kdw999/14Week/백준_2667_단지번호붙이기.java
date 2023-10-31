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

public class Main {
	
	static char[][] map; // 아파트 맵
	static boolean[][] visited; // 방문한 단지 체크
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N;
	static int cnt;
	static int cntApt;
	static List<Integer> house; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 맵 크기
		
		map = new char[N][N];
		visited = new boolean[N][N];
		cnt = 0;
	
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		house = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && map[i][j] == '1') {
					cntApt=1;
					bfs(i,j);
				}
			}
		}
		
		System.out.println(house.size());
		Collections.sort(house); 
		
		for(int n : house) System.out.println(n);
		
	} // main
	
	public static void bfs(int r, int c) {
		
		Queue<Pos> q = new ArrayDeque<>();
		
		q.offer(new Pos(r, c));
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			
			Pos p = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				// 범위 벗어나면
				if(nr < 0 || nr>=N || nc < 0 || nc >= N || visited[nr][nc]) continue;
				
				// 아파트 단지라면
				if(map[nr][nc] == '1') {
					cntApt++;
					q.offer(new Pos(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
		
		house.add(cntApt);
		cnt++;
	}
}