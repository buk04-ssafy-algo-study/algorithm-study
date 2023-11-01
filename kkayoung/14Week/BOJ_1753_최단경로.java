// https://www.acmicpc.net/problem/1753
// 우선순위 큐를 사용한 다익스트라
import java.io.*;
import java.util.*;

public class Main {

	static int[] dist;
	static boolean[] visited; 
	static int INF = Integer.MAX_VALUE;
	static class Edge implements Comparable<Edge>{
		int v, weight;
		Edge(int v, int weight){
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o){ // 가중치 오름차순 정렬
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());// 정점 수
		int E = Integer.parseInt(st.nextToken());// 간선 수
		int K = Integer.parseInt(br.readLine()); // 시작

		PriorityQueue<Edge> pq = new PriorityQueue<>(); // K번 정점에서 가장 가까운 정점부터 방문해야 하기 때문에, 가중치 기준으로 정렬한 우선순위 큐가 필요
		List<Edge>[] adjList = new List[V+1]; // 인접리스트
		for(int i=1;i<=V;i++) adjList[i]=new ArrayList<>();
		visited = new boolean[V+1]; // 방문
		dist = new int[V+1];   // 시작점에서부터 각 노드까지의 최단 거리 저장 
		Arrays.fill(dist,INF); // INF(노드에 도달할 수 없음)로 초기화
		dist[K] = 0; // 시작점->시작점 거리는 0

		for(int i=0;i<E;i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[u].add(new Edge(v,w));
		}

		// dijkstra
		pq.offer(new Edge(K,0));
		while(!pq.isEmpty()){
			Edge e = pq.poll();
			int now = e.v;
			
			if(visited[now]==true) continue;
			visited[now] = true;

			for(Edge link:adjList[now]){
				if(dist[now]+link.weight < dist[link.v]){ //  K, now번 노드 사이 거리 + now, link.v번 노드 사이 거리 < K, link.v번 노드 사이 거리
					dist[link.v] = dist[now]+link.weight;
					pq.offer(new Edge(link.v,dist[link.v])); // K번 노드에서 link.v번 노드까지의 거리를 우선순위 큐에 넣음
				}
			}
			// System.out.println(Arrays.toString(dist));
		} 
				
		// output
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=V;i++){
			int d = dist[i];
			if(d==Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(d+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
}
