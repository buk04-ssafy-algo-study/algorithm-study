package ssafy.study.week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16234_인구이동 {
	private static int N, L, R, date;
	private static int[][] population;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // N*N 배열
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken()); // 인구수의 차이가 L 이상 R 이하

		population = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				population = new int[i][j];
			}
		}

		// 인구가 이동하는 경우는 인접한 국가의 인구수 차이가 L 이상 R 이하인 경우

		System.out.println(date);

	}
}
