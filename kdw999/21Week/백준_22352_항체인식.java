package Week21;

import java.util.*;
import java.io.*;

public class 백준_22352_항체인식 {

	static int[][] before;
	static int[][] after;
	static boolean[][] visited;
	static int N, M;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		before = new int[N][M]; // 주입 전 배열
		after = new int[N][M]; // 주입 후 배열
		visited = new boolean[N][M]; // 방문체크
		
		boolean flag = false; // 백신은 딱 한 번 주입, 체크용
		
		// 백신 주입 전 정보 저장
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				before[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 백신 주입 후 정보 저장
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				after[i][j] = Integer.parseInt(st.nextToken());
				
				// 접종 전/후의 값이 다르고 백신 투입을 한 번도 안했다면
				if(before[i][j] != after[i][j] && !flag) {
					flag = true;
					dfs(i, j, before[i][j], after[i][j]);
				}
			}
		}
		
		String result = "YES";
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(before[i][j] != after[i][j]) result = "NO"; // 항체 꽂은 후 탐색 완료 했는데도 전/후 배열의 값이 다르다면 백신이 안먹힌 것
			}
		}
		
		System.out.println(result);
		
	}
	
	public static void dfs(int r, int c, int beforeVal, int afterVal) {
		
		// 범위 벗어나면
		if(r < 0 || r >= N || c < 0 || c >= M) return;
		// 인접한 칸이 이미 방문한 칸이거나 같은 값이 아니라면
		if(visited[r][c] || before[r][c] != beforeVal) return;
		
		visited[r][c] = true;
		before[r][c] = afterVal;
		
		// 인접 4칸 탐색
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			dfs(nr, nc, beforeVal, afterVal);
		}
	}
}
