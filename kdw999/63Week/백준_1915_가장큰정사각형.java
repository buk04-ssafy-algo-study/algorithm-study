package Week63;

import java.io.*;
import java.util.*; 

public class 백준_1915_가장큰정사각형 {
	 public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st;

	        // n과 m 입력 받기
	        st = new StringTokenizer(br.readLine());
	        int n = Integer.parseInt(st.nextToken());
	        int m = Integer.parseInt(st.nextToken());

	        // 2D 배열 arr와 dp 배열 선언
	        int[][] arr = new int[n][m];
	        int[][] dp = new int[n][m];
	        int maxSquare = 0;

	        // 배열 입력 받기
	        for (int i = 0; i < n; i++) {
	        	String line = br.readLine();
	            for (int j = 0; j < m; j++) {
	                arr[i][j] = line.charAt(j)-48;
	            }
	        }

	        // DP 배열 계산
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < m; j++) {
	                if (arr[i][j] == 1) {
	                    if (i == 0 || j == 0) {
	                        dp[i][j] = 1;  // 첫 번째 행이나 열은 1만큼만 가능
	                    } else {
	                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
	                    }
	                    maxSquare = Math.max(maxSquare, dp[i][j]);  // 최대 크기 갱신
	                }
	            }
	        }

	        System.out.println(maxSquare * maxSquare); 
	    }
	}