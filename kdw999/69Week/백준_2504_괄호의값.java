package Week69;

import java.util.*;
import java.io.*;

public class 백준_2504_괄호의값 {

	static String line;
	
	public static void main(String[] args) throws IOException {

		init();
		System.out.println(solve());
	}
	
	  private static int solve() {
	        Stack<Character> stack = new Stack<>();
	        int result = 0;
	        int value = 1;

	        for (int i = 0; i < line.length(); i++) {
	            char c = line.charAt(i);

	            if (c == '(') {
	                stack.push(c);
	                value *= 2;  // '(' : 2
	            } 
	            else if (c == '[') {
	                stack.push(c);
	                value *= 3;  // '[' : 3
	            } 
	            else if (c == ')') {
	                
	            	if (stack.isEmpty() || stack.peek() != '(') {
	                    return 0;  // 잘못된 괄호열
	                } else if (line.charAt(i - 1) == '(') {
	                    result += value;  // '()' 짝 맞으면 값 더함
	                }
	                stack.pop();
	                value /= 2; // 더했으니 곱했던 건 다시 나누기
	            } 
	            else if (c == ']') {
	                if (stack.isEmpty() || stack.peek() != '[') {
	                    return 0;  // 잘못된 괄호열
	                } else if (line.charAt(i - 1) == '[') {
	                    result += value;  // '[]' 짝 맞으면 값 더함
	                }
	                stack.pop();
	                value /= 3;
	            }
	        }

	        // 괄호가 모두 맞게 짝을 이루지 않으면 0 리턴
	        if (!stack.isEmpty()) {
	            return 0;
	        }

	        return result;
	    }
	
	private static void init() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine();
	}
}
