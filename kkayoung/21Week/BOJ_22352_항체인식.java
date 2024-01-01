// https://www.acmicpc.net/problem/22352
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] before, after;
	static boolean[][] visited;
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; // u d l r
	static boolean chance = true; // 백신 투입 기회가 남아있나?

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		before = new int[N][M];
		after = new int[N][M];
		visited = new boolean[N][M];

		// input
		for(int i=0;i<2*N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				if(i<N) before[i][j] = Integer.parseInt(st.nextToken()); // 백신 전
				else after[i-N][j] = Integer.parseInt(st.nextToken());   // 백신 후
			}
		}

		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(before[i][j] != after[i][j]) {
					if(chance) { // 백신 투입 전,후가 다르고, 투입 기회가 남아있으면
						dfs(i, j, after[i][j]); // 값 업데이트
						chance = false;
					}
				}
			}
		}

		// output
		bw.write(yesorno());
		bw.flush();
		bw.close();
	}

	static String yesorno() { // 전, 후 다른 칸이 존재하면 NO, 그렇지 않으면 YES 리턴
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(before[i][j] != after[i][j]) return "NO";
			}
		}
		return "YES";
	}

	static void dfs(int r, int c, int v) {
		visited[r][c] = true;

		for(int i=0;i<4;i++) {
			int nr = r+dir[i][0];
			int nc = c+dir[i][1];

			if(0<=nr && nr<N && 0<=nc && nc<M && !visited[nr][nc] && before[r][c]==before[nr][nc]) {
				dfs(nr, nc, v);
			}
		}
		before[r][c] = v;
	}
}
