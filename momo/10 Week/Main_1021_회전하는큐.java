import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1021_회전하는큐 {
	static int n, m;
	static int[] arr;
	static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		List<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			queue.add(i);
		}

		for (int i = 0; i < m; i++) {
			int now = Integer.parseInt(st.nextToken());
			int left = 0;
			int right = 0;
			if(now == queue.get(0)) {
				queue.remove(0);
				continue;
			}
			for (int j = 1;; j++) {
				left++;
				if (queue.get(j) == now) {
					break;
				}
			}
			for (int j = queue.size() - 1;; j--) {
				right++;
				if (queue.get(j) == now)
					break;
			}
			if (left <= right) {
				for (int j = 0; j < left; j++) {
					shiftL(queue);
					cnt++;
				}
			} else {
				for (int j = 0; j < right; j++) {
					shiftR(queue);
					cnt++;
				}
			}
			queue.remove(0);
		}
		System.out.println(cnt);
	}
	static void shiftR(List<Integer> queue) {
		int turn = queue.get(queue.size() - 1);
		queue.remove(queue.size() - 1);
		queue.add(0, turn);
	}

	static void shiftL(List<Integer> queue) {
		int turn = queue.get(0);
		queue.remove(0);
		queue.add(turn);
	}
}