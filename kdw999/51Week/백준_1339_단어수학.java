package Week51;

import java.util.*;
import java.io.*;

public class 백준_1339_단어수학 {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] alphabet = new int[26]; // A ~ Z;

		char[] word;
		for(int i=0; i<N; i++) {
			word = br.readLine().toCharArray();
			
			int digits = word.length-1;
			
			for(int j=0; j<word.length; j++) {
				
				// Math.pow는 double형 반환
				alphabet[word[j] - 'A'] += Math.pow(10, digits--); // 각 알파벳이 몇의 자리에 쓰였는지 10의 제곱 함수를 사용하여 저장
			}
			
		}
		Arrays.sort(alphabet);
		
		int num = 9;
		int result = 0;
		for(int i=alphabet.length-1; i>=0; i--) {
			
			if(alphabet[i] > 0) {
				
				result += alphabet[i] * num;
				num--;
			}
		}
		
		System.out.println(result);
	}
}