package Week58;

import java.io.*;
import java.util.*;

public class 백준_14284_간선이어가기2 {

	static class Node implements Comparable<Node>{
		
		int to, weight;
		
		public Node (int to, int weight) {
			this.to  = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
		
	}
	
	static List<List<Node>> adjList = new ArrayList<>();
	static int n, m, s, t;
	static final int MAXWEIGHT = 98765321;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 정점 수
		m = Integer.parseInt(st.nextToken()); // 간선 수
		dist = new int[n+1];
		Arrays.fill(dist, MAXWEIGHT);
		
		for(int i=0; i<=n; i++) adjList.add(new ArrayList<>());
		
		for(int i=0; i<m; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList.get(to).add(new Node(from, weight));
			adjList.get(from).add(new Node(to, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		search(s);
		System.out.println(dist[t]);
	}
	
	static void search(int start) {
		
		dist[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {

			Node neighbor = pq.poll();
			
			int curNode = neighbor.to;
			int curWeight = neighbor.weight;

			if(curNode == t) return;
			if(dist[curNode] < curWeight) continue;
			
		for(int i=0; i<adjList.get(curNode).size(); i++) {
			
			int nextNode = adjList.get(curNode).get(i).to;
			int nextNodeWeight = adjList.get(curNode).get(i).weight + curWeight;

			if(nextNodeWeight < dist[nextNode]) {
				dist[nextNode] = nextNodeWeight;
				pq.offer(new Node(nextNode, nextNodeWeight));
			}
			
			
		}
		}
	}
}
