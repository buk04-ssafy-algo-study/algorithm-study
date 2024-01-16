package Week23;

import java.util.*;
import java.io.*;

public class 백준_11562_백양로브레이크 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 건물의 수
		int m = Integer.parseInt(st.nextToken()); // 길의 수
		
		int[][] change = new int[n+1][n+1];
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(i != j) change[i][j] = 250; // 초기 변환값들은 최대 변환값으로 초기화
			}
		}
		
		// u v b에서 b의 값에 따라 dist에 선 변환 횟수를 초기화 
		for(int i=0; i<m; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken()); // u에서
			int v = Integer.parseInt(st.nextToken()); // v로
			int b = Integer.parseInt(st.nextToken());
			
			change[u][v] = 0; // 입력값 u에서 v로의 이동은 최소 단방향이 디폴트라 변환 횟수 0
			
			// 단방향이라면 v에서 u는 오지 못하니 변환 횟수 1로 초기화
			if(b==0) change[v][u] = 1;
			else change[v][u] = 0;
	}
		
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(i==j) continue;
				
				if(change[i][j] > change[i][k] + change[k][j]) {
					change[i][j] = change[i][k] + change[k][j];
				 }
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int k = Integer.parseInt(br.readLine());
		for(int i=0; i<k; i++) {
			
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()); // 출발
			int e = Integer.parseInt(st.nextToken()); // 도착
			
			sb.append(change[s][e] + "\n");
		}
		
		System.out.println(sb.toString());
  }
}
