package Week28;

import java.util.*;
import java.io.*;

class Pos{
	int r;
	int c;
	int distance;
	
	public Pos(int r, int c, int distance) {
		this.r = r;
		this.c = c;
		this.distance = distance;
	}
}

public class 백준_16236_아기상어 {
	
	static int N;
	static int[][] map;
	static Queue<Pos> q = new ArrayDeque<>();
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int size=2;
	static int eating;
	static int distance;
	
	  public static void main(String[] args) throws Exception{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        N = Integer.parseInt(br.readLine()); 
	        map = new int[N][N];

	        for(int i=0; i<N; i++) {
	        	StringTokenizer st = new StringTokenizer(br.readLine());
	        	for(int j=0; j<N; j++) {
	        		map[i][j] = Integer.parseInt(st.nextToken());
	        		if(map[i][j] == 9) {
	        			map[i][j] = 0;
	        			q.offer(new Pos(i, j, 0)); 
	        		}
	        	}
	        }
	  
	        bfs();
	        System.out.println(distance);
	}
	  
	public static void bfs() {
		
		while(true) {
		List<Pos> fishPos = new ArrayList<>(); // 잡아먹을 수 있는 물고기 좌표 저장
		int[][] distMap = new int[N][N]; // 물고기 잡아먹을 때 마다 잡아먹은 위치에서 거리 재기 때문에 매 번 객체 생성
		
		while(!q.isEmpty()) {
			
			Pos p = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				// 이동할 수 있는 범위 내에 있으면서
				if(0 <= nr && nr < N && 0 <= nc && nc < N) {
					
					// 다음 칸이 이동할 수 있는 칸이라면 이동거리 +1
					if(distMap[nr][nc] == 0 && map[nr][nc] <= size) {
						distMap[nr][nc] = distMap[p.r][p.c]+1;
						q.offer(new Pos(nr, nc, distMap[nr][nc])); // 아기 상어 이동
						
						// 다음 칸이 잡아먹을 수 있는 물고기라면 리스트에 저장
						if(map[nr][nc] >= 1 && map[nr][nc] <= 6 && map[nr][nc] < size) {
							fishPos.add(new Pos(nr, nc, distMap[nr][nc]));
						}
					}
				}
				
			}
			
		} // q while문, 먹을 수 있는 물고기 발견이 끝나면
		
		// 먹을 수 있는 물고기가 더 이상 없다면 끝
		if(fishPos.size() == 0) return;
		
		// 저장해둔 먹을 수 있는 물고기들 위치 다 꺼내서 가장 가까운 위치 찾기
		Pos fish = fishPos.get(0); // 0부터 ~ 리스트 크기까지
		for(int i=1; i<fishPos.size(); i++) {
			
			// 저장해둔 거리를 비교해서 더 작은 쪽 거리를 가진 물고기 위치받기
			if(fish.distance > fishPos.get(i).distance) fish = fishPos.get(i); 
			
			// 거리가 같을 경우
			else if(fish.distance == fishPos.get(i).distance) {
				
				// 거리가 같을 때 행의 위치 비교
				if(fish.r > fishPos.get(i).r) fish = fishPos.get(i);
				
				// 거리 같고 행도 같을 때
				else if(fish.r == fishPos.get(i).r) {
					
					// 열 비교
					if(fish.c > fishPos.get(i).c) {
						fish = fishPos.get(i);
					}
				}
			}
		}// for
		// 아무 조건에도 걸리지 않았다면 0번 인덱스 그대로 있을 것
		
		// 가장 가까운 물고기 찾았으니 잡아먹고 값 0으로, 그 때의 거리 저장
		map[fish.r][fish.c] = 0;
		distance += fish.distance;
		eating++; // 먹었으니 먹은 횟수 +1; 
		
		// 먹은 횟수가 사이즈와 같다면 사이즈 증가
		if(size == eating) {
			size++;
			eating = 0;
		}
		
		// 잡아먹은 가장 가까운 물고기의 위치부터 아기 상어는 또 탐색하러 가기
		q.offer(fish);
	  }
	}
}