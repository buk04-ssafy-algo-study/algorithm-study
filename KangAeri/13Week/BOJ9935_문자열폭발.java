import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9935_문자열폭발 {

	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = br.readLine();
		String key = br.readLine();		
		int i=0, j=0, flag=0;
		Stack<Character> stk  = new Stack<>();
		
		for(i=0; i<in.length(); i++) {
			stk.push(in.charAt(i));
			if(stk.size()<key.length()) {
				continue;
			}
			flag=1;
			for(j=0; j<key.length(); j++) {
				if(stk.get(stk.size()-key.length()+j)!=key.charAt(j)) {
					flag=0;
					break;
				}
			}
			if(flag==1) {
				for(j=0; j<key.length(); j++) {
					stk.pop();
				}
			}
		}	

		if(stk.empty()) {
			sb.append("FRULA");
		} else {
			for(i=0; i<stk.size(); i++) {
				sb.append(stk.get(i));
			}
		}	
		System.out.println(sb);
	}
	
}
