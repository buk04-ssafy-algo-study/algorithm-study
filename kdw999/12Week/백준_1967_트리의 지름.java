// 리스트 배열에다가 연결된 간선들을 저장
// 하나씩 탐색하면서 간선에 저장된 값들을 더해감 [방문 체크도 해주기]
// 간선 끝까지 갈 때 마다 최대값과 비교해서 저장

import java.util.*;
import java.io.*;

class Node{
	int num;
	int weight;
	
	public Node(int num, int weight) {
		this.num = num;
		this.weight = weight;
	}
}

public class Main {
	
	static List<Node>[] adjList;
	static int diameter = Integer.MIN_VALUE;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 노드 수
		adjList = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) adjList[i] = new ArrayList<>();
		
		// 간선 수는 노드 수-1
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Node(to, weight)); 
			adjList[to].add(new Node(from, weight));

		}
		

		for(int i=1; i<=N; i++) {
			visited = new boolean[N+1];
			visited[i] = true;
			dfs(i, 0);
		}
		System.out.println(diameter);

	}
	
	public static void dfs(int start, int sum) {
		
		for(Node node : adjList[start]) {
			if(!visited[node.num]) {
				visited[node.num] = true;
				dfs(node.num, sum + node.weight);
			}
		}
		
		diameter = Math.max(diameter, sum);
	}
}
