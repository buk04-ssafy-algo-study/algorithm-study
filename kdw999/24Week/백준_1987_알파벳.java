package Week24;

import java.io.*;
import java.util.*;

public class 백준_1987_알파벳 {

	static int R, C;
	static boolean[] visited;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());;
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		visited = new boolean[26]; // 방문한 알파벳인지 체크
		map = new int[R][C];
		result = 1;
		
		for(int i=0; i < R; i++) {
			String line = br.readLine();
			for(int j=0; j< C; j++) {
				map[i][j] = line.charAt(j) - 'A';
			}
		}
		
		dfs(0, 0, 0);
		
		System.out.println(result);
		

	}
	
	public static void dfs(int r, int c, int cnt) {
		
		if(visited[map[r][c]]) { // map[r][c]에 있는 알파벳을 이미 방문했다면
			result = Math.max(result, cnt); // 최대 이동 횟수 갱신
			return;
		}
		else {
			
			visited[map[r][c]] = true; // 방문 체크
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr >=0 && nc >=0 && nr < R && nc < C) dfs(nr, nc, cnt+1);
			}
			
			visited[map[r][c]] = false;
		}
	}
}
