package Week22;

import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
	 int n; // 정점
	 int dis; // 두 정점 거리
	 
	 public Node(int n, int dis) {
		 this.n = n;
		 this.dis = dis;
	 }

	@Override
	public int compareTo(Node o) {
		return dis - o.dis;
	}
}

public class 백준_1504_최단경로 {
	
	static int N, E, v1, v2;
	static List<Node>[] list;
	static int[] dist;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 정점 갯수
		E = Integer.parseInt(st.nextToken()); // 간선 갯수
		dist = new int[N+1];
		visited = new boolean[N+1];
		Arrays.fill(dist, 200000000);
		
		list = new ArrayList[N+1];
		for(int i=0; i<=N; i++) list[i] = new ArrayList<>();
		
		for(int i=1; i<=E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			
			// 양방향
			list[from].add(new Node(to, dis));
			list[to].add(new Node(from, dis));
		}
		
		st = new StringTokenizer(br.readLine());
	    v1 = Integer.parseInt(st.nextToken()); // 거치는 정점1
	    v2 = Integer.parseInt(st.nextToken()); // 거치는 정점2
	    
	    // 문제에선 1 -> N까지 가면서 v1, v2를 거치라고 한다
	    // 2가지 루트로 쪼개기
	    
	    // 1 -> v1 -> v2 -> N
	    int route1 = dijk(1, v1) + dijk(v1, v2) + dijk(v2, N);
	    
	    // 1 -> v2 -> v1 -> N
	    int route2 = dijk(1, v2) + dijk(v2, v1) + dijk(v1, N);
	    
	    if(route1 >= 200000000 && route2 >= 200000000) System.out.println(-1);
	    else System.out.println(Math.min(route1, route2));
	}
	
	public static int dijk(int start, int end) {
		
		// 매 번 거리 배열과 방문 배열 초기화
		Arrays.fill(dist, 200000000); 
		Arrays.fill(visited, false);
		
		PriorityQueue<Node> pq = new PriorityQueue<>(); // 최단 거리를 찾기 위해 짧은 거리 순으로 저장하기
		pq.offer(new Node(start, 0)); // 시작 정점, 그 때의 이동거리는 0
		dist[start] = 0; // 시작 정점은 이동 거리 0
	    boolean[] visited = new boolean[N+1];
		
		while(!pq.isEmpty()) {
			
			Node node = pq.poll(); // start 정점과 그 때의 이동 거리 꺼내기
			int curNode = node.n; // 현재 정점 

			// 방문 안한 루트면
			if(!visited[curNode]) {
				visited[curNode] = true;
				
				for(Node n : list[curNode]) {
					
					// 다음 정점까지의 이동 거리가 현재까지 이동한 거리 + 다음 거리로의 이동보다 작아야만 이동 가능, 크면 최단거리가 아니니까
					if(!visited[n.n] && dist[n.n] > dist[curNode] + n.dis) {
						dist[n.n] = dist[curNode] + n.dis; // 다음 정점의 이동거리는 현재 정점까지의 이동거리 + 다음 정점으로 가는데 필요한 이동거리 
						pq.add(new Node(n.n, dist[n.n])); // 다음 정점과 그 때의 이동거리를 다시 pq에 저장
					}
				}
			}
		}
		
		return dist[end];
	}
}
