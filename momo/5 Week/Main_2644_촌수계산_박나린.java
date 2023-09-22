import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_2644_촌수계산_박나린 {
	static int ans = 0;
	static int N;
	static List<Integer>[] list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] split = br.readLine().split(" ");
		int a = Integer.parseInt(split[0]);
		int b = Integer.parseInt(split[1]);
		
		list = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			split = br.readLine().split(" ");
			int nodeA = Integer.parseInt(split[0]);
			int nodeB = Integer.parseInt(split[1]);
			
			list[nodeA].add(nodeB);
			list[nodeB].add(nodeA);
		}
		boolean[] isVisited = new boolean[N+1];
		dfs(a, b, 0, isVisited);
		if(ans == 0) {
			System.out.println(-1);
			return;
		}
		System.out.println(ans);
	}
	static void dfs(int node, int b, int depth, boolean[] isVisited) {
		if(node == b) {
			ans = depth;
			return;
		}
		isVisited[node] = true;
		for (int i = 0; i <list[node].size(); i++) {
			if(isVisited[list[node].get(i)]) continue;
//			isVisited[list[i].get(i)] = true;
			dfs(list[node].get(i), b, depth + 1, isVisited);
//			isVisited[list[i].get(i)] = false;
		}
	}
}
