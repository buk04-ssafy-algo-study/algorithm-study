package Week81;

import java.io.*;
import java.util.*;

public class 백준_16973_직사각형탈출 {
	
	static class Node {
		
		int r;
		int c;
		int move;
		
		public Node(int r, int c, int move) {
			this.r = r;
			this.c = c;
			this.move = move;
		}
	}
	
	static int N, M, H, W, sr, sc, fr, fc;
	static int result;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static List<Node> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		init();
		solve();
		if(result == 0) System.out.println(-1);
		else System.out.println(result);
	}
	
	private static void solve() {
		
		Queue<Node> q = new ArrayDeque<>();
		
		boolean[][] visited = new boolean[N+1][M+1];
		visited[sr][sc] = true;
		
		q.offer(new Node(sr, sc, 0));
		
		while(!q.isEmpty()) {
			
			Node n = q.poll();
			
			for(int i=0; i<4; i++) {
				
				int nr = n.r + dr[i];
				int nc = n.c + dc[i];
				
				// 이동할 직사각형의 범위 체크
				if(nr < 1 || nr > N || nc < 1 || nc > M) continue;
				if(nr+H-1 < 1 || nr+H-1 > N || nc+W-1 < 1 || nc+W-1 > M) continue;
				
				if(visited[nr][nc]) continue;
				if(!checkBlock(nr, nc)) continue;
				
				if(nr == fr && nc == fc) {
					result = n.move+1;
					return;
				}
				
				q.offer(new Node(nr, nc, n.move+1));
				visited[nr][nc] = true;
			}
		}
	}
	
	private static boolean checkBlock(int r, int c) {
		
		for(int i=0; i<list.size(); i++) {
			
			Node n = list.get(i);
			
			// 이동한 직사각형이 벽에 막힐 경우
			if(r <= n.r && n.r <= r+H-1 && 
			   c <= n.c && n.c <= c+W-1) return false;
		}
		return true;
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];

		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=1; j<=M; j++) {
				int n = Integer.parseInt(st.nextToken());
				
				map[i][j] = n;
				if(n == 1) list.add(new Node(i, j, 0)); 
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		result = 0;
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		fr = Integer.parseInt(st.nextToken());
		fc = Integer.parseInt(st.nextToken());
		
	}
}
