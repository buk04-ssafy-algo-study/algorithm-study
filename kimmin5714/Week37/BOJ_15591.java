package Week37;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15591 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		ArrayList<int[]>[] adj = new ArrayList[N+1]; // 인접리스트

		for(int i=1;i<N;i++){
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			if(adj[p] == null)
				adj[p] = new ArrayList<>();
			if(adj[q] == null)
				adj[q] = new ArrayList<>();
			adj[p].add(new int[]{q,r});
			adj[q].add(new int[]{p,r});
		}

		for(int i=0;i<Q;i++){
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cnt = 0;
			boolean[] visited = new boolean[N+1];

			Queue<Integer> q = new ArrayDeque<>();
			q.offer(v);
			visited[v] = true;

			while(!q.isEmpty()){
				int cur = q.poll();

				for(int a=0;a<adj[cur].size();a++){ // 인접리스트 탐색
					int[] tmp = adj[cur].get(a);
					if(!visited[tmp[0]] && tmp[1]>=k) { // 방문하지않고 k보다 큰 경우
						q.offer(tmp[0]);
						visited[tmp[0]] = true;
						cnt++;
					}
				}
			}
			sb.append(cnt+"\n");
		}
		System.out.println(sb);
	}
}