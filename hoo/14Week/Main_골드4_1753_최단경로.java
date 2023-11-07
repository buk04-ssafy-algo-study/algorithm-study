package study.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_골드4_1753_최단경로 {
	
	static class Node implements Comparable<Node>{
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
		@Override
		public String toString() {
			return this.to + " " + this.weight;
		}
	}
	static List<ArrayList<Node>> adj;
	static int V, E;
	static int startV;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		init();
		dijkstra();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < V+1; i++) {
			if (dist[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
			else sb.append(dist[i]).append("\n");
		}
		System.out.println(sb);
	}
	
	static void init() throws IOException {
		adj = new ArrayList<ArrayList<Node>>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		startV = Integer.parseInt(br.readLine());
		dist = new int[V+1];
		for (int i = 0; i < V+1; i++) {
			adj.add(new ArrayList<Node>());
			dist[i] = Integer.MAX_VALUE;
		}
		dist[startV] = 0;
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			Node n = new Node(to, weight);
			adj.get(from).add(n);
		}
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] check = new boolean[V+1];
		pq.add(new Node(startV, 0));
		
		while (!pq.isEmpty()) {
			Node nowNode = pq.poll();
			int nowTo = nowNode.to;
			
//			if (check[nowTo]) continue;
//			check[nowTo] = true;

			for (int i = 0; i < adj.get(nowTo).size(); i++) {
				Node toNode = adj.get(nowTo).get(i);
				if (dist[toNode.to] > dist[nowTo] + toNode.weight) {
					dist[toNode.to] = dist[nowTo] + toNode.weight;
					pq.offer(new Node(toNode.to, dist[toNode.to]));
				}
			}
		}
	}
	
}
