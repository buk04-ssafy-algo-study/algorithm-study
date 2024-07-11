// https://www.acmicpc.net/problem/20303
import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K, friendCnt, candyCnt, groupCnt;
	static int[] candy;
	static List<Integer>[] adjList;
	static List<int[]> groups;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		candy = new int[N+1];
		adjList = new List[N+1];
		visited = new boolean[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			adjList[i] = new ArrayList<>();
			candy[i] = Integer.parseInt(st.nextToken());
		}

		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b);
			adjList[b].add(a);
		}

		groups = new ArrayList<>();
		for(int i=1;i<=N;i++) {
			if(visited[i]) continue;
			friendCnt = 0;
			candyCnt = 0;
			groupCnt++;
			findFriend(i);
			groups.add(new int[]{friendCnt, candyCnt});
		}		
		
		int answer = findMaxCandy();
		System.out.println(answer);
	}

	static int findMaxCandy() {
		int[] dp = new int[K];
		
		for(int[] arr:groups) {
			int friendCnt = arr[0];
			int candyCnt = arr[1];
			
			for(int i=K-1;i>=friendCnt;i--) {
				dp[i] = Math.max(dp[i], dp[i-friendCnt]+candyCnt);
			}
		}
		return dp[K-1];
	}

	static void findFriend(int child) {
		
		candyCnt += candy[child];
		friendCnt++;
		visited[child] = true;

		for(int friend:adjList[child]) {
			if(visited[friend]) continue;
			findFriend(friend);	
		}
	}
	
}
