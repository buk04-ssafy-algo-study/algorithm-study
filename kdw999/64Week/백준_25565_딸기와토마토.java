package Week64;

import java.io.*;
import java.util.*;

public class 백준_25565_딸기와토마토 {
	
	static int N, M, K;
	static int[][] map, seedMap;
	static List<int[]> list;
	
	public static void main(String[] args) throws IOException {
		
		int oneCnt = init(); // 예제 데이터 초기화 및 맵의 1갯수(씨앗 수) 세기
		
		// 즈티와 레오는 딱 한 줄에만 K개의 씨앗을 심는다.
		// 그렇다면 K*2가 심을 수 있는 씨앗 최대치이다.
		// K*2-(1의 갯수)로 겹친 씨앗 수를 알 수 있음
		
		// K*2 == (1의 갯수)면 겹친 씨앗이 없음
//		if(K*2-oneCnt == 0)
		
		// K*2-(1의 갯수) == k면 둘의 위치가 완전히 겹침
		if((K*2-oneCnt) == K){
			// 맵에서 1인 곳만 계산
			case1();
		}
		
		// K*2-(1의 갯수) == 1면 가로, 세로 교차하는 곳 1개인 경우와 
		// 한 줄에 마지막 심은 곳과 처음 심은 곳이 겹치는 경우 이 2개인 상황
		else if((K*2-oneCnt) == 1) {
			// 맵에서 가로, 세로 겹친 곳만 계산
			case2();
		}
		
		// K*2-(1의 갯수) > 1 한 줄에서 겹친 경우 계산
		// case2의 한 줄에서 1개만 겹친 경우는 여기서 계산
		else if((K*2-oneCnt) > 1) {
			// 맵에서 한 줄로 겹친 곳 계산
			case3();
		}
 
		print(); // 딸기, 토마토 동시에 뿌린 곳 출력
	
	} // main
	
	static int init() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N+1][M+1];
		list = new ArrayList<>();
		seedMap = new int[N+1][M+1];
		
		int oneCnt = 0;
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) oneCnt++;
			}
		}
		 return oneCnt;
	} // init
	
	static void case1() {
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] == 1) list.add(new int[] {i, j});
			}
		}
	} // case1
	
    static void case2() {
    	
    	// 가로, 세로 교차하는 부분만 찾기
		for(int i=1; i<=N; i++) {
			boolean rowSeq = false;
			for(int j=1; j<=M; j++) {
				
				if(map[i][j] == 1 && (j+1<=M) && (map[i][j+1]) == 1) {
					seedMap[i][j]++;
					rowSeq = true;
				}
				else if(map[i][j] == 1 && rowSeq) seedMap[i][j]++;
				
			}
		}
		for(int i=1; i<=M; i++) {
			boolean colSeq = false;
			for(int j=1; j<=N; j++) {
				
				if(map[j][i] == 1 && j+1<=N && map[j+1][i] == 1) {
					seedMap[j][i]++;
					colSeq = true;
				}
				else if(map[j][i] == 1 && colSeq) seedMap[j][i]++;

				if(seedMap[j][i] == 2) {
					list.add(new int[] {j, i});
					return;
				}
			}
		}
		
		// 가로, 세로로 교차하는 부분이 없다면 한 줄에서 한 칸만 겹친 상태
		if(list.size()==0) case3();
	} // case2
	
    static void case3() {
    	
    	seedMap = new int[N+1][M+1];
    	// 가로 검사
    	  for(int i = 1; i <= N; i++) {
    		  for (int j = M; j >= 1; j--) {
  	        	if(map[i][j] == 1 && (j-1 >= 1) && (map[i][j-1]) == 1) {
  	        		
  	        		for(int k=j; k>= j-K+1; k--) seedMap[i][k]++;
  	        		break;
  	        	}
  	          }
    		  
    	        for (int j = 1; j <= M; j++) {
    	        	if(map[i][j] == 1 && (j+1<=M) && (map[i][j+1]) == 1) {
    	        		
    	        		for(int k=j; k<= j+K-1; k++) {
    	        			seedMap[i][k]++;
    	        			if(seedMap[i][k]==2) list.add(new int[] {i, k});
    	        		}
    	        		break;
    	        	}
    	        }
    	    }
    	  
    	// 세로 검사
    	  for(int i = 1; i <= M; i++) {
  	        for (int j = N; j >= 1; j--) {
  	        	if(map[j][i] == 1 && (j-1 >= 1) && (map[j-1][i]) == 1) {
  	        		
  	        		for(int k=j; k>= j-K+1; k--) seedMap[k][i]++;
  	        		break;
  	        	}
  	        }
  	        
  	        for (int j = 1; j <= N; j++) {
	        	if(map[j][i] == 1 && (j+1<=N) && (map[j+1][i]) == 1) {
	        		
	        		for(int k=j; k<= j+K-1; k++) {
	        			seedMap[k][i]++;
	        			if(seedMap[k][i]==2) list.add(new int[] {k, i});
	        		}
	        		break;
	        	}
	        }
  	    }
	} // case3
	 
	static void print() {
	 
		if(list.isEmpty()) System.out.println(0);
		else {
		  System.out.println(list.size());
		  
		  for(int i=0; i<list.size(); i++) {
		   System.out.println(list.get(i)[0]+" "+list.get(i)[1]);
		     }
		}
		
	} // print
}
