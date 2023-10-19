import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static class Stuff {
		int weight;
		Stuff next;

		Stuff(int weight, Stuff next) {
			this.weight = weight;
			this.next = null;
		}
	}

	static Stuff[] lighter;   // 물건 i보다 가벼운 물건 연결리스트
	static int[] cmpcnt;      // 비교 횟수 저장
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		lighter = new Stuff[N];
		cmpcnt = new int[N];

		for (int i = 0; i < N; i++) {
			lighter[i] = new Stuff(-1, null); // dummy head
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int heavy = Integer.parseInt(st.nextToken()) - 1;
			int light = Integer.parseInt(st.nextToken()) - 1;

			Stuff newNode = new Stuff(light, null);
			newNode.next = lighter[heavy].next; // dummy head 뒤에 newNode 추가
			lighter[heavy].next = newNode;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) { // 모든 물건에 대해 dfs 탐색
			visited = new boolean[N];
			visited[i] = true;
			if (lighter[i].next == null) // empty
				continue;
			dfs(i, i);
		}
		for (int i = 0; i < N; i++) {
			sb.append((N - 1 - cmpcnt[i]) + "\n"); // (전체 물건 개수 - 1 - 물건 i와 비교한 물건 개수) 출력, 자기 자신과 비교하는 경우 제외시키기 위해 1 뺌
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

	static void dfs(int src, int n) {
		if (lighter[n].next == null)
			return;

		for (Stuff tmp = lighter[n]; tmp != null; tmp = tmp.next) {
			if (tmp.weight == -1 || visited[tmp.weight])
				continue;
			visited[tmp.weight] = true;
			cmpcnt[src]++; // 물건 src와 물건 tmp.weight 비교함 -> 물건 cnt 비교 횟수 1 증가
			cmpcnt[tmp.weight]++; // 물건 src와 물건 tmp.weight 비교함 -> 물건 tmp.weight 비교 횟수 1 증가
			dfs(src, tmp.weight);
		}
	}
}
