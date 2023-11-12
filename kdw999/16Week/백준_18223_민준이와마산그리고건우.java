package Week16;

import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
	int to;
	int weight;
	
	public Node(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}

public class 백준_18223_민준이와마산그리고건우 {
	static int V, E, P;
	static List<Node>[] adjList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken()); // 민수 위치 노드
		
		adjList = new ArrayList[V+1];
		
		for(int i=1; i<=V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 양방향 인접리스트
		for(int i=1; i<=E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Node(to, weight));
			adjList[to].add(new Node(from, weight));
		}
		
		// 시작 ~ 민준 + 민준 ~ 도착점이 / 시작 ~ 도착점이랑 같다면 민준이 쪽도 들름
		int root1 = dijk(1, P, V) + dijk(P, V, V);
		int root2 = dijk(1, V, V);
		
		if(root1 == root2) System.out.println("SAVE HIM");
		else System.out.println("GOOD BYE");
		
	} // main
		
	public static int dijk(int start, int end, int V) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		boolean[] visited = new boolean[V+1];

		int[] dist = new int[V+1];
		for(int i=1; i<=V; i++) dist[i] = Integer.MAX_VALUE;
		dist[start] = 0;
		
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			int curNode = n.to;
			
			if(visited[curNode]) continue;
			visited[curNode] = true;
			
			if(curNode == end) return dist[end];
			
			for(Node node : adjList[curNode]) {
				int nextWeight = node.weight;
				int nextNode = node.to;
				if(!visited[nextNode] && dist[nextNode] > dist[curNode] + nextWeight) {
					dist[nextNode] = dist[curNode] + nextWeight;
					pq.add(new Node(nextNode, nextWeight));
				}
			}
		}
		
		return dist[end];
	}
}
