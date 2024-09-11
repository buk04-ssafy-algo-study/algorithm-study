package Week52;

import java.io.*;
import java.util.*;

public class Softeer_동계테스트시점예측 {

	static int N, M;
	static int[][] map;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static boolean[][] isVisited;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		
		while(true) {
            isVisited = new boolean[N][M];
			airCheck(); 
		
		List<int[]> list = new ArrayList<>(); // 녹는 위치 저장

		for(int i=1; i<N-1; i++) {
			for(int j=1; j<M-1; j++) {
				if(map[i][j] == 1) {
					
					boolean canMelting =  meltSnow(i, j);
					if(canMelting) list.add(new int[] {i, j});
				}
			}
		}
		if(list.size() == 0) break;
		result++;
		
		// 삭제될 눈 -1(외부공기)처리
		for(int i=0; i<list.size(); i++) {
			int[] idx = list.get(i);
			map[idx[0]][idx[1]] = -1;
		}
		
	  }
		System.out.println(result);
	}
	
	static void airCheck(){
		
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {0, 0});
		
		map[0][0] = -1;
		isVisited[0][0] = true;
		
		while(!q.isEmpty()) {

			int[] qp = q.poll();
			
			for(int i=0; i<4; i++) {
				
				int nr = qp[0] + dr[i];
				int nc = qp[1] + dc[i];
			
				if(nr < 0 || nr >= N ||  nc < 0 || nc >= M) continue;
				if(map[nr][nc] == 1 || isVisited[nr][nc]) continue;

					map[nr][nc] = -1;

				
				q.offer(new int[] {nr, nc});
				isVisited[nr][nc] = true;
			}

		}
	}
	
    static boolean meltSnow(int r, int c){
			
			int outdoorAir = 0;
			
			for(int i=0; i<4; i++) {
				
				int nr = r + dr[i];
				int nc = c + dc[i];
			
				// 외부 공기 닿은 횟수 체크
				if(map[nr][nc] == -1) {
					outdoorAir += 1;
				}
			}
			if(outdoorAir >= 2) return true;
			return false;
		}
	
}
