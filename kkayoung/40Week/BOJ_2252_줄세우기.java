// https://www.acmicpc.net/problem/2252
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] adjList = new List[N+1];
		int[] indeg = new int[N+1];
		for(int i=1;i<=N;i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			indeg[B]++;
			adjList[A].add(B);
		}

		String answer = topologySort(N, indeg, adjList);

		System.out.println(answer);
	}

	static String topologySort(int N, int[] indeg, List<Integer>[] adjList) {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new ArrayDeque<>();

		for(int i=1;i<=N;i++) {
			if(indeg[i]==0) {
				q.offer(i);
			}
		}

		while(!q.isEmpty()) {
			int n = q.poll();
			sb.append(n).append(" ");
			
			for(int i:adjList[n]) {
				indeg[i]--;
				if(indeg[i]==0) {
					q.offer(i);
				}
			}
		}

		return sb.toString();
	}
}
