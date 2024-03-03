// https://www.acmicpc.net/problem/9935
// 1. 문자열을 뒤에서부터 차례대로 스택에 넣음
// 2. 이 때 넣은 문자가 폭발 문자열의 첫 번째 문자와 같다면
// 스택의 top에서부터 폭발 문자열 길이만큼 탐색하며 폭발 문자열과 일치하는 지 검사
// 3. 일치하다면 스택에서 뺀다
// 4. 위 과정이 끝난 후 스택이 비어있다면 "FRULA" 출력
// 그렇지 않다면 스택에서 차례대로 빼서 출력
import java.io.*;
import java.util.*;

public class Main {

	static Stack<Character> st;
	static String target; // 폭발 문자열
	static char[] text; // 문자열
	static int targetLen, popCnt; // 폭발 문자열 길이, 스택에서 삭제시켜야 할 문자 개수
	static StringBuffer removedStr = new StringBuffer();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		// init
		st = new Stack<>();

		// input
		text = br.readLine().toCharArray();
		target = br.readLine();
		targetLen = target.length();
		
		// explode
		for(int i=text.length-1;i>=0;i--){
			char c = text[i];
			st.add(c);
			if(c==target.charAt(0)){ // 폭발 문자열의 첫 번째 문자와 같은가?
				explode();
			}
		}

		// output
		if(st.size()!=0){
			while(!st.empty()){
				sb.append(st.pop());
			}
		}
		else{
			sb.append("FRULA");
		}
		System.out.println(sb);

	}

	static void explode(){
		popCnt = 0;
		removedStr.setLength(0);
		int stackSize = st.size();
		int i=0;
		while(i<targetLen){ // 폭발 문자열 길이만큼 스택 탐색
			int topIdx = stackSize-i-1;
			if(topIdx<0) break;
			char top = st.get(topIdx);
			if(top!=target.charAt(i)) break; // 폭발 문자열과 일치하지 않음

			removedStr.append(String.valueOf(top));
			popCnt++;
			i++;
		}

		if(target.equals(removedStr.toString())){
			// explode
			for(i=0;i<popCnt;i++){
				st.pop();
			}
		}
	}
}
