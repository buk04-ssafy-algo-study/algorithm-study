package Week12;/*
 * 1. 각 노드를 dfs하여 최대합을 구한다.
 * 	1-1. 각 노드의 인접 노드 리스트 요소마다 dfs 수행
 * 	1-2. 단말 노드에 도달한 경우 
 * 
 * <주의할 점>
 * 1. ArrayList 배열 초기화 : List<Node> list[] = new ArrayList[n];
 * 2. 인접 리스트에서 단말 노드까지 가는 방법.. 
 * 	2-1. 특정 노드의 인접 리스트 조회하면서 방문  
 * 	2-2. 모든 인접 노드를 방문한 노드일 때 재귀 종료
 * 	2-3. 2-2 시점이 2-1 끝났을 때임! 따라서 2-1 for문 직후에 max값 업데이트
 * 3. 트리는 싸이클이 없으므로 갔던 곳 false로 되돌리지 않아도 됨
 * 4. 정점마다 dfs 시작할 때마다 방문 배열 새로 선언해야 초기화한 상태로 진행됨!
 * 
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	int num, v;

	public Node(int num, int v) {
		super();
		this.num = num;
		this.v = v;
	}

	@Override
	public String toString() {
		return "Node [num=" + num + ", v=" + v + "]";
	}
}
public class BOJ_1967 {
	static int n, result = Integer.MIN_VALUE;
	static List<Node> list[];
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		list = new ArrayList[n+1];
		
		for(int i=1;i<=n;i++) 
			list[i] = new ArrayList<>();
		
		for(int i=0;i<n-1;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			list[from].add(new Node(to, v));
			list[to].add(new Node(from, v));
		}
		
		for(int i=1;i<=n;i++) {
			visited = new boolean[n+1];
			visited[i] = true;
			dfs(i, 0);	
		}
		System.out.println(result);
	}
	private static void dfs(int num, int sum) {
		
		List<Node> tmp = list[num];
		
		for(int i=0;i<tmp.size();i++) {			
			if(visited[tmp.get(i).num]) continue;
			
			visited[tmp.get(i).num] = true;
			dfs(tmp.get(i).num, sum+tmp.get(i).v);
		}
		result = Math.max(result, sum);
	}
}
