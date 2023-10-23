package Week13;

import java.util.*;
import java.io.*;

public class 백준_9935_문자열폭발 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine(); // 문자열
		String bombStr = br.readLine(); // 폭발 문자열
		
		// Stack을 쓰는 게 배열보다 메모리적 이점
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<str.length(); i++) {
			stack.push(str.charAt(i));
			
			// 스택에 문자 하나씩 넣으면서 폭발 문자열과 크기가 같게 되면 폭발 문자열을 포함하고 있는지 탐색
			if(stack.size() >= bombStr.length()) {
				boolean flag = true;
				
				
				// 문자열 크기에서 폭발 문자열 크기만큼 빼면서 문자열 인덱스와 폭발 문자열 인덱스를 비교
				// 폭발 문자열 길이만큼 같다면 아래에서 flag는 true일테니 폭발 문자열 길이만큼 스택에서 폭발 문자열을 빼주게 된다.
				// 하나라도 문자가 다르다면 다음으로 넘어가고 계속 문자 추가
				for(int j=0; j<bombStr.length(); j++){
					if(stack.get(stack.size()-bombStr.length()+j) != bombStr.charAt(j)) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					for(int j=0; j<bombStr.length(); j++) stack.pop();
				}
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		// for each문 쓰면 스택을 앞에서 부터 가져올 수 있음
		for(Character c : stack) {
			sb.append(c);
		}
		
		if(sb.length() != 0) System.out.println(sb);
		else System.out.println("FRULA");
	}
}
