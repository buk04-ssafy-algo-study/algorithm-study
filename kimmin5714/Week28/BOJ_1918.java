package Week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1918 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		char[] input = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<>();

		//입력 문자열 앞에서부터 확인하고 처리
		for(char ch : input) {
			switch(ch) {
				//연산자인 경우, 스택에 넣음
				//현재 연산자보다 우선순위가 낮은 연산자가 나올 때까지 pop하면서 sb에 추가
				case '+':
				case '-':
				case '*':
				case '/':
					while(!stack.isEmpty() && (priority(stack.peek()) >= priority(ch)))
						sb.append(stack.pop());
					stack.add(ch);
					break;
				case '(':
					stack.add(ch);
					break;
				case ')':
					//'('만날 때까지 pop하면서 sb에 추가
					while(!stack.isEmpty() && stack.peek() != '(')
						sb.append(stack.pop());
					//'(' 빼기, sb에 추가x
					if(!stack.isEmpty())
						stack.pop();
					break;
				default:
					//피연산자인 경우, 바로 sb에 추가
					sb.append(ch);
			}
		}
		//남은 연산자들 나중에 들어간 순서대로 추가
		while(!stack.isEmpty())
			sb.append(stack.pop());

		System.out.println(sb);
	}

	//연산자 우선순위 : *,/    >    +,-    >     (,)
	private static int priority(Character op) {
		if(op=='(' || op==')') return 0;
		else if(op=='+' || op=='-') return 1;
		else return 2;
	}
	//괄호가 가장 나중 순위인 이유 
	//입력 스트링의 앞에서부터 탐색하니까 무조건 )보다 (가 먼저 나오는데, 괄호 안에 연산이 우선이 되어야 함
	//그러려면 ( 만났을 때 stack을 pop하지 않고 넘어가야함
	//pop하지 않으려면 ( 앞의 연산자 우선순위 보다 (의 우선순위가 더 낮아야 함, 즉 + - * / 연산자보다 우선순위가 낮아야 함 
}