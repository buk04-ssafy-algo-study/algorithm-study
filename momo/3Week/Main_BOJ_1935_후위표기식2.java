import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main_BOJ_1935_후위표기식2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		char[] str = br.readLine().toCharArray();
		Map<Character, String> map = new HashMap<>();
		for(int i=0; i< N;i++) {
			map.put((char)('A'+i), br.readLine());
		}
		
		//알고리즘
		Stack<Double> stack = new Stack<>();
		for(int i=0;i<str.length;i++) {
			// 알파벳이라면 스택에 넣기
			if(str[i] != '+' && str[i] != '-' &&
					str[i] != '*' && str[i] != '/') {
				stack.add(Double.parseDouble(map.get(str[i])));
			}
			// 연산자라면 스택에서 두 개를 꺼내서 계산
			else {
				double second = stack.pop();
				double first = stack.pop();
				
				switch(str[i]) {
					case '+':
						stack.push((first+second));
						break;
					case '-':
						stack.push((first-second));
						break;
					case '/':
						stack.push((first/second));
						break;
					case '*':
						stack.push((first*second));
						break;
				}
				
			}
		}
		System.out.printf("%.2f",stack.pop());
	}
}
