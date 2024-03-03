// https://www.acmicpc.net/problem/14716
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] banner;
	static int[][] dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	static int TEXT = 1;
	static int BLANK = 0;
	static Queue<int[]> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int answer = 0;

		// input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		banner = new int[N][M];

		for(int r=0;r<N;r++){
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<M;c++){
				banner[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// count
		for(int r=0;r<N;r++) {
			for(int c=0;c<M;c++) {
				if(banner[r][c]==BLANK) continue;
				bfs(r, c);
				answer++;
			}
		}
		
		// output
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	static void bfs(int r, int c){
		queue.offer(new int[]{r, c});
		banner[r][c] = BLANK; // visited;

		while(!queue.isEmpty()){
			int[] now = queue.poll();
			r = now[0];
			c = now[1];

			for(int d=0;d<8;d++){
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				if(0<=nr && nr<N && 0<=nc && nc<M && banner[nr][nc]==TEXT) {
					queue.offer(new int[]{nr, nc});
					banner[nr][nc] = BLANK; // visited
				}
			}
		}
	}
}
