package Week41;

import java.io.*;
import java.util.*;

public class 백준_12919_A와B2 {
	
	static String S, T;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine();
		T = br.readLine();
		
	    System.out.println(search(T));
	}
	
	// 재귀로 글자 수 줄여나감
	static int search(String str) {
		
		// 크기 같아졌을 때
		if(S.length() == str.length()) {
			if(str.equals(S)) return 1;
			else return 0;
		}
		
		int res=0;
		
		// A를 추가한 경우
		if(str.charAt(str.length()-1) == 'A') {
			
			res += search(str.substring(0, str.length()-1));
		}
		
		// B를 추가한 경우
		if(str.charAt(0) == 'B') {
			
			String subStr = str.substring(1);
			StringBuilder sb = new StringBuilder(subStr);
			String reverse = sb.reverse().toString();
			res += search(reverse);
		}
		
		return res > 0 ? 1 : 0;
	}
}
