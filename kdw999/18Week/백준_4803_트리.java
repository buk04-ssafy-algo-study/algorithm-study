package Week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_4803_트리 {
	
	static int N, M, node, edge, tc;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		tc=0;
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) break; // 마지막 입력
			
			visited = new boolean[N+1];
			graph = new ArrayList[N+1];
			
			for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				// 양방향 그래프
				graph[x].add(y);
				graph[y].add(x);
			}
			
			tc++;
			int tree=0;
			
			for(int i=1; i<=N; i++) {
				if(visited[i]) continue; // 방문한 노드면 다음 노드
				
				node=0;
				edge=0;
				
				dfs(i);
				
				// 간선 = 정점-1;
				// 양방향 그래프라서 간선이 2배, 간선 = (정점-1) * 2;
				// 트리 1개 완성, 하나로 연결된 그래프가 트리인지 판단
				if(edge == (node-1) * 2) tree++;
			}
			
			sb.append("Case ").append(tc).append(": ");
			if(tree==0) sb.append("No trees.");
			else if(tree == 1) sb.append("There is one tree.");
			else sb.append("A forest of ").append(tree).append(" trees.");
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	public static void dfs(int n) {
		
		// 배열 리스트에 저장된 간선 탐색
		node++; // 한 정점에서 시작, 정점+1
		edge += graph[n].size(); // 해당 정점에 연결된 간선 수 합하기
		visited[n] = true;
		
		for(int n2 : graph[n]) {
			if(visited[n2]) continue; // 방문 여부 확인
			dfs(n2);
		}
	}
}
