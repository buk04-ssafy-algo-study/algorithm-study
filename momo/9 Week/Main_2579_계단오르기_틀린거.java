import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
*	예제 거의 1000개는 맞는데 틀린 것도 있어서 아까운 풀이
*/

public class Main_2579_계단오르기_틀린거 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n+1];
		
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int max = 0;
		for (int i = n; i > 0;) {
			max += arr[i];
			if(i - 1 == 0) {
				break;
			}
			if(arr[i-1] > arr[i-2]) {
				max += arr[i-1];
				i -= 3;
			}else {
				i -= 2;
			}
		}
		System.out.println(max);
		
	}
}
