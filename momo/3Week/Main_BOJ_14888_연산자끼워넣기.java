import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_14888_연산자끼워넣기 {
	static int r;
	static int n;
	static boolean[] isSelected;
	static char[] ops;
	static char[] op;
	static int[] arr; 
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		r = n-1;
		ops = new char[r];
		isSelected = new boolean[r];
		op = new char[r];
		arr = new int[n];
		
		String[] split = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(split[i]);
		}
		split = br.readLine().split(" ");
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			switch(i) {
			case 0:
				for (int j = 0; j < Integer.parseInt(split[i]); j++) {
					op[cnt++] = '+';
				}
				break;
			case 1:
				for (int j = 0; j < Integer.parseInt(split[i]); j++) {
					op[cnt++] = '-';
				}
				break;
			case 2:
				for (int j = 0; j < Integer.parseInt(split[i]); j++) {
					op[cnt++] = '*';
				}
				break;
			case 3:
				for (int j = 0; j < Integer.parseInt(split[i]); j++) {
					op[cnt++] = '/';
				}
				break;
			}
		}
		perm(0);
		System.out.println(max);
		System.out.println(min);
	}
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	private static void perm(int cnt) {
		if(cnt == r) {
			int sum = arr[0];
			for (int i = 0; i < r; i++) {
//				System.out.print(arr[i] + " " + ops[i] + " ");
				switch(ops[i]) {
				case '+':
					sum = sum + arr[i+1];
					break;
				case '-':
					sum = sum - arr[i+1];
					break;
				case '*':
					sum = sum * arr[i+1];
					break;
				case '/':
					sum = sum / arr[i+1];
					break;
				}
			}
			if(sum >= max) {
				max = sum;
			}
			if(sum <= min) {
				min = sum;
			}
			return;
		}
		for(int i=0;i<r;i++) {
			if(isSelected[i]) continue;
			ops[cnt] = op[i];
			isSelected[i] = true;
			perm(cnt+1);
			isSelected[i] = false;
		}
	}
}
