/* https://www.acmicpc.net/problem/11724
 * dfs 
 * 1. 인접행렬
 * 2. 모든 정점에 대하여 dfs 탐색 
 * 	2-1. 정점 배열 만들어서 방문 여부 확인
 *  2-2. 특정 정점에 대해 방문 체크하고 연결 여부 확인
 *  2-3. 연결되어 있으면 dfs 재귀
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ11724_연결요소의개수 {
	
	static int[][] graph;
	static int V, E;
	static boolean[] visited;
	
	public static void dfs(int index) {
		if(visited[index] == true)
			return;
		else {
			visited[index] = true;
			for(int i = 1; i <= V; i++) {
				if(graph[index][i] == 1) {
					dfs(i);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		String[] str = br.readLine().split(" ");
		V = Integer.parseInt(str[0]);
		E = Integer.parseInt(str[1]);
		graph = new int[V+1][V+1];
		visited = new boolean[V+1];
		
		for(int i = 0; i < E; i++) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			
			graph[a][b] = graph[b][a] = 1;
		}
		
		int result = 0 ;
		
		for(int i = 1; i <= V; i++) {
			if(!visited[i]) { 
				dfs(i);
				result++;
			}
		}
		
		System.out.println(result);
		return;
	}
}