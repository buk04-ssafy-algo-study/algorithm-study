package ssafy.study.week11;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main_16922_로마숫자만들기 {
	private static int N, sum;
	private static Set<Integer> set; // 나올 수 있는 모든 수를 중복 없이 세기 위한 Set
	private static int[] roma = { 1, 5, 10, 50 }; // 사용 가능한 수를 미리 배열로 선언
	private static int[] result; // 중복조합으로 선택한 수들을 모아둘 배열

	private static void comb(int index, int count) {
		if (count == N) { // 로마숫자 N개를 사용할 때 만들 수 있는 모든 수
			sum = 0;
			for (int n : result) {
				// 선택된 모든 수의 합을 set에 넣는다
				sum += n;
			}
			set.add(sum);
			return;
		}

		for (int i = index; i < 4; i++) {
			result[count] = roma[i]; // 선택한 수를 모두 배열에 넣는다.
			comb(i, count + 1); // 중복을 허용하기 때문에 index는 i 부터 시작
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		set = new HashSet<>();

		result = new int[N];

		comb(0, 0);

		// set을 이용해 중복을 제거하였기 때문에 가능한 수의 개수는 set.size로 반환
		System.out.println(set.size());
	}
}
