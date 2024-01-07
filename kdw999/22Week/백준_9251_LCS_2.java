package Week22;

import java.util.*;
import java.io.*;

public class 백준_9251_LCS_2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		
		int[] dp = new int[str2.length]; // 2번 째 문자열을 기준으로, 해당 문자의 공통부분수열 길이 저장
		
		for(int i=0; i<str1.length; i++) {
			
			int curLength =0; // 현재 이어진 공통부분수열 길이, 현재 몇 개의 공통수열이 나왔는지 체크
			for(int j=0; j<str2.length; j++) {
				
				// 현재 공통부분수열 길이보다 dp에 저장한 문자의 길이가 길다면 curLength 변경
				if(curLength < dp[j]) curLength = dp[j];
				else if(str1[i] == str2[j]) { // 두 문자열을 비교하면서 문자가 같게 될 경우 해당 위치의 dp에 현재 공통부분수열 길이 +1
					dp[j] = curLength + 1;
				}
			}
		}
		
		Arrays.sort(dp);
		System.out.println(dp[dp.length-1]);
	}
}
