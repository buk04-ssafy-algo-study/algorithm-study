package Week52;

import java.io.*;
import java.util.*;

public class 백준_1956_운동 {
 
	final static int MAXVAL = 987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int result = MAXVAL;
		
		int[][] dist = new int[V+1][V+1];
		
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				
				if(i==j) dist[i][j] =0; 
				else dist[i][j] = MAXVAL;
			}
		}
		
		for(int i=0; i<E; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			dist[from][to] = distance;
		}

		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i==j) continue; // 같은 마을 넘어가
				
				for(int k=1; k<=V; k++) {
					if(i==k || j==k) continue;
					
					dist[j][k] = Math.min(dist[j][k], dist[j][i]+dist[i][k]);
				}
			}
		}
		
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i==j) continue;
				
				if(dist[i][j] == MAXVAL || dist[j][i] == MAXVAL) continue;
				result = Math.min(result, dist[i][j]+dist[j][i]); // 사이클 완성된 길 비교
			}
		}
		
		System.out.println(result==MAXVAL ? -1 : result);
	}
}
