package Week22;

import java.io.*;
import java.util.*;

//치즈안에 갇힌 공기와 갇히지 않은 공기를 구분하는 게 필요한데..
class Pos {
	int r;
	int c;
	
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class 백준_2633_치즈 {
	static int N, M;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static List<Pos> list;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		list = new ArrayList<>();
		visited = new boolean[N][M];
		
		int cheeseCnt = 0; // 치즈 갯수
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) cheeseCnt++;
			}
		}

		// 외부 공기 2, 치즈 1, 내부 공기 0
		
		int time=0;
		
		// 치즈가 없을 때 까지 탐색
		while(cheeseCnt > 0) {
		
		time++; // 경과 시간
		
		// 가장자리엔 치즈를 놓을 수 없다.
		setAir(0, 0); // 외부, 내부 공기 정의, 한 타임에 치즈 다 녹이고 다시 외내부 공기 정의해줘야함
		
		// 치즈들을 하나씩 탐색하면서 녹는지 판단
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) meltCheeze(i, j);
			 }
		  }
		
		// 리스트에 저장된 녹일 치즈 위치 꺼내서 외부 공기로 변환
		for(int i=0; i<list.size(); i++) {
			int lr = list.get(i).r;
			int lc = list.get(i).c;
			
			map[lr][lc] = 2; // 녹은 치즈는 자연스레 외부공기가 된다.
			cheeseCnt--; // 치즈 갯수 줄여줌
		 }
		
		// 한 타임에 썼던 리스트와 방문 배열은 초기화
		list = new ArrayList<>(); 
		visited = new boolean[N][M];
		
	  }
		
		System.out.println(time);
	}
	
	public static void meltCheeze(int r, int c) {
		
		int adjAirCnt=0;
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >=M) continue;
			if(map[nr][nc] == 2) adjAirCnt++; // 외부 공기랑 닿으면 +1
			
		}
		
		// 외부 공기와 닿은 면적이 2군데 이상이면 녹을 치즈 위치 저장
		if(adjAirCnt>=2) list.add(new Pos(r, c));
		
	}
	
	public static void setAir(int r, int c) {
		
		
		Queue<Pos> q = new ArrayDeque<Pos>();
		q.offer(new Pos(r, c));
		visited[r][c] = true;
		map[r][c] = 2;
		
		while(!q.isEmpty()) {
			
			Pos p = q.poll();
			
			for(int i=0; i<4; i++) {
				
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				// 범위 벗어나는 조건, 방문했던 곳이거나 치즈인 경우
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(visited[nr][nc] || map[nr][nc] == 1) continue;
				
				// 내부 공기는 어차피 치즈에 둘러쌓여서 만날 일 도 없다.
				// 다음 칸이 방문했던 곳이랑 치즈가 아니라면 외부공기로 변환 
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc));
					map[nr][nc] = 2;
			}
		}
	}
}
