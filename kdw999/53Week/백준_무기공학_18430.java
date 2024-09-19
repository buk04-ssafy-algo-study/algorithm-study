package Week53;

import java.io.*;
import java.util.*;

public class 백준_무기공학_18430 {

    static int N,M,max;
	static int[][] map;
	static boolean[][] visit;
	
	static int[][] dr = {{0,1}, {0,1}, {-1,0}, {-1,0}};
	static int[][] dc = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		max = Integer.MIN_VALUE;
		
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0);
		System.out.println(max);
	}

	static void dfs(int tmp, int sum) {
		
		// 마지막 체크 지점 도착
		if(tmp==N*M) {
			max = Math.max(max, sum); // 강도 비교
			return;
		}
		
		// 행열 좌표
		int ti = tmp / M;
		int tj = tmp % M;
		
		if(!visit[ti][tj]) {
		
			for(int d=0;d<4;d++) {
				
				//부메랑 모양 가능한지 체크 
				int i1 = ti+dr[d][0];
				int i2 = ti+dr[d][1];
				int j1 = tj+dc[d][0];
				int j2 = tj+dc[d][1];
				
				if(isPossible(i1,i2,j1,j2)) {
					
					// 만들었으면 방문 표시
					visit[ti][tj] = true;
					visit[i1][j1] = true;
					visit[i2][j2] = true;
					dfs(tmp+1, sum+(map[ti][tj]*2)+map[i1][j1]+map[i2][j2]);
					visit[ti][tj] = false;
					visit[i1][j1] = false;
					visit[i2][j2] = false;
				}
			}
		}
		
		dfs(tmp+1, sum);
	}
	
	// 범위 벗어나는지, 만들었던 건지 체크
	static boolean isPossible(int i1, int i2, int j1, int j2) {
		if(i1<0 || i2<0 || i1>=N || i2>=N) return false;
		
		if(j1<0 || j2<0 || j1>=M || j2>=M) return false;
		
		if(visit[i1][j1]) return false;
		
		if(visit[i2][j2]) return false;
		
		return true;
	}
}