package Week72;

import java.io.*;
import java.util.*;

public class 백준_1647_도시분할계획 {

	static class Town implements Comparable<Town>{
		int house;
		int cost;
		
		Town(int house, int cost){
			this.house = house;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Town o) {
			return this.cost - o.cost;
		}
	}
	static int N, M;
	static List<List<Town>> list;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(separate());
	}
	
	// Prim
	private static int separate() {

		// 가중치 값으로 경로 저장
		PriorityQueue<Town> pq = new PriorityQueue<>();
		
		pq.offer(new Town(1, 0)); // 초기값
		
		int curCost = 0; // 연결된 경로 중 낮은 가중치부터 차례대로 가중치 값 합산
		int maxCost = 0; // 경로 중 최대값 빼주기 위한 변수
		while(!pq.isEmpty()) {
	 
			Town t = pq.poll();
			
			// 방문 안한 경로만 탐색
			if(!visited[t.house]) {
				
				visited[t.house] = true;
				
				// 최대 가중치 저장
				maxCost = Math.max(maxCost, t.cost);
				curCost += t.cost;
				
				// 연결된 경로 탐색
				for(int i=0; i<list.get(t.house).size(); i++) {
					
					Town next = list.get(t.house).get(i);
					
					if(!visited[next.house]) pq.offer(new Town(next.house, next.cost));
				}
			}
		}
		
		return curCost - maxCost;
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 집 갯수
		M = Integer.parseInt(st.nextToken()); // 길 갯수
		
		list = new ArrayList<>();
		visited = new boolean[N+1];
		
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			
		st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		list.get(A).add(new Town(B, C));
		list.get(B).add(new Town(A, C));
		
		}
	}
}
