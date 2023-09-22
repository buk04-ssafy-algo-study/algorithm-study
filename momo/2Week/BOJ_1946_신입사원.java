import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_1946_신입사원 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());

			int[][] arr = new int[N][2];
			for (int j = 0; j < N; j++) {
				String[] split = br.readLine().split(" ");
				for (int j2 = 0; j2 < 2; j2++) {
					arr[j][j2] = Integer.parseInt(split[j2]);
				}
			}
			Arrays.sort(arr, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return (o1[0] - o2[0]);
				}
			});
			int passer = 1;
			int min = arr[0][1];
			for (int j = 1; j < N; j++) {
				if(arr[j][1] < min) {
					passer++;
					min = arr[j][1];
				}
			}
			System.out.println(passer);
		}
	}
}
