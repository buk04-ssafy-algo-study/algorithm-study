import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, k;
	private static int[] parents, cost;

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		// a와 b는 친구
		if (cost[a] < cost[b]) {
			// b의 친구비가 더 크다면
			// b의 부모는 a: a의 친구비 == b의 친구비
			parents[b] = a;
		} else {
			parents[a] = b;
		}
	}

	private static int find(int x) {
		if (parents[x] == x)
			return x;

		return find(parents[x]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		cost = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			union(v, w);
		}

		int res = 0;
		for (int i = 1; i <= N; i++) {
			if (parents[i] == i) {
				// 친구를 사귀기 위한 최소비용일 때
				res += cost[i];
			}
		}

		// System.out.println(res);

		if (res <= k) {
			System.out.println(res);
		} else {
			System.out.println("Oh no");
		}
	}
}