// https://www.acmicpc.net/problem/2096
import java.io.*;
import java.util.*;

public class Main {	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// input
		int N = Integer.parseInt(br.readLine());
		int[][] maxArr = new int[N][3]; // 최댓값을 저장할 배열
		int[][] minArr = new int[N][3]; // 최솟값을 저장할 배열
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				int n = Integer.parseInt(st.nextToken());
				maxArr[i][j] = n;
				minArr[i][j] = n;
			}
		}

		// 내려가기
		for(int r=1;r<N;r++) {
			minArr[r][0] += Math.min(minArr[r-1][0], minArr[r-1][1]);
			minArr[r][1] += Math.min(Math.min(minArr[r-1][0], minArr[r-1][1]), minArr[r-1][2]);
			minArr[r][2] += Math.min(minArr[r-1][1], minArr[r-1][2]);

			maxArr[r][0] += Math.max(maxArr[r-1][0], maxArr[r-1][1]);
			maxArr[r][1] += Math.max(Math.max(maxArr[r-1][0], maxArr[r-1][1]), maxArr[r-1][2]);
			maxArr[r][2] += Math.max(maxArr[r-1][1], maxArr[r-1][2]);
		}

		int minimum = Integer.MAX_VALUE;
		int maximum = -1;
		for(int c=0;c<3;c++){
			//min
			minimum = (minArr[N-1][c]<minimum) ? minArr[N-1][c]:minimum;
			//max
			maximum = (maxArr[N-1][c]>maximum) ? maxArr[N-1][c]:maximum;
		}

		// output
		sb.append(maximum).append(" ").append(minimum);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}	
}
