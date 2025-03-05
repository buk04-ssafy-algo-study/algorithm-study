package Week76;

import java.io.*;
import java.util.*;

public class 백준_18428_감시피하기 {

	static int N;
	static char[][] map;
	static List<int[]> T;
	static List<int[]> X;
	
	public static void main(String[] args) throws IOException {

		init();
		solve(0, 0, new int[3][2]);
		System.out.println("NO");
	}
	
	private static void solve(int start, int oNum, int[][] obstacle) {
		
		// O를 놓을 좌표 3개를 찾은 경우
		if(oNum == 3) {
		    
			char[][] tempMap = copyMap();
			
			for(int[] pos : obstacle) {
				tempMap[pos[0]][pos[1]] = 'O';
			}
			
			if(searchS(tempMap)) {
				System.out.println("YES");
				System.exit(0);
			}
			
			return;
		}
		
		for(int i=start; i<X.size(); i++) {
			obstacle[oNum] = X.get(i);
			solve(i+1, oNum+1, obstacle);
		}
		return;
	}
	
	private static boolean searchS(char[][] tempMap) {

     for(int t=0; t<T.size(); t++) {
			
			int r = T.get(t)[0];
			int c = T.get(t)[1];	
		
		// 하
		for(int i=1; i<N; i++) {
			if(r+i < N && tempMap[r+i][c] == 'O') break; // 장애물 만나면 다음 방향 탐색
			if(r+i < N && tempMap[r+i][c] == 'S') return false; // 학생 찾으면 탐색 끝
		}
		// 상
		for(int i=1; i<N; i++) {
			if(r-i >= 0 && tempMap[r-i][c] == 'O') break;
			if(r-i >= 0 && tempMap[r-i][c] == 'S') return false;
		}
		// 좌
		for(int i=1; i<N; i++) {
			if(c-i >= 0 && tempMap[r][c-i] == 'O') break;
			if(c-i >= 0 && tempMap[r][c-i] == 'S') return false;
		}
		// 우
		for(int i=1; i<N; i++) {
			if(c+i < N && tempMap[r][c+i] == 'O') break;
			if(c+i < N && tempMap[r][c+i] == 'S') return false;
		}
		
     }
		return true;
		
	}
	
	private static char[][] copyMap() {
		
		char[][] copy = new char[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copy[i][j] = map[i][j];
			}
		}
		
		return copy;
	}
	
	private static void init() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		T = new ArrayList<>();
		X = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			
			String[] line = br.readLine().split(" ");
			
			for(int j=0; j<N; j++) {
				map[i][j] = line[j].charAt(0);
				
				if(map[i][j] == 'T') T.add(new int[]{i, j});
				if(map[i][j] == 'X') X.add(new int[]{i, j});
			}
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
	}
}
