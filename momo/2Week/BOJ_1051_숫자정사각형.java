import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1051_숫자정사각형 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		 String[] split = br.readLine().split(" ");
		 int n = Integer.parseInt(split[0]);
		 int m = Integer.parseInt(split[1]);
		 int[][] arr = new int[n][m];
		 
		 for (int i = 0; i < n; i++) {
			 String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j)-'0';
			}
		}
		 int len = Math.min(m,n);
		 while(len > 1) {
			 for (int i = 0; i <= n-len; i++) {
				 for (int j = 0; j <= m-len; j++) {
					int value = arr[i][j];
					if(value == arr[i+len-1][j] && value == arr[i][j+len-1]
							&& value == arr[i+len-1][j+len-1]) {
						System.out.println(len*len);
						return;
					}
				}
			}
			 len--;
		 }
		 System.out.println(len*len);
//		 System.out.println(Arrays.deepToString(arr));
	}
}