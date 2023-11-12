// https://www.acmicpc.net/problem/4179
import java.io.*;
import java.util.*;

public class Main {

	static char WALL = '#';
	static char BLANK = '.';
	static char JIHOON = 'J';
	static char FIRE = 'F';
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; // u d l r
	static char[][] maze;
	static int[][] spreadTime;
	static int R, C;
	static Queue<int[]> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		maze = new char[R][C];
		spreadTime = new int[R][C]; // 불이 번진 시간 배열
		// init
		for(int r=0;r<R;r++){
			Arrays.fill(spreadTime[r],Integer.MAX_VALUE);
		}
		q = new ArrayDeque<>();
		int jr=0, jc=0, fr=0, fc=0;
		//input
		for(int r=0;r<R;r++){
			maze[r] = br.readLine().toCharArray();
			for(int c=0;c<C;c++){
				if(maze[r][c]=='J'){ // 지훈 초기위치
					jr = r; jc = c;
				}
				else if(maze[r][c]=='F'){ // 불이 난 공간
					fr = r; fc = c;
					spreadTime[fr][fc] = 0;
					q.offer(new int[]{fr,fc,0});
				}
			}
		}

		burn(); // 불 번짐
		bw.write(escape(jr,jc));
		bw.flush();
		bw.close();
	}

	static void burn(){
		
		while(!q.isEmpty()){
			int[] fire = q.poll();
			int r = fire[0];
			int c = fire[1];
			int time = fire[2];

			for(int d=0;d<4;d++){
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				if(canReach(nr,nc) && time+1<spreadTime[nr][nc]){
					spreadTime[nr][nc] = time+1;
					q.offer(new int[]{nr,nc,time+1});
				}
			}
		}
	}

	static String escape(int r, int c){ // 지훈 탈출
		int result = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[R][C];
		q.offer(new int[]{r, c, 0});
		visited[r][c] = true;
		
		while(!q.isEmpty()){
			int[] jihoon = q.poll();
			r = jihoon[0];
			c = jihoon[1];
			int time = jihoon[2];
			if(r==0 || r==R-1 || c==0 || c==C-1){ // 가장자리 도착
				result = Math.min(result,time+1);
			}

			for(int d=0;d<4;d++){
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				if(canReach(nr,nc) && !visited[nr][nc] && time+1<spreadTime[nr][nc]){
					q.offer(new int[]{nr,nc,time+1});
					visited[nr][nc] = true;
				}
			}
		}

		return (result==Integer.MAX_VALUE)?"IMPOSSIBLE":String.valueOf(result);
	}

	static boolean canReach(int r, int c){
		if(r<0 || r>=R || c<0 || c>=C) return false;
		if(maze[r][c]==WALL || maze[r][c] == FIRE) return false;
		return true;
	}
}
