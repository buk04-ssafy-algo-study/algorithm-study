package Week80;

import java.io.*;
import java.util.*;

public class 백준_2660_회장뽑기 {
 
	
	static int N;
	static int[][] dist;
	
	public static void main(String[] args) throws IOException  {

		init();
		solve();
		
	}
	
	private static void solve() {
		
		// 플로이드 워셜
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]); // i에서 j로 바로 가는 경우와 i에서 k, k에서 j로 경유해서 가는 경우
				}
			}
		}
		
		int[] scores = new int[N+1];
		int minScore = 987654321;
		
		for(int i=1; i<=N; i++) {
		
			int score = 0;
			
			for(int j=1; j<=N; j++) {
				
				// 해당 번호인 학생의 가장 먼 친구 찾기
				score = Math.max(score, dist[i][j]);
			}
			
			scores[i] = score;
			
			// 회장 후보를 뽑기 위한 가장 낮은 점수 지정
			minScore = Math.min(minScore, score);
		}
		
	    ArrayList<Integer> list = new ArrayList<>();
	    
	    for(int i=1; i<=N; i++) {
	    	if(scores[i] == minScore) { // 가장 점수 낮은 애들 후보로
	    		list.add(i);
	    	}
	    }
	    
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append(minScore + " "+ list.size()+"\n");
	    for(int i=0; i<list.size(); i++) sb.append(list.get(i)+" ");
	    
	    System.out.println(sb);
	    
	}
	
	private static void init() throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		dist = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			
			Arrays.fill(dist[i], 987654321);
			dist[i][i] = 0;
		}
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a == -1 && b == -1) break;
			
			dist[a][b] = 1; // a b는 바로 연결된 친구라 1점
			dist[b][a] = 1;
			
		}
		
	}
}
