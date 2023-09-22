import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1080_행렬 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]);
		int m = Integer.parseInt(split[1]);
		
		int[][] A = new int[n][m];
		int[][] B = new int[n][m];
		
		// 입력 받기
		for(int i=0;i<n;i++) {
			String temp = br.readLine();
			for(int j=0; j< m;j++) {
				A[i][j] = temp.charAt(j)-'0';
			}
		}
		for(int i=0;i<n;i++) {
			String temp = br.readLine();
			for(int j=0; j< m;j++) {
				B[i][j] = temp.charAt(j)-'0';
			}
		}
		
		// 알고리즘
		int cnt = 0;
		for(int y=0;y <= n-3; y++) {
			for(int x=0; x<=m-3;x++){
				if(A[y][x] != B[y][x]) {
					for(int i=0;i<3;i++) {
						for (int j = 0; j < 3; j++) {
							A[y+i][x+j] = (A[y+i][x+j] ==0) ? 1 : 0;
						}
					}
					cnt++;
				}
			}
		}
		for(int i = 0; i<n;i++) {
			for (int j = 0; j < m; j++) {
				if(A[i][j] != B[i][j]) {
					sb.append(-1);
					System.out.println(sb);
					return;
				}
			}
		}
		sb.append(cnt);
		System.out.println(sb);
		
	}
}
