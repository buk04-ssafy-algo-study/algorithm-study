// https://www.acmicpc.net/problem/1194
import java.io.*;
import java.util.*;

public class Main {

	static final char BLANK = '.';
	static final char WALL = '#';
	static final char EXIT = '1';
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	static char[][] maze;
	static int N, M;
	
	static class Minsik{
		int r, c, dist, key;
		Minsik(int r, int c, int dist, int key){
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.key = key;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N][M];

		int minsikR = 0, minsikC = 0;

		for(int i=0;i<N;i++){
			String line = br.readLine();
			for(int j=0;j<M;j++){
				maze[i][j] = line.charAt(j);
				if(maze[i][j]=='0'){
					minsikR = i;
					minsikC = j;
				}
			}
		}

		int answer = escape(minsikR, minsikC);
		if(answer==Integer.MAX_VALUE) answer = -1;
		// output
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	static int escape(int r, int c){
		int result = Integer.MAX_VALUE;
		boolean[][][] visited = new boolean[N][M][64]; // 행, 열, 열쇠
		Queue<Minsik> q = new ArrayDeque<>();
		q.offer(new Minsik(r, c, 0, 0));
		visited[r][c][0] = true;

		while(!q.isEmpty()){
			Minsik now = q.poll();
			r = now.r;
			c = now.c;
			int key = now.key;

			if(maze[r][c]==EXIT){
				result = Math.min(result, now.dist);
				continue;
			}
			
			for(int d=0;d<4;d++){
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];

				if(0<=nr && nr<N && 0<=nc && nc<M && maze[nr][nc]!=WALL && !visited[nr][nc][now.key]){
					visited[nr][nc][key] = true;
					if('a'<=maze[nr][nc] && maze[nr][nc]<='f'){ // key
						int idx = maze[nr][nc]-'a';
						int addKey = (1<<idx)|now.key;
						visited[nr][nc][addKey] = true;
						q.offer(new Minsik(nr, nc, now.dist+1, addKey));
					}
					else if('A'<=maze[nr][nc] && maze[nr][nc]<='F'){ // door
						int keyIdx = maze[nr][nc]-'a';
						if(((1<<keyIdx)&now.key) == (1<<keyIdx)){ // 열쇠 가지고 있을 경우만 큐에 넣음
							q.offer(new Minsik(nr, nc, now.dist+1, now.key));
						}
					}
					else { // blank
						q.offer(new Minsik(nr,nc,now.dist+1, now.key));
					}
				}
			}
		}
		return result;		
	}
}
