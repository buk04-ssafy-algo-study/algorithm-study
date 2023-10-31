package Week14;

import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
	int end;
	int weight;
	
	public Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}

public class 백준_1753_최단경로 {
	
	static int V;
	static int E;
	static int K;
	static List<Node>[] adjList;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken()); // 정점
		E = Integer.parseInt(st.nextToken()); // 간선
		
		adjList = new ArrayList[V+1];
		dist = new int[V+1];
		for(int i=1; i<=V; i++) dist[i] = Integer.MAX_VALUE;
		
		K = Integer.parseInt(br.readLine()); // 시작 정점
		
		for(int i=1; i<=V; i++) adjList[i] = new ArrayList<>();
		
		for(int i=1; i<=E; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Node(to, weight));
		}
		
		dijk(K);
		
		for(int i=1; i<=V; i++) {
			if(dist[i] == Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(dist[i]+"\n");
		}
		System.out.println(sb);
		
	} // main
	
	public static void dijk(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>(); // 가중치 순으로 나열하기 위함
		
		boolean[] visit = new boolean[V+1];
		pq.offer(new Node(start, 0));
		dist[start] = 0; // 자기자신은 0
		
        while(!pq.isEmpty()) {
        	Node curNode = pq.poll();
        	int cur = curNode.end;
        	
        	if(visit[cur] == true) continue;
        	visit[cur] = true;
        	
        	for(Node node : adjList[cur]) {
        		if(dist[node.end] > dist[cur] + node.weight) {
        			dist[node.end] = dist[cur] + node.weight;
        			pq.add(new Node(node.end, dist[node.end]));
        		}
        	}
        }
	}
}
