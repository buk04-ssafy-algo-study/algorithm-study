package Week54;

import java.util.*;
import java.io.*;

public class 백준_17352_여러분의다리가되어 {

	static int N;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		
		for(int i=1; i<=N; i++) parent[i] = i;
		
		
		for(int i=0; i<N-2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			union(start, end);
		}
		
		for(int i=2; i<=N; i++) {
			
			// 부모가 같은지
			// 다르면 해당 섬을 부모 노드에 연결
			if(!parentCheck(1, i)) {
				System.out.println(1+" "+ i);
				return;
			}
		}
		
	}
	
	// 집합 연결, 부모 통일
	static void union(int x, int y) {
		
		x = find(x);
		y = find(y);
		
		// 부모가 다르면
		if(x != y) { 
			
			if(x < y) parent[y] = x;
			else parent[x] = y;
		}
	}

	// 어떤 집합에 속해있는지, 연결되어있는 부모 찾기
	static int find(int x) {

		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	// 각 다리의 부모가 같은지 체크
	static boolean parentCheck(int x, int y) {
		return find(x) == find(y);
	}
}
