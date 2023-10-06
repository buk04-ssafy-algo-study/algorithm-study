// https://www.acmicpc.net/problem/28066
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] line = in.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int K = Integer.parseInt(line[1]);

		ArrayDeque<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}

		int answer = 0;
		while (true) {
			if (q.size() < K) {
				answer = q.poll();
				break;
			}

			int first = q.poll();

			for (int i = 0; i < K - 1; i++) {
				q.poll();
			}
			q.offer(first);
		}
		out.write(String.valueOf(answer));
		out.flush();
		out.close();
	}
}
