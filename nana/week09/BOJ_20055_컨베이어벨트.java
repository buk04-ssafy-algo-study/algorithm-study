import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, K, level;
	private static int[] conv;
	private static boolean[] robot;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		conv = new int[N * 2];
		robot = new boolean[N];
		level = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N * 2; i++) {
			conv[i] = Integer.parseInt(st.nextToken());
		}

		while (true) {
			level++;

			int temp = conv[N * 2 - 1];
			for (int i = N * 2 - 1; i > 0; i--) {
				conv[i] = conv[i - 1];
			}
			conv[0] = temp;

			for (int i = N - 1; i > 0; i--) {
				robot[i] = robot[i - 1];
			}
			robot[0] = false;
			robot[N - 1] = false;

			for (int i = N - 1; i > 0; i--) {
				if (robot[i - 1] && !robot[i] && conv[i] > 0) {
					robot[i - 1] = false;
					robot[i] = true;
					conv[i]--;
					robot[N - 1] = false;
				}
			}

			if (conv[0] > 0) {
				robot[0] = true;
				conv[0]--;
			}

			int count = 0;
			for (int i = 0; i < N * 2; i++) {
				if (conv[i] == 0)
					count++;
			}

			if (count >= K)
				break;
		}

		System.out.println(level);
	}
}
