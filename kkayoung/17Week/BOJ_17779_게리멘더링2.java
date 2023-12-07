// https://www.acmicpc.net/problem/17779
import java.io.*;
import java.util.*;

public class Main {

	static int[][] A;
	static int N, total;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// input
		N = Integer.parseInt(br.readLine());
		A = new int[N][N];
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++){
				A[i][j] = Integer.parseInt(st.nextToken());
				total += A[i][j];
			}
		}

		int answer = Integer.MAX_VALUE;
		for(int x=0;x<N;x++){
			for(int y=0;y<N;y++){
				for(int d1=1;d1<N;d1++){
					for(int d2=1;d2<N;d2++){
						if(x+d1+d2>=N) continue;
						if(y-d1<0 || y+d2>=N) continue;
						answer = Math.min(answer, diff(x,y,d1,d2));
					}
				}
			}
		}

		// output
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	static int diff(int x, int y, int d1, int d2) {
		int[] population = new int[5];

		// 경계선
		boolean[][] border = new boolean[N][N];
		for(int i=0;i<=d1;i++){
			border[x+i][y-i] = true;
			border[x+d2+i][y+d2-i] = true;
		}
		
		for(int i=0;i<d2;i++){
			border[x+i][y+i] = true;
			border[x+d1+i][y-d1+i] = true;
		}
		// 1
		for(int r=0;r<x+d1;r++) {
			for(int c=0;c<=y;c++) {
				if(border[r][c]) break;
				population[0] += A[r][c];
			}
		}
		// 2
		for(int c=y+1;c<N;c++) {
			for (int r=0;r<=x+d2;r++) {
				if(border[r][c]) break;
				population[1] += A[r][c];
			}
		}
		// 3
		for(int r=x+d1;r<N;r++) {
			for(int c=0;c<y-d1+d2;c++) {
				if(border[r][c]) break;
				population[2] += A[r][c];
			}
		}
		// 4
		for(int c=y-d1+d2;c<N;c++) {
			for(int r=N-1;r>x+d2;r--) {
				if(border[r][c]) break;
				population[3] += A[r][c];
			}
		}
		// 5
		population[4] = total-population[0]-population[1]-population[2]-population[3];
    // sort
		Arrays.sort(population);
		return population[4]-population[0];
	}
}
