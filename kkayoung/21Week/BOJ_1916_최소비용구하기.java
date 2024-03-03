// https://www.acmicpc.net/problem/1916
import java.io.*;
import java.util.*;

public class Main {

	static class Node implements Comparable<Node>{
		int v, cost;
		Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	static PriorityQueue<Node> pq;
	static List<Node>[] connected;
	static int N, M;
	static int[] dist;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		connected = new List[N+1];
		dist = new int[N+1];
		visited = new boolean[N+1];

		for(int i=1;i<=N;i++) {
			connected[i] = new ArrayList<>();
			dist[i] = Integer.MAX_VALUE;
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			connected[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		// output
		bw.write(String.valueOf(dist[end]));
		bw.flush();
		bw.close();
	}

	static void dijkstra(int start) {
		pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;

		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int vertex = now.v;

			if(visited[vertex]) continue;
			visited[vertex] = true;

			for(Node node : connected[vertex]) {
				if(dist[vertex]+node.cost < dist[node.v]) {
					dist[node.v] = dist[vertex]+node.cost;
					pq.offer(new Node(node.v, dist[node.v]));
				}
			}
		}
	}
}
