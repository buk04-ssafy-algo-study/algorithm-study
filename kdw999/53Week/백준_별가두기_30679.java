package Week53;

import java.io.*;
import java.util.*;

public class 백준_별가두기_30679 {

    static int N,M;
	static int[][] map;
	static Queue<Integer> q;
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
       M = Integer.parseInt(st.nextToken());
       map = new int[N][M];
       for (int i = 0; i < N; i++) {
           st = new StringTokenizer(br.readLine());
           for (int j = 0; j < M; j++) {
               map[i][j] = Integer.parseInt(st.nextToken());
           }
       }
		
       q = new ArrayDeque<>();
       
       for (int i = 0; i < N; i++) {   
           boolean[][][] isVisited = new boolean[N][M][4];
           isVisited[i][0][0] = true;
           dfs(i, i, 0, 0, isVisited);
           isVisited[i][0][0] = false;
       }
       
       sb.append(q.size()).append("\n");
       while (!q.isEmpty()) sb.append(q.poll()+1).append(" ");
       System.out.println(sb);
	}
	
	static void dfs(int sr, int r, int c, int dir, boolean[][][] isVisited) {
       int nr = r + dr[dir] * map[r][c];
       int nc = c + dc[dir] * map[r][c];
       
       
       if(0 <= r && r < N && 0 <= c && c < M) return;
       
       int ndir = (dir + 1) % 4;
       if (isVisited[nr][nc][ndir]) {  
           q.offer(sr);
           return;
       }
       
       isVisited[nr][nc][ndir] = true;
       dfs(sr, nr, nc, ndir, isVisited);
       isVisited[nr][nc][ndir] = false;
   }
}
