package Week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_16926_배열돌리기1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 외곽 라인부터 4방향 꼭짓점 정하고 돌려야되는 라인 돌리고 다음 라인만큼 4방향 쪼이기
		for(int i=0; i<R; i++) {
			int r1=0;
			int c1=0;
			int r2=N-1;
			int c2=M-1;
			
			// 4방향 꼭짓점이 뭉치지 않을 때 까지 
			while(r1 < r2 && c1 < c2) {
				int temp = arr[r1][c1]; // 마지막 변수는 미리 값 저장해줘야 다돌리고나서 넣어줄 수 있다.
				
				// 윗 부분 돌리기
				for(int c=c1; c<c2; c++) arr[r1][c] = arr[r1][c+1];
				
				// 오른쪽 부분 돌리기
				for(int r=r1; r<r2; r++) arr[r][c2] = arr[r+1][c2];
				
				// 아랫 부분 돌리기
				for(int c=c2; c>c1; c--) arr[r2][c] = arr[r2][c-1];
				
				// 왼쪽 부분 돌리기
				for(int r=r2; r>r1+1; r--) arr[r][c1] = arr[r-1][c1];
				
				arr[r1+1][c1] = temp;
				r1 = r1+1;
				c1 = c1+1;
				r2 = r2-1;
				c2 = c2-1;
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}