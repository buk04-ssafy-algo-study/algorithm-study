package Week61;

import java.util.*;
import java.io.*;

public class 백준_24513_좀비바이러스 {
	
	static class Village {
		int r, c;
		int num;
		boolean isInfectingV1;
		boolean isInfectingV2;
		
		public Village (int r, int c, int num, boolean isInfectingV1, boolean isInfectingV2 ) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.isInfectingV1 = isInfectingV1;
			this.isInfectingV2 = isInfectingV2;
		}
		
		public Village(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}
	
	static int N, M;
	static Village[][] map;
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Village[N][M];
		
		int virus_1_row = 0;
		int virus_1_col = 0;
		int virus_2_row = 0;
		int virus_2_col = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				
				int num = Integer.parseInt(st.nextToken());
				
				if(num == 1) {
					virus_1_row = i;
					virus_1_col = j;
					map[i][j] = new Village(i, j, 1, false, false);
				}
				else if(num == 2) {
					virus_2_row = i;
					virus_2_col = j;
					map[i][j] = new Village(i, j, 2, false, false);
				}
				else if(num == -1) {
					map[i][j] = new Village(i, j, -1, false, false);
				}
				else {
					map[i][j] = new Village(i, j, 0, false, false);
				}
			}
		}
		BFS(virus_1_row, virus_1_col, virus_2_row, virus_2_col);
		
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				if(map[i][j].num == 1) {
					num1++;
				}
				else if( map[i][j].num == 2) {
					num2++;
				}
				else if( map[i][j].num == 3) {
					num3++;
				}
//				System.out.print(map[i][j].num+" ");
			}
//			System.out.println();
		}
		System.out.println(num1+" "+num2+" "+num3);
	}
	
	static void BFS(int v1r, int v1c, int v2r, int v2c) {
		
		Queue<Village> q = new ArrayDeque<>();
		
		q.offer(new Village(v1r, v1c, 1, false, false));
		q.offer(new Village(v2r, v2c, 2, false, false));
		
		while(!q.isEmpty()) {
			
			int qSize = q.size();
			
			// v1, v2의 다음 이동 좌표를 큐에 넣기전 임시로 담아둘 리스트, 
			// v1와 v2가 동시에 만나서 v3가 되면 해당 좌표 삭제
			List<Village> v1v2Point = new ArrayList<>();
			
			// v3가 된 좌표 리스트, v3 좌표와 v1v2 좌표 비교하기 위함
			List<Village> v3Point = new ArrayList<>();
			
			while(qSize > 0) {
				qSize--;
				Village v = q.poll();
				
				int num = v.num; // 바이러스 번호, 마을 상태
				boolean iIV1 = v.isInfectingV1;
			    boolean iIV2 = v.isInfectingV2;
				
				for(int i=0; i<4; i++) {
					
					int nr = dr[i] + v.r;
					int nc = dc[i] + v.c;
					
					// 0이 아닌 경우 넘기기
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if(map[nr][nc].num == 1 || map[nr][nc].num == 2 
			        || map[nr][nc].num == 3 || map[nr][nc].num == -1) continue;
					
					// 현재 마을이 1번 바이러스
					if(num == 1) {
						
						// 1번 바이러스가 먼저 방문했다면
						if(map[nr][nc].isInfectingV1) continue;
						
						if(map[nr][nc].isInfectingV2) {
							map[nr][nc].num = 3;
							v3Point.add(new Village(nr, nc, 3));
							continue;
						}
						else {
							v1v2Point.add(new Village(nr, nc, 1));
							map[nr][nc].isInfectingV1 = true;
						}
						
					}
					
					// 현 마 2 바
					else if(num == 2) {
						
						if(map[nr][nc].isInfectingV2) continue;
						
						if(map[nr][nc].isInfectingV1) {
							map[nr][nc].num = 3;
							v3Point.add(new Village(nr, nc, 3));
							continue;
						}
						else {
							v1v2Point.add(new Village(nr, nc, 2));
							map[nr][nc].isInfectingV2 = true;
						}
					}
				}
				
			} // in while
			
			for(int k=0; k<v3Point.size(); k++) {
				
				int v3r = v3Point.get(k).r;
				int v3c = v3Point.get(k).c;
				
				for(int j=0; j<v1v2Point.size(); j++) {
					
					int vr = v1v2Point.get(j).r;
					int vc = v1v2Point.get(j).c;
					
					if(vr == v3r && vc == v3c) {
						
						v1v2Point.remove(j);
						j--;
					}
				}
			}
			
			for(int j=0; j<v1v2Point.size(); j++) {
				
				int vr = v1v2Point.get(j).r;
				int vc = v1v2Point.get(j).c;
				int num = v1v2Point.get(j).num;
				
				q.offer(new Village(vr, vc, num));
				map[vr][vc].num = num;
			}
		} // out while
	}
}