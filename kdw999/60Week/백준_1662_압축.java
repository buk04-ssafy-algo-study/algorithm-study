package Week60;

import java.util.*;
import java.io.*;

public class 백준_1662_압축 {
	 static char[] S;
	    static int[] rightIdx; // '(' 위치에 ')' 위치 저장

	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	        S = br.readLine().toCharArray();
	        Stack<Integer> stack = new Stack<>();
	        rightIdx = new int[51];

	        for (int i = 0; i < S.length; i++) {
	            if (S[i] == '(') stack.add(i); // '(' 인덱스 저장
	            else if (S[i] == ')') rightIdx[stack.pop()] = i; // '('의 위치에 ')'의 인덱스 저장
	        }

	        System.out.print(decode(0, S.length));
	    }

	    private static int decode(int start, int end) {
	        int len = 0;
	        for (int i = start; i < end; i++) {
	            if (S[i] == '(') { // 열린 괄호인 경우 재귀로 괄호 속 탐색
	                int K = S[i - 1] - '0'; // 반복 횟수
	                int Q = decode(i + 1, rightIdx[i]); // 반복할 문자열 길이
	                len += K * Q - 1;
	                i = rightIdx[i]; // 괄호 이후로 탐색
	            } else { // 숫자인 경우 길이 증가
	                len++;
	            }
	        }
	        return len;
	    }
	}
