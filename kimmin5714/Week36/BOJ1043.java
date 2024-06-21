package Week36;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1043 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = null;
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 사람 수
		int M = Integer.parseInt(st.nextToken()); // 파티 수

		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int[] knum = new int[K + 1];
		for (int i = 0; i < K; i++)
			knum[i] = Integer.parseInt(st.nextToken()); //진실 아는 사람

		ArrayList[] partyIn = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			partyIn[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			while (true) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp>=1 && tmp<=N) {
					partyIn[i].add(tmp);
				}
				else {
					break;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; ; j++) {
			}
		}
	}
}
