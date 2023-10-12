package ssafy.study.week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1976_여행가자 {
	private static int N, M;
	private static String result;
	private static int[][] city;
	private static int[] plan;
	private static boolean[] isVisited;

	private static void dfs(int index) {
		isVisited[index] = true; // 방문한 도시에 방문체크

		for (int i = 1; i <= N; i++) {
			if (city[index][i] == 1 && !isVisited[i]) {
				// 길이 존재하고 방문하지 않았던 도시라면 해당 도시를 기준으로 dfs
				dfs(i);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		city = new int[N + 1][N + 1];
		plan = new int[M];
		isVisited = new boolean[N + 1];

		result = "YES"; // 결과값은 YES

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}

		// 가장 처음 방문하려고 계획한 도시부터 여행한다.
		// 중간에 어떤 도시에 방문하는 것은 상관 없지만
		// 처음 방문하는 도시는 계획과 동일해야한다.
		dfs(plan[0]);

		for (int i = 0; i < M; i++) {
			// 갈 수 있는 여행지를 모두 여행한 후
			// 계획 했던 여행지에 방문하지 않았다면 결과값은 NO로 바꾼다
			if (!isVisited[plan[i]]) {
				result = "NO";
			}
		}

		System.out.println(result);
	}
}
