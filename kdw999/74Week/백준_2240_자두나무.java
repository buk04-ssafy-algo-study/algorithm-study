package Week74;

import java.io.*;
import java.util.*;

public class 백준_2240_자두나무 {

	static int T, W;
	static int[] location;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException  {
		init();
		System.out.println(solve());

	}
	
	private static int solve() {
		
		// 초기값 설정
        for (int w = 0; w <= W; w++) dp[0][w] = 0;
        

        // DP 수행
        for (int t = 1; t <= T; t++) {
            for (int w = 0; w <= W; w++) {
            	
                // 현재 위치 확인 (짝수 w는 1번 나무, 홀수 w는 2번 나무)
                int currentTree = (w % 2 == 0) ? 1 : 2;

                // 자두 받기 여부
                int get = (location[t] == currentTree) ? 1 : 0;

                // 이동하지 않은 경우
                dp[t][w] = dp[t - 1][w] + get;

                // 이동한 경우 (w > 0 일 때만 가능)
                if (w > 0) {
                    dp[t][w] = Math.max(dp[t][w], dp[t-1][w-1] + get);
                }
            }
        }

        // 최댓값 찾기
        int result = 0;
        for (int w = 0; w <= W; w++) {
            result = Math.max(result, dp[T][w]);
        }

        return result;
	}
	
	private static void init() throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		location = new int[T+1];
		dp = new int[T+1][W+1];
		
		for(int i=1; i<=T; i++) location[i] = Integer.parseInt(br.readLine());
		
	}
}
