package Week25;

import java.util.*;
import java.io.*;

public class 백준_25047_사각형게임 {

	static int N, maxScore;
	static int[][] map;
	static int[] input;
	static int[] pick;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		maxScore = 0;
		
		input = new int[N+1];
		for(int i=0; i<=N; i++) {
			input[i] = i;
		}
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int rotate = N;
		int cnt = 0;
		int pickNum = 0;
		while(rotate >= 0) {
			
			pick = new int[cnt+1];
			combi(cnt, 0, pickNum);
			cnt++;
			pickNum++;
		}

	}
	
	public static void combi(int cnt, int start, int pickNum) {
		
		
		// 필요 갯수 뽑으면 해당 인덱스에 맞게 배열의 행들을 칠하고 그 때 민우가 얻는 점수 체크
		if(cnt == pickNum) {
		
			int minuScore = 0;
			
			int[][] drawing = new int[N][N];
			
			for()
			
			return;
		}
	
		
		for(int i=start; i<=N; i++) {
			
			pick[cnt] = input[i];
			
			combi(cnt+1, i+1, pickNum);
		}
	}
}
