package ssafy.study.week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1541_잃어버린괄호 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// - 를 기준으로 먼저 나눈다.
		StringTokenizer st = new StringTokenizer(br.readLine(), "-");

		// 뺄셈 기호를 기준으로 나눈 숫자들(괄호로 묶은 덧셈식)을 저장할 공간
		// 총 몇개의 식이 나올지 모르기 때문에 List
		List<String> calc = new ArrayList<>();

		// stringtokenizer에 나뉘어진 식이 있다면 모두 calc 배열에 넣는다.
		while (st.hasMoreTokens()) {
			calc.add(st.nextToken());
		}

		int result = 0; // 전체 계산한 값을 넣을 변수

		// calc 배열에 있는 모든 요소를 각각 + 기호를 기준으로 나눔
		for (String str : calc) {
			st = new StringTokenizer(str, "+");
			int sum = 0; // 괄호 하나를 계산한 값을 넣을 변수

			// 해당 식에 + 기호를 기준으로 나눈 요소가 있는 동안
			// ( ) 안의 수들을 모두 더한다
			while (st.hasMoreTokens()) {
				sum += Integer.parseInt(st.nextToken());
			}

			// 만약에 해당 str 값이 가장 첫 식이면 전체 값에서 더해주고
			if (str == calc.get(0)) {
				result += sum;
			} else { // 이후로는 모두 앞에 - 부호가 있는 식이기 때문에 전체 값에서 빼준다.
				result -= sum;
			}
		}

		System.out.println(result);

	}
}
