import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_14889_스타트와링크 {
	static int[][] arr;
	static int N;
	static int R;
	static int[] picks;
	static int MIN;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		picks = new int[N/2];
		
		MIN = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			String[] split = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(split[j]);			
				}
		}
		R = N/2;
		comb(0, 1);
	}
	
	private static void comb(int cnt, int start) {
		if(cnt == R) {
			int sum1 = 0;
			int sum2 = 0;
			int[] link = new int[R];
			
			
			for (int i = 0; i < picks.length; i++) {
				for (int j = i+1; j < picks.length; j++) {
					sum1 += arr[picks[i]][picks[j]];
				}
			}
			if(Math.abs(sum1 - sum2) < MIN) {
				MIN = Math.abs(sum1 - sum2);
			}
			return;
		}
		for (int i = start; i < N; i++) {
			picks[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}
}
