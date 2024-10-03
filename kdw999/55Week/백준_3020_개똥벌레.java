package Week55;

import java.io.*;
import java.util.*;
 
public class 백준_3020_개똥벌레 {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[] height = new int[H+1]; // 해당 높이에서 부딪히는 횟수 저장
		int[] botHeight = new int[H+1]; // 석순 높이 갯수
		int[] topHeight = new int[H+1]; // 종유석 높이 갯수
		
		for(int i=1; i<=N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// 석순
			if(i % 2 == 1) {
				botHeight[num]++;
			}
			
			// 종유석
			else if(i % 2 == 0) {
				topHeight[num]++;
			}
		}
		
		// 석순 갯수 계산
		for(int i=H; i>0; i--) {
						
			if(i==H) continue;
		    else botHeight[i] = botHeight[i] + botHeight[i+1];
		}
		
		// 종유석 갯수 계산
		for(int i=H; i>0; i--) {
						
			if(i==H) continue;
			else topHeight[i] = topHeight[i] + topHeight[i+1];
		}
		
		// 부딪힌 갯수 계산
		for(int i=H; i>0; i--) {
			height[i] = botHeight[i] + topHeight[H-i+1];
		}
 
		Arrays.sort(height);
		StringBuilder sb = new StringBuilder();
		
		int minCrush = height[1];
		sb.append(minCrush+" "); // 부딪힌 최소 횟수
		
		int cnt = 0;
		for(int i=1; i<height.length; i++) {
			if(height[i] == minCrush) cnt++;
			else break;
		}
		sb.append(cnt);
		System.out.println(sb);
	}
}
