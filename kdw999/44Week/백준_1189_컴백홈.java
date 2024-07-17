package Week44;

import java.io.*;
import java.util.*;

public class 백준_1189_컴백홈 {
	
	static int R, C, K, result;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		result = 0;
		
		map = new char[R][C];
		visited = new boolean[R][C];
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			
			for(int j=0; j<C; j++) {
				map[i][j] = line.charAt(j);
				
			}
		}
		
		dfs(R-1, 0, 0);
		System.out.println(result);
	}
	
    static void dfs(int r, int c, int cnt) {
    	
    	visited[r][c] = true;
    	cnt++;
    	
    	if(r == 0 && c == C-1) {
    		
    		if(cnt == K) {
    			result++;
    		}
    		return;
    	}
    	
    	for(int i=0; i<4; i++) {
    		
    		int nr = r + dr[i];
    		int nc = c + dc[i];
    		
    		if(nr >= R || nr < 0 || nc >=C || nc < 0) continue;
    		if(visited[nr][nc] || map[nr][nc] == 'T') continue;
    		
    		dfs(nr, nc, cnt);
    		visited[nr][nc] = false;
    	}
    }
}
