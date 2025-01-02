import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String input = br.readLine();

		String[] IPv6 = new String[8];
		Arrays.fill(IPv6, ""); // 배열을 빈 문자열로 초기화

		Queue<Character> q = new ArrayDeque<>();

		int count = 0;

		for (int i = 0; i < input.length(); i++) {
			char temp = input.charAt(i);
			q.add(temp); // 입력받은 문자를 모두 큐에 넣는다
			if (temp == ':') // 콜론의 개수를 센다
				count++;
		}

		int index = 0; // 결과배열(IPv6)의 인덱스

		while (!q.isEmpty()) {

			// 현재 값을 "확인만" 한다
			char now = q.peek();

			if (now != ':') {
				// 현재값이 : 이 아니라면 배열에 추가한 뒤 poll
				IPv6[index] += now;
				q.poll();
			} else if (now == ':') {
				// : 이라면 일단 poll
				q.poll();
				char temp = q.peek();

				// 다음 값도 : 이라면 중간에 0000으로 출력될 부분이 존재
				if (temp == ':') {
					index++; // 다음칸부터 0000으로 만듦
					for (int i = 0; i < 8 - count; i++) {
						// 전체 칸의 수 - 처음에 세어준 : 개수
						IPv6[index++] = "0000";
					}
					q.poll(); // 콜론은 큐에서 뺌
				} else {
					// 문자 값이라면 다음칸에 넣어야하기 때문에 index만 올려준다
					index++;
				}
			}

		}

		for (String r : IPv6) {
			// 출력시 해당 칸의 값의 길이가 4보다 작으면
			if (r.length() < 4) {
				for (int i = 0; i < 4 - r.length(); i++) {
					// 4 - 길이 만큼 0을 추가
					sb.append("0");
				}
				sb.append(r); // 값을 추가
			} else {
				sb.append(r);
			}
			sb.append(':'); // 배열 한칸의 입력이 끝나면 : 입력
		}

		sb.deleteCharAt(sb.length() - 1); // 마지막에 출력된 : 값 제거
		System.out.println(sb);
	}
}
