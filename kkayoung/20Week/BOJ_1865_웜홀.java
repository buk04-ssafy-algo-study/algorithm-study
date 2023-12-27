// https://www.acmicpc.net/problem/1865
import java.io.*;
import java.util.*;

public class Main{

	static class Edge{
		int v, weight;
		Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
	}
	static List<Edge>[] adjList;
	static int N;
	static int INF = 10000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// input
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			adjList = new List[N+1];
			for(int i=1;i<=N;i++) adjList[i] = new ArrayList<>();
			for(int m=0;m<M;m++) { // 도로
				st = new StringTokenizer(br.readLine());
				int src = Integer.parseInt(st.nextToken());
				int dst = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

				adjList[src].add(new Edge(dst, weight));
				adjList[dst].add(new Edge(src, weight));
			}
			for(int w=0;w<W;w++) { // 웜홀
				st = new StringTokenizer(br.readLine());			
				adjList[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), -1*Integer.parseInt(st.nextToken())));
			}

			if(bellmanFord()) sb.append("YES\n");
			else sb.append("NO\n");
		}
			
		// output
		bw.write(String.valueOf(sb.toString()));
		bw.flush();
		bw.close();
	}

	static boolean bellmanFord() {
		boolean updated = false;
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);

		dist[1] = 0;
		for(int i=1;i<N;i++) { // dist를 최대 N-1번 갱신
			updated = false;
			
			for(int j=1;j<=N;j++) {

				for(Edge e:adjList[j]) {
					if(dist[j]+e.weight < dist[e.v]) {
						dist[e.v] = dist[j]+e.weight;
						updated = true;
					}
				}
			}
			if(!updated) break;
		}

		if(updated) { // N번째 갱신했을 때 dist가 변경되면 음의 사이클이 존재함
			for(int j=1;j<=N;j++) {
				for(Edge e:adjList[j]) {
					if(dist[j]+e.weight < dist[e.v]) {
						dist[e.v] = dist[j]+e.weight;
						return true;
					}
				}
			}
		}
		return false;
	}
}
