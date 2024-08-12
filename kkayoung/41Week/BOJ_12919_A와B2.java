// https://www.acmicpc.net/problem/12919
import java.io.*;
import java.util.*;

public class Main {

	static boolean isAvailable = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String S = br.readLine();
		String T = br.readLine();
		
		StringBuilder sb = new StringBuilder();
		sb.append(T);
		convert(sb, S);

		int answer = isAvailable ? 1 : 0;
		System.out.println(answer);
	}

	static void convert(StringBuilder sb, String S) {
		if(sb.length() == S.length()) {
			isAvailable = sb.toString().equals(S);
			return;
		}

		if(isAvailable) return;
			// oper1
			if(sb.charAt(sb.length()-1)=='A') {
				sb.setLength(sb.length()-1);
				convert(sb, S);
				sb.append("A");
			}
		
			if(isAvailable) return;
			if(sb.charAt(0)=='B') {
				sb.reverse().setLength(sb.length()-1);
				convert(sb, S);
				sb.append("B").reverse();
			}
	}


	
}
