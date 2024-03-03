// https://www.acmicpc.net/problem/17836
import java.io.*;
import java.util.*;

public class Main {

	static final int BLANK = 0;
	static final int WALL = 1;
	static final int GRAM = 2;
	static int N, M, T;
	static int[][] map; // 지도
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // u r d l
	static boolean[][][] visited; // 방문 여부
	static int time = Integer.MAX_VALUE; 
	static Queue<int[]> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		q = new ArrayDeque<>();
		
		// input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 성 크기
		M = Integer.parseInt(st.nextToken()); // 성 크기
		T = Integer.parseInt(st.nextToken()); // 시간제한
		map = new int[N][M];
		visited = new boolean[N][M][2];

		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
    
		// 공주에게 도달할 수 있는 최단 시간 계산
		rescue();

		// output
		String answer = (time==Integer.MAX_VALUE) ? "Fail":String.valueOf(time);
		bw.write(answer);
		bw.flush();
		bw.close();
		
	}

	static void rescue(){
		q.offer(new int[]{0,0,0,0}); // r, c, time, gram 보유 flag
		visited[0][0][0] = true; // 그람을 보유하지 않았을 때 (0,0) 방문

		while(!q.isEmpty()){
			int[] now = q.poll();
			int r = now[0]; // row
			int c = now[1]; // col
			int t = now[2]; // 시작점에서부터 (r,c)까지 도달하는 데 소요한 시간
			int hasGRAM = now[3]; // 0이면 그람을 가지고 있지 않음
			if(r==N-1 && c==M-1 && t<=T){ // 공주에게 도달함
				time = Math.min(t,time);
			}
			if(t>time) continue; // 현재 구한 최단 시간보다 더 오래 걸리면 더 이상 탐색하지 않음

			for(int d=0;d<4;d++){
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				if(0<=nr && nr<N && 0<=nc && nc<M && visited[nr][nc][hasGRAM]==false){
					if (hasGRAM==0){ // 현재 그람 미보유
						if(map[nr][nc]==GRAM){ // 다음 칸이 그람이라면
							q.offer(new int[]{nr,nc,now[2]+1,1}); // flag on
							visited[nr][nc][1] = true; // gram을 가진 상태에서 (nr,nc)를 방문
						}
						else if(map[nr][nc]==BLANK){ // 다음 칸이 빈칸
							q.offer(new int[]{nr,nc,now[2]+1,0});
							visited[nr][nc][0] = true; // gram을 가지지 않은 상태에서 (nr,nc)를 방문
						}
					}
					else { // 현재 그람 보유 -> 벽, 빈칸 상관없이 방문 가능함
						map[nr][nc] = BLANK;
						q.offer(new int[]{nr,nc,now[2]+1,1});
						visited[nr][nc][1] = true;
					}
				}
			}
		}
	}
}
