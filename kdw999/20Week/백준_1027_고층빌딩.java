package Week20;

import java.util.*;
import java.io.*;

public class 백준_1027_고층빌딩 {
	
	static int N;
	static int[] building;
	static int[] visible;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		building = new int[N];
		visible = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) building[i] = Integer.parseInt(st.nextToken());
		
		// 0번 빌딩부터 시작해 우측 빌딩만 탐색, 0번 빌딩에서 3번 빌딩이 보인다면 3번에서도 0번 빌딩이 보이기 때문에 좌측 탐색 X
		search();
		
		Arrays.sort(visible);
		int length = visible.length;
		
		System.out.println(visible[length-1]);
	}
	
	public static void search() {
		
		
		for(int i=0; i<N-1; i++) {
			
			// 바로 옆 빌딩은 무조건 보임
			visible[i]++;
			visible[i+1]++;
			
			// 기울기는 소수가 될 수 있다.
			double slope = (building[i+1] - building[i]) / (i+1 - i); // 초기 옆 빌딩과 기울기 계산
			
			for(int j=i+2; j<N; j++) {
				
				double nextSlope = (double)(building[j] - building[i]) / (j - i); // 옆 빌딩 이후 다음 빌딩들과 기울기 계산
				
				if(nextSlope <= slope) continue; // 탐색하는 빌딩의 기울기가 현재 최고 기울기보다 커야만 빌딩이 보인다
				slope = nextSlope;
				
				visible[i]++;
				visible[j]++;
			}
		}
	}

}
