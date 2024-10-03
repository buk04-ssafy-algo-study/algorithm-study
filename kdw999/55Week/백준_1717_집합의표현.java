package Week55;

import java.util.*;
import java.io.*;

public class 백준_1717_집합의표현 {
	
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parents = new int[n+1];
		for(int i=1; i<=n; i++) parents[i] = i;
		
		for(int i=0; i<m; i++) {
		
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// 합집합인지
			if(a == 1) {
				boolean hap = checkEqual(b, c);
				if(hap) sb.append("YES" + "\n");
				else sb.append("NO"+ "\n");
			}
			// 합집합으로 합치기
			else {
				union(b, c);
			}
		}
		System.out.println(sb);
	}
	
	static void union(int b, int c) {
		
		int x = find(b);
		int y = find(c);
		
		if(x < y) parents[y] = x;
		else parents[x] = y;
	}
	
	// 부모 찾기
	static int find(int x) {
		
		if(parents[x] == x) return parents[x];
		else return parents[x] = find(parents[x]);
	}
	
	// 부모가 같다면 합집합
	static boolean checkEqual(int b, int c) {
		
		int x = find(b);
		int y = find(c);
		
		return x == y;
	}
}
