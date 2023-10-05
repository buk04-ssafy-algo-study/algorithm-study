import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1253_좋다 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			int target = arr[i];
			int left = 0, right = N-1;
			int sum = arr[0];
			while(true) {
				if(i == left) left ++;
				else if(i == right) right--;
				if(left >= right) break;
				
				if(arr[left] + arr[right] > arr[i])right--;
				else if(arr[left] + arr[right] < arr[i]) left++;
				else {
					cnt++;
					break;
				}
			}
		}
		System.out.println(cnt);
		
	}
}
