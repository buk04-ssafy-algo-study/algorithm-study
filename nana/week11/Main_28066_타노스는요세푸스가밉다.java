package ssafy.study.week11;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main_28066_타노스는요세푸스가밉다 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();

		Queue<Integer> q = new ArrayDeque<>();

		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}

		while (true) {

			// q의 크기가 K보다 작으면 가장 앞의 숫자 하나만 남을 때까지 숫자를 다 빼낸다.
			if (q.size() < K) {
				q.offer(q.poll());
				while (q.size() != 1) {
					q.poll();
				}
				break;
			}

			q.offer(q.poll());

			// 첫번째 숫자는 이미 뺐기 때문에 K-1번 동안 숫자를 뺀다
			for (int i = 0; i < K - 1; i++) {
				q.poll();
			}

		}

		// 마지막 남은 수를 꺼내서 출력
		System.out.println(q.poll());
	}
}
