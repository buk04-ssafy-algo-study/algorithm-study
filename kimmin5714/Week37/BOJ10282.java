package Week37;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Computer implements Comparable<Computer> {
	int depend;
	int time;

	public Computer(int depend, int time) {
		this.depend = depend;
		this.time = time;
	}

	@Override
	public int compareTo(Computer o) {
		return this.time - o.time;
	}
}
public class BOJ10282 {
	static int[] dist;
	static ArrayList<Computer>[] list;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for(int t=0;t<T;t++){
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list = new ArrayList[n+1];
			dist = new int[n+1];
			visited = new boolean[n+1];

			for(int i=1;i<=n;i++){
				dist[i] = Integer.MAX_VALUE;
				list[i] = new ArrayList<>();
			}
			for(int i=0;i<d;i++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());

				// 방문하지 않은 노드 중 최단 거리인 노드 선택
				list[b].add(new Computer(a,s)); // b에 a가 의존
			}

			dijkstra(c);

			int total = 0;
			int time = 0;

			for(int i=1;i<=n;i++){
				if(dist[i] != Integer.MAX_VALUE) {
					total++;
					time = Math.max(time, dist[i]);
				}
			}
			sb.append(total+" "+time+"\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Computer> q = new PriorityQueue<>();

		dist[start] = 0;
		q.offer(new Computer(start, 0));

		while(!q.isEmpty()){
			int cur = q.poll().depend;

			if(!visited[cur]){
				visited[cur] = true;

				for(Computer com : list[cur]){
					if(dist[com.depend] > dist[cur]+com.time){
						dist[com.depend] = dist[cur]+com.time;
						q.offer(new Computer(com.depend, dist[com.depend]));
					}
				}
			}
		}
	}
}