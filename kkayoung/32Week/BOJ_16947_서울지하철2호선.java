// https://www.acmicpc.net/problem/16947
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static List<Integer>[] adjList;
	static boolean[] visited;
	static int[] distance;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adjList = new List[N+1];
		for(int i=1;i<=N;i++) {
			adjList[i] = new ArrayList<>();
		}
		distance = new int[N+1];
		Arrays.fill(distance, 1000);
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			
			adjList[s1].add(s2);
			adjList[s2].add(s1);
		}

		for(int src=1;src<=N;src++) {
			visited = new boolean[N+1];
			visited[src] = true;
			dfs(src, src, 1);
		}	
		
		bfs();

		System.out.println(print());
	}
	
	static void dfs(int origin, int now, int cnt) {
		
		for(int next:adjList[now]) {
			if(visited[next] && next==origin && cnt>2) {
				distance[origin] = 0;
				continue;
			} else if(!visited[next]) {
				visited[next] = true;
				dfs(origin, next, cnt+1);
			}
		}

	}

	static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();

		for(int i=1;i<=N;i++) {
			if(distance[i]==0) {
				q.offer(new int[]{i, 0});
			}
		}

		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int station = arr[0];
			int cnt = arr[1];

			for(int next:adjList[station]) {
				if(distance[next]==0) continue;
				if(cnt+1<distance[next]) {
					distance[next] = cnt+1;	
					q.offer(new int[]{next, cnt+1});
				}
			}
		}
	}

	static String print() {
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			sb.append(distance[i]).append(" ");
		}
		return sb.toString();
	}
}
