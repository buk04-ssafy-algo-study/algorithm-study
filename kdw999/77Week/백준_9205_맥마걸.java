package Week77;

import java.io.*;
import java.util.*;

public class 백준_9205_맥마걸{
	
	static class Convenient{
		
		int x;
		int y;
		
		public Convenient (int x, int y) {
			this.x = x;
			this.y = y;
		}
 
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			int n = Integer.parseInt(br.readLine()); // 편의점
		
			int homeX = 0, homeY = 0;
			int festivalX= 0, festivalY = 0;
			
			Convenient[] store = new Convenient[n]; 
			
			for(int i=0; i<n+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				// 상근 집
				if(i==0) {
					homeX = x;
					homeY = y;
				}
				
				// 페스티벌 좌표
				else if(i==n+1) {
					festivalX = x;
					festivalY = y;
				}
				
				// 편 좌
				else {
					store[i-1] = new Convenient(x, y);
				}
			}
			
			 
		
				Queue<Convenient> q = new ArrayDeque<>();
				boolean visited[] = new boolean[n]; // 방문처리
				
				q.offer(new Convenient(homeX, homeY));
				
				boolean flag = false;
				
				while(!q.isEmpty()) {
					
					Convenient c = q.poll();
					
					// 현재 위치에서 페스티벌 도착하는 지 체크
					if(Math.abs(festivalX - c.x) + Math.abs(festivalY - c.y) <= 1000) {
						sb.append("happy\n");
						flag = true;
						   break;
					}
					
					// 현재 위치에서 이동 가능한 모든 편의점 방문
					for(int i=0; i<n; i++) {
						
						if(Math.abs(store[i].x - c.x) + Math.abs(store[i].y - c.y) <= 1000) {
							if(!visited[i]) {
								q.offer(new Convenient(store[i].x, store[i].y));
								visited[i] = true;
							}
						}
					}
 
				}
				
				// 페스티벌에 도착하지 못한 경우
				if(!flag) {
					sb.append("sad\n");
				}
				 
		} // T
		System.out.println(sb);
	}
}
