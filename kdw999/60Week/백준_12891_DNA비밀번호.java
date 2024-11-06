package Week60;

import java.io.*;
import java.util.*;

public class 백준_12891_DNA비밀번호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int S = Integer.parseInt(st.nextToken()); // DNA 문자열 길이
		int P = Integer.parseInt(st.nextToken()); // 부분 문자열 길이
		
		String randomDNA = br.readLine();
		
		st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		String partsDNA = randomDNA.substring(0, P); // P인덱스 앞 까지 자름
		
		int result = 0;
		
		int countA = partsDNA.length() - partsDNA.replace("A", "").length();
		int countC = partsDNA.length() - partsDNA.replace("C", "").length();
		int countG = partsDNA.length() - partsDNA.replace("G", "").length();
		int countT = partsDNA.length() - partsDNA.replace("T", "").length();
		
		int idx=0;
		for(int i=P; i<=S; i++) {

			if(countA >= A && countC >= C && countG >= G && countT >= T) {
				result++;
			}
			
			if(i==S) break;
			
			String firstPartsDNA = String.valueOf(randomDNA.charAt(idx++));
			if(firstPartsDNA.equals("A")) countA--;
			else if(firstPartsDNA.equals("C")) countC--;
			else if(firstPartsDNA.equals("G")) countG--;
			else countT--;
			
			String onePartsDNA = String.valueOf(randomDNA.charAt(i));
			if(onePartsDNA.equals("A")) countA++;
			else if(onePartsDNA.equals("C")) countC++;
			else if(onePartsDNA.equals("G")) countG++;
			else countT++;
			
		}
		
		System.out.println(result);
	}
}
