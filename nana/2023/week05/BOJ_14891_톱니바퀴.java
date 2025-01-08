import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int K;
	private static int[][] gear = new int[5][8];
	private static int result;
	private static boolean[] r;

	private static void findL(int num) {

		int left = num - 1;
		if (left < 1)
			return;
		if (gear[num][6] != gear[left][2]) {
			r[left - 1] = true;
			findL(left);
		}

	}

	private static void findR(int num) {
		int right = num + 1;
		if (right > 4)
			return;
		if (gear[num][2] != gear[right][6]) {
			r[right - 1] = true;
			findR(right);
		}
	}

	private static void rotate(int num, int dir) {
		if (dir > 0) {
			int temp = gear[num][7];
			for (int i = 1; i < 8; i++) {
				gear[num][i] = gear[num][i - 1];
			}
			gear[num][0] = temp;
		} else {
			int temp = gear[num][0];
			for (int i = 0; i < 7; i++) {
				gear[num][i] = gear[num][i + 1];
			}
			gear[num][7] = temp;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int n = 1; n <= 4; n++) {
			String str = br.readLine();
			for (int s = 0; s < 8; s++) {
				gear[n][s] = str.charAt(s) - '0';
			}
		}

		K = Integer.parseInt(br.readLine());
		result = 0;

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			r = new boolean[4];

			findL(num);
			findR(num);

//			for (int i = 0; i < 4; i++) {
//				if (r[i]) {
//					rotate((num + 4) % 4, dir);
//					dir *= -1;
//				}
//			}

			for (int i = 0; i < 4; i++) {
				if (r[i]) {
					rotate(i + 1, dir);
				}
			}
		}

		for (int i = 1; i <= 4; i++) {
			System.out.println(gear[i][0]);
			if (gear[i][0] == 1) {
				result += Math.pow(2, i - 1);
			}
		}

		System.out.println(result);
	}

}
