package Week17;

import java.util.*;
import java.io.*;

class Turn {
	int r;
	int c;
	int dir;
	
	public Turn(int r, int c, int dir) {
		this.r = r;
		this.c = c;
		this.dir = dir;
	}
	
	public Turn(int r, int c) {
		this.r = r ;
		this.c = c;
	}
}

public class 백준_20165_인내의도미노장인호석 {
	
	static int N, M, R;
	static int[][] map;
	static Turn[] atk, dfs;
	static int atkPts=0;
	static String[][] domino;
	static boolean[][] check;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N+1][M+1];
		atk = new Turn[R];
		dfs = new Turn[R];
		domino = new String[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				domino[i][j] = "S";
			}
		}
		
		for(int i=0; i<2*R; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 공격
			if(i % 2 == 0) {
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int dir = getDir(st.nextToken());
				atk[i/2] = new Turn(r, c, dir);
			}
			
			// 수비 
			else if(i % 2 != 0) {
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				dfs[i/2] = new Turn(r, c);
			}
		}
		
		for(int i=0; i<R; i++) {
			
			// 공격
			if(domino[atk[i].r][atk[i].c].equals("S")) {
				domino[atk[i].r][atk[i].c] = "F";
				atkPts += atkRun(atk[i].r, atk[i].c, atk[i].dir);
			}
			
			// 수비
			domino[dfs[i].r][dfs[i].c] = "S";
		}
		
		System.out.println(atkPts);
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(domino[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static int atkRun(int r, int c, int dir) {
		
		Queue<Turn> q = new ArrayDeque<>();
		q.add(new Turn(r, c));
		int result = 1;
		
		while(!q.isEmpty()) {
			
			Turn t = q.poll();
			
			int dominoHeight = map[t.r][t.c]; // 도미노 높이
			
			int nr = t.r;
			int nc = t.c;
			
			for(int i=1; i<dominoHeight; i++) {
				
				nr += dr[dir];
				nc += dc[dir];
				
				// 범위
				if(nr < 1 || nr > N || nc < 1 || nc > M) break;
			    
				if(domino[nr][nc].equals("F")) continue; // 쓰러진 도미노면 다음
				domino[nr][nc] = "F";
				result++;
				q.add(new Turn(nr, nc));
			}
		}
		
		return result;
	}
	
	public static int getDir(String str) {
	
		if(str.equals("E")) return 3;
		else if(str.equals("W")) return 2;
		else if(str.equals("S")) return 1;
		else return 0;
	}
}
