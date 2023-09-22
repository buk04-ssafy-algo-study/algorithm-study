import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_16202_MST게임_김가영 {
	static class Node implements Comparable<Node> {
		int from;
		int to;
		public Node(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		int weight;
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	static int[] parents; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		
		parents = new int[N + 1];
		Node[] graph = new Node[M];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[i] = new Node(from, to, i+1);
		}
		Arrays.sort(graph);
		
		
		for (int j = 0; j < K; j++) {
			parents = new int[N + 1];
			for (int i = 1; i <= N ; i++) {
				parents[i] = i;
			}
			List<Node> tmpList = new LinkedList<>();
			int sum = 0;
			int check = 1;
			for (int i = 0; i < graph.length; i++) {
				int from = graph[i].from;
				int to = graph[i].to;
				if(union(from, to)) {
					sum += graph[i].weight;
					tmpList.add(graph[i]);
					check++;
				}
			}
			if(check != N) sb.append(0 + " ");
			else {
				sb.append(sum + " ");
			}
			Node[] tmp = Arrays.copyOfRange(graph,1,graph.length);
			graph = tmp;
		}
		System.out.println(sb);
	}
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		if(aRoot < bRoot) parents[bRoot] = aRoot;
		else parents[aRoot] = bRoot;
		return true;
	}
}
