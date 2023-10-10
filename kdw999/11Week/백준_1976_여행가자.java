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
		for(int i=1; i<N+1; i++) parents[i] = i; // 자기 자신 루트 가리키게 세팅
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num ==1) {
					union(i,j); // 루트 노드 합치기
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int root = find(Integer.parseInt(st.nextToken())); // 출발 도시
		for(int i=0; i<M-1; i++) {
			
			int num = Integer.parseInt(st.nextToken()); // 방문할 도시
			if(root != find(num)) { // 출발 도시와 방문할 도시의 루트가 다르다면 여행할 수 없다.
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
		
	}
	static int find(int x) {
		if(x == parents[x]) return x; // 루트가 자기 자신이라면
		int rx = find(parents[x]); // 자기 자신이 아니면 루트 노드 리턴
		return rx;
	}
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x<y) { // 양방향 연결;
			parents[y]= x;
		}else {
			parents[x] = y;
		}
		
	}
}
