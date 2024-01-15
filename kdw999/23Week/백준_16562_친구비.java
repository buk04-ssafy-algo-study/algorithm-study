package Week23;

import java.util.*;
import java.io.*;

public class 백준_16562_친구비 {
	
	static int N, M, k;
	static List<List<Integer>> adjList;
	static boolean[] check;
	static int[] needMoney;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 관계 수
		k = Integer.parseInt(st.nextToken()); // 가진 돈
		check = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		needMoney = new int[N+1]; // 친구마다 필요한 친구비
		for(int i=1; i<=N; i++) needMoney[i] = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList<>();
		for(int i=0; i<=N; i++) adjList.add(new ArrayList<>()); // 리스트 배열은 인덱스에 맞게 초기화, 인접리스트는 필요한 갯수만큼 초기화
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 양방향
			adjList.get(a).add(b);
			adjList.get(b).add(a);
			
		}
		
		// BFS로 친구 한 무리를 검색하고 무리내에서 비용이 최소인 애한테 돈주면 최소 비용으로 친구를 사귈 수 있지 않을까?
		
		int sum =0;
		boolean flag = false;
		
		for(int i=1; i<=N; i++) {
			
			
			if(check[i]) continue;
			
			k = k - needMoney[i];
			if(k < 0) {
				flag = true;
				break;
			}
			int min = bfs(i); // 친구의 친구 무리를 탐색하면서 한 무리의 최소 친구비를 찾는 작업
			sum += min;
		}
		
		if(flag) System.out.println("Oh no");
		else System.out.println(sum);
		
	}
	
	public static int bfs(int start) {
		
		int min= needMoney[start]; // 최소비용을 탐색 시작한 친구의 친구비로 초기화
		check[start] = true;
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			
			int num = q.poll();
			
			for(int n : adjList.get(num)) {
				
				if(check[n]) continue; // 방문한 곳이면 건너뛰기
				if(needMoney[n] < min) min = needMoney[n]; // 친구의 친구의 친구비가 최소인지 판단
				check[n] = true;
				q.offer(n); // 친구의 친구를 큐에 넣고 친구의 친구의 친구를 계속 탐색
				
			}
		}
		return min;
	}
}
