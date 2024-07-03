package Week42;

import java.util.*;
import java.io.*;

public class 백준_9466_텀프로젝트 {
	
	static int V, cnt;
	static LinkedList<Integer> e[];
	static boolean[] visited;
	static boolean[] finished;

	public static void main(String[] args) throws IOException {

		int v;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int K = Integer.parseInt(br.readLine());
		while (K--!=0) {
			V = Integer.parseInt(br.readLine()); // 정점

			visited = new boolean[V+1];
			finished = new boolean[V+1];
			e = new LinkedList[V+1];
			for (int i=1; i<=V; ++i) {
				e[i]=new LinkedList();
			}
			
			StringTokenizer vw = new StringTokenizer(br.readLine());
			for (int i=1; i<=V; i++) {	
				v = Integer.parseInt(vw.nextToken());
				e[i].add(v);
			}
			
			cnt=0;
			for(int i = 1; i<=V; i++) {	
				DFS(i);
			}
			
			sb.append(V-cnt).append('\n');
		}
		System.out.println(sb);
	}
	
	static void DFS(int idx) {
        if (visited[idx]) return; 
        
        visited[idx] = true;
        int next = e[idx].get(0);
        
        if (!visited[next]) {
        	DFS(next);
        }else {
        	// 사이클이 있는 경우
        	if (!finished[next]) {
        		cnt++;
        		while (next != idx) {
        			next = e[next].get(0);
        			cnt++;
        		}
        	}
        }
        finished[idx] = true;
	}
}