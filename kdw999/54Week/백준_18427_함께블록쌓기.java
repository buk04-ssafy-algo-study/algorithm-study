package Week54;

import java.io.*;
import java.util.*;

public class 백준_18427_함께블록쌓기 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		List<Integer>[] list = new ArrayList[n+1];
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			list[i] = new ArrayList<Integer>();
			
			while(st.hasMoreTokens()) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		int[][] dp = new int[n+1][h+1]; // i번 학생이 j높이를 만들 수 있는 경우의 수 저장
		
		for(int i=1; i<=n; i++) {
			
			for(int j=1; j<=h; j++) {
				for(int block : list[i]) {
					if(j==block) dp[i][j]++; // 3의 높이를 만들려할 때 해당 학생이 3의 블럭을 갖고있으면 경우의 수 +1
					
					// 목표 높이가 갖고있는 블럭보다 크다면 이전 학생의 블럭을 사용해야함
					if(j>block) {
						dp[i][j] += dp[i-1][j-block]; // 목표 높이를 맞추기 위해 이전 학생의 블럭을 사용해서 충족하는 경우
					}
				}
				
				dp[i][j] += dp[i-1][j]; // 이전 학생이 3높이의 블록을 만들었다면 경우의 수 누적하기
				dp[i][j] %= 10007;
			}
		}
		
		System.out.println(dp[n][h]);
	}
}
