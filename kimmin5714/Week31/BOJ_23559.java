package Week31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_23559 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long answer = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int[][] menu = new int[N][2];

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			menu[n][0] = Integer.parseInt(st.nextToken());
			menu[n][1] = Integer.parseInt(st.nextToken());
		}

		//날짜와 맛 상관 없이 A(5000원) 메뉴를 가능한 만큼 고름
		//A와 B식당의 맛 차이 기준으로 내림차순 정렬
		Arrays.sort(menu, new Comparator<int[]>() {
			@Override
			public int compare(int[] e1, int[] e2) {
				//A를 선택했을 때 만족도가 높은 순서대로 정렬
				return (e2[0] - e2[1]) - (e1[0] - e1[1]);
			}
		});

		int n = 0;
		while (X - ((N - n) * 1000) >= 4000) { //남은 돈으로 A를 먹을 수 있는지
												// N-n 남은 메뉴 수
												// 매일 최소 1000원씩 사용하므로 *1000
			//돈이 모두 떨어질 때까지 A(5000원) 먹음
			if(menu[n][1] > menu[n][0])
				break;

			//A메뉴 고름
			answer += menu[n][0];
			X -= 5000;
			n++;
		}
		while (n < N) { //B만족도가 더 큰 경우
			//1. A를 먹기에 돈이 부족한 경우
			//2. 모든 식사들이 A보다 B가 맛있는 경우 (정렬)
			answer += menu[n][1]; //B메뉴만 고름
			n++;
		}

		System.out.println(answer);
	}
}
