package Week57;

import java.io.*;
import java.util.*;

public class 백준_17404_RGB거리2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] rgb = new int[N+1][3];
		int[][] dp = new int[N+1][3];

		for(int i=1; i<=N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++){
				rgb[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int min = Integer.MAX_VALUE;

		// 첫 집 색칠을 빨, 파, 초 각각으로 하는 경우로 나누어서 진행함
		for(int k=0; k<3; k++){
			for(int i=0; i<3; i++){
				if(k == i) dp[1][i] = rgb[1][i];
				else dp[1][i] = 1000 * 1000; // 첫 집에 색칠한 색깔의 경우의 수로 진행해줄 거라 다른 색 초기값은 최솟값 갱신 안되게 큰수로 잡아준다.
			}
			for(int i=2; i<=N; i++){
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + rgb[i][0];
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + rgb[i][1];
				dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + rgb[i][2];
			}

			for(int i=0; i<3; i++){
				
				// 시작 색과 마지막 색이 같지 않아야 하는 조건
				// 빨간색 시작이면 빨간색에 저장된 최솟값은 제외하고 초록, 파랑 중에 저장된 최솟값만 비교해서 값 갱신하기
				if(i != k) min = Math.min(min, dp[N][i]);
			}
		}

		System.out.println(min);
	}
}
