package Week11;

import java.util.*;
import java.io.*;

public class 백준_1976_여행가자 {
	static int N, M;
	static int[] parents;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		int[] target = new int[M];
		parents = new int[N+1];
		for(int i=1; i<N+1; i++) {
			parents[i] = i;
		}
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num ==1) {
					union(i,j);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int root = find(Integer.parseInt(st.nextToken()));
		for(int i=0; i<M-1; i++) {
			
			int num = Integer.parseInt(st.nextToken());
			if(root != find(num)) { 
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
		
	}
	static int find(int x) {
		if(x == parents[x]) return x;
		int rx = find(parents[x]);
		return rx;
	}
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x<y) {
			parents[y]= x;
		}else {
			parents[x] = y;
		}
		
	}
}