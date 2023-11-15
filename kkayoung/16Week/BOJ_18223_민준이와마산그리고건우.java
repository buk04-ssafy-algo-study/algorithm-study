// https://www.acmicpc.net/problem/18223
import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge implements Comparable<Edge>{
		int v, dist;
		boolean save; // 건우를 구했는지
		Edge(int v, int dist){
			this(v, dist, false);
		}
		Edge(int v, int dist, boolean save){
			this.v = v;
			this.dist = dist;
			this.save = save;
		}
		@Override
		public int compareTo(Edge o){ // 거리가 같다면 save 기준 내림차순 정렬
			if(this.dist!=o.dist)
				return Integer.compare(this.dist, o.dist);
			else
				return Boolean.compare(o.save, this.save);
		}
		@Override
		public String toString(){
			return "v: "+v+", dist:"+dist+", save:"+save;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// input
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		// adj list
		List<Edge>[] adj = new List[V+1];
		for(int i=1;i<=V;i++){
			adj[i] = new ArrayList<>();
		}

		for(int i=0;i<E;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a].add(new Edge(b,c));
			adj[b].add(new Edge(a,c));
		}

		// dijkstra
		boolean saved = false;
		boolean[] visited = new boolean[V+1];
		int[] dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		if(P==1) pq.offer(new Edge(1,0, true));
		else pq.offer(new Edge(1, 0, false));

		while(!pq.isEmpty()){
			Edge now = pq.poll();
			int v = now.v;

			if(visited[v]==true) continue;
			visited[v] = true;
			if(v==V && saved==false){ // 마산에 도착했을 때 saved 값 갱신
				saved = now.save;
			}

			for(Edge connectEdge : adj[v]){
				if(dist[v]+connectEdge.dist<=dist[connectEdge.v]){
					dist[connectEdge.v] = dist[v]+connectEdge.dist;
					if(connectEdge.v==P){ // 건우를 만남
						pq.offer(new Edge(connectEdge.v,dist[connectEdge.v],true));	
					} else{
						pq.offer(new Edge(connectEdge.v,dist[connectEdge.v], now.save));
					}
				}
			}
		}
		
		String answer = saved==true ? "SAVE HIM" : "GOOD BYE";
		bw.write(answer);
		bw.flush();
		bw.close();
	}
}
