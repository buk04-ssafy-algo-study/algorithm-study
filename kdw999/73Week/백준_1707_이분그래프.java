package Week73;

import java.io.*;
import java.util.*;

public class 백준_1707_이분그래프 {

	static int K;
	static int[] group;
	 
	static List<List<Integer>> adjList;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList<>();
			for(int l=0; l<=V; l++) adjList.add(new ArrayList<>());
			
			for(int i=0; i<E; i++) {
				
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				adjList.get(u).add(v);
				adjList.get(v).add(u);
			}
			
			// 이분 그래프 체크
			if(link(V)) sb.append("YES\n");
			else sb.append("NO\n");
		}
		System.out.println(sb);
	}
	
	private static boolean link(int V) {
		
		 group = new int[V+1]; // 정점들이 집합에 속했는지 체크용
		 // 0 : 집합 소속 X
		 // 1 : 1집합 소속
		 // 2 : 2집합 소속
		
		for(int i=1; i < adjList.size(); i++) {
			if(group[i] == 0) {
				
				// 연결된 정점이 같은 집합에 속한다면 이분 그래프 X
				if(!bfs(i)) return false;
			}
		}
		
		return true;
	}
	
	private static boolean bfs(int start) {
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		group[start] = 1; // 첫 노드는 집합1로
		
		while(!q.isEmpty()) {
			
			int node = q.poll(); // 현재 정점
			int groupNum = group[node]; // 정점이 속한 그룹
			
			// 현재 정점이랑 연결된 정점들
			for(int next : adjList.get(node)) {
				
				// 연결된 정점이 아직 그룹에 안속해있다면
				if(group[next] == 0) {
					group[next] = -groupNum;
					q.add(next);
				}
				
				// 연결된 정점이 이미 같은 집합에 속해있으면 이분 그래프 X
				else if(group[next] == groupNum) return false;
			}
		}
		
		return true;
	}
 
}
