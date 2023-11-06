package Week15;

import java.util.*;
import java.io.*;

class Pos {
	int r;
	int c;
	
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

// 맵 크기가 12X6이면 버블을 1개씩 옮겨도 될 거 같은데? 
public class 백준_11559_PuyoPuyo {
	
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int maxHeight;
	static int chain;
	static boolean flag;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[13][7];
		
		for(int i=1; i<=12; i++) {
			String line = br.readLine();
			for(int j=1; j<=6; j++) {
				 map[i][j] = line.charAt(j-1);
			}
		}
		
		int height=0;
		// 버블 최고 높이 탐색용
		for(int i=1; i<=6; i++) {
			height=0;
			for(int j=12; j>=1; j--) {
				if(map[j][i] == '.') continue;
				height++;
			}
			maxHeight = Math.max(height, maxHeight); // 연쇄로 터진 버블들의 자리를 위에 있는 버블들로 채울 때 최대 높이만큼 돌리기 위함
		}
		
		// 연쇄가 일어나지 않을 때 까지 계속 탐색
		while(true) {
		for(int i=12; i>=1; i--) {
			for(int j=1; j<=6; j++) {
				
				if(map[i][j] != '.') bfs(i, j);
			}
		}
		
		if(!flag) break; // 연쇄 작용이 한 번이라도 일어나지 않으면 탈출 
		else if(flag) { // 연쇄 작용이 한 번이라도 일어났다면
			chain++; // 연쇄 횟수+1
			
			drop(); // 연쇄로 터진 버블들의 빈 자리를 채우기
			flag = false;
		}
	}
		System.out.println(chain);
		
	}//main
	
	public static void bfs(int r, int c) {
	
		boolean[][] visited = new boolean[13][7];
		
		List<Pos> list = new ArrayList<>(); // 4버블이상 모일 시 해당 버블 지우고 위치를 .으로 바꿔주기 위한 리스트
		list.add(new Pos(r, c));
		
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(r, c));
		visited[r][c] = true;
		
		int bubbleCnt=1; // 연속되는 버블 갯수
		while(!q.isEmpty()) {
			
			Pos p = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				// 범위 벗어나거나 방문한 버블이면 다음으로
				if(nr < 1 || nr > 12 || nc < 1 || nc > 6 || visited[nr][nc] ) continue;
				
				if(map[p.r][p.c] == map[nr][nc]) {
					q.offer(new Pos(nr, nc));
					list.add(new Pos(nr, nc));
					bubbleCnt++;
					visited[nr][nc] = true;
				}
			}
		}
		
		// 4버블 이상 모이면 터트리고 .으로 초기화하고
		if(bubbleCnt >= 4) {
			for(int i=0; i<list.size(); i++) {
				int lr = list.get(i).r;
				int lc = list.get(i).c;
				
				map[lr][lc] = '.';
			}
			flag = true; // 하나라도 연쇄작용이 일어남
		}
		
	} // bfs
	
	// 4버블 이상 터질 시 문자들을 밑으로 떨구기 위한 메서드
	public static void drop() {
		
		for(int i=1; i<=6; i++) {
			for(int j=12; j>12-maxHeight; j--) { // 아래에서 위로 버블 찾기
				
				if(map[j][i] != '.' && j == 12) continue; // 색깔 버블이면서 마지막칸 이라면 떨굴게 없으니 패스
				if(j+1 <= 12 && map[j+1][i] != '.') continue; // 색깔 버블이면서 아랫칸에 이미 버블이 있다면 떨굴게 없으니 패스
				if(map[j][i] != '.') { // 색깔 버블이라면
					char bubble = map[j][i];
					
					for(int k=j; k<=12; k++) { // 색깔 버블 위치에서 아래로 탐색하면서
						
						// 떨어질 버블이 밑에 깔린 버블에 닿을 경우
						if(k+1 <= 12 && map[k+1][i] != '.') {
							map[k][i] = bubble;
							map[j][i] = '.';
							break;
						}
						
						// 떨어질 버블이 바닥에 닿을 경우
						if(k==12) {
							map[k][i] = bubble;
							map[j][i] = '.';
							break;
						}
					}
				}
		}
	}
		
	} // drop
}
