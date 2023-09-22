package probs0829;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class BOJ16202 {
	static Edge[] edgeList;
	static int[] parents;
	static int V, E;
		
	static class Edge implements Comparable<Edge> {
		
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static void make() {
		parents = new int[V+1];
		for (int i = 0; i <= V; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		
		int aRoot = find(a);
		int bRoot = find(b);	
		if (aRoot == bRoot) {
			return false;
		}
		parents[bRoot] = aRoot;
		return true;
	}

	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k=0,i=0,cost;
		Edge e;
		String[] in;
		in = br.readLine().split(" ");
		V = Integer.parseInt(in[0]);
		E = Integer.parseInt(in[1]);
		k = Integer.parseInt(in[2]);
		edgeList = new Edge[E];
		for(i=1; i<=E;i++) {
			in = br.readLine().split(" ");
			int from = Integer.parseInt(in[0]);
			int to = Integer.parseInt(in[1]);
			int weight = i;
			edgeList[i-1] = new Edge(from, to, weight);
		}

		for(int turn=0; turn<k; turn++) {
			make();
			cost = 0;  
			int count = 0; 
			
			for (int j=turn; j<E; j++) { 
				e = edgeList[j];
				
				if (union(e.from, e.to)) {
					cost += e.weight;
					count++;
				}
				
			}
			if (count != V - 1) {
				cost=0;
			}
			

			sb.append(cost+" ");
		}

		System.out.println(sb);
	}
}
