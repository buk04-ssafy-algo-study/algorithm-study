package Week71;

import java.io.*;
import java.util.*;

public class 백준_25511_값이k인트리노드의깊이 {

	static int n, k, depth;
	static List<List<Integer>> node;
	static int[] arr;
	public static void main(String[] args) throws IOException{

		init();
		solve();
		System.out.println(depth);
	}
	
	private static void solve() {
		
		Queue<Integer> q = new ArrayDeque<>();
		
		q.offer(0);
		depth = 0;
		
		// 정점 0 값이 k인 경우
		if(arr[0] == k) return;
		
		while(!q.isEmpty()) {
			
			// q 사이즈로 깊이 구분
			int qs = q.size();
			
			depth++;
			while(qs > 0) {
				qs--;
				int qp = q.poll();
			
				// 정점에 저장해둔 다른 정점 꺼내서 정점 값 확인
				for(int i=0; i<node.get(qp).size(); i++) {
					int num = node.get(qp).get(i);
					if(arr[num] == k) return;
                    q.offer(num);
				}
			}
		}
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		node = new ArrayList<>();
		for(int i=0; i<n; i++) node.add(new ArrayList<>());
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			node.get(a).add(b);
		}
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
	}
}
