package Week15;

import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
	int end;
	int T;
	
	public Node (int end, int T) {
		this.end = end;
		this.T = T;
	}

	@Override
	public int compareTo(Node o) {
		return this.T - o.T;
	}
}
public class 백준_1238_파티 {

	static int N, M, X;
	static List<Node>[] adjList;
	static List<Node>[] reverseList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 마을 수, 학생 수
		M = Integer.parseInt(st.nextToken()); // 간선 수
		X = Integer.parseInt(st.nextToken()); // 파티하는 마을
		
		adjList = new ArrayList[N+1];
		reverseList = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
			reverseList[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()); // 시작점
			int to = Integer.parseInt(st.nextToken()); // 끝점
			int T = Integer.parseInt(st.nextToken()); // 소요시간
			
			adjList[from].add(new Node(to, T));
			reverseList[to].add(new Node(from, T));
		}
		
		
		// 다른 마을에서 -> X 마을까지의 거리
		int[] go = dijkstra(adjList, X);
		// X 마을에서 -> 다른 마을까지의 거리
		int[] back = dijkstra(reverseList, X);
		
		int result = Integer.MIN_VALUE;
		for(int i=1; i<=N; i++) {
			int dist = go[i] + back[i];
			
			if(dist > result) result = dist;
		}
		System.out.println(result);
	} // main
	
	public static int[] dijkstra(List<Node>[] list, int start) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			int end = node.end;
			
			if(visited[end]) continue;
			visited[end] = true;
			
			for(Node next : list[end]) {
				if(dist[next.end] > dist[end] + next.T) {
					dist[next.end] = dist[end] + next.T;
					pq.offer(new Node(next.end, dist[next.end]));
				}
			}
	  }
		
		
		return dist;
	}
}