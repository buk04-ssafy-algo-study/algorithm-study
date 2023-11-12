// https://www.acmicpc.net/problem/1238
import java.io.*;
import java.util.*;

public class Main {

	static int N, M, X;
	static class Edge implements Comparable<Edge>{
		int v, t;
		Edge(int v, int t){
			this.v = v;
			this.t = t;
		}
		@Override
		public int compareTo(Edge o){
			return Integer.compare(this.t, o.t);
		}
	}
	static int[] toX, fromX;
	static List<Edge>[] original, reversed;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N명
		M = Integer.parseInt(st.nextToken()); // M개 단방향 도로
		X = Integer.parseInt(st.nextToken()); // 파티가 열리는 X번 마을
		init();
		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			// 시작점, 끝점, 소요시간 T
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			original[src].add(new Edge(dst, T));
			reversed[dst].add(new Edge(src, T));
		}

		dijkstra(original, fromX);
		dijkstra(reversed, toX);

		int answer = Integer.MIN_VALUE;
		for(int i=1;i<=N;i++){
			answer = Math.max(answer, fromX[i]+toX[i]);
		}
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	static void init(){
		toX = new int[N+1]; // 모든 정점에서 X까지 거리
		fromX = new int[N+1]; // X에서 모든 정점까지 거리
		original = new ArrayList[N+1];
		reversed = new ArrayList[N+1]; // 역방향 


		for(int i=1;i<=N;i++) {
			original[i] = new ArrayList<>();
			reversed[i] = new ArrayList<>();
			toX[i] = Integer.MAX_VALUE;
			fromX[i] = Integer.MAX_VALUE;
		}
	}

	static void dijkstra(List<Edge>[] adjList, int[] time){
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];

		pq.offer(new Edge(X,0));
		time[X] = 0;

		while(!pq.isEmpty()){
			Edge now = pq.poll();
			int v = now.v; // 현재 X와 가장 가까운 정점

			if(visited[v]==true) continue;
			visited[v] = true;

			for(Edge edge : adjList[v]){ // edge: v와 연결된 간선
				if(time[v]+edge.t<time[edge.v]){
					time[edge.v] = time[v]+edge.t;
					pq.offer(new Edge(edge.v,time[edge.v]));
				}
			}
		}
	}
}
