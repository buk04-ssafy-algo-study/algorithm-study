package Week58;

import java.io.*;
import java.util.*;

public class 백준_7490_0만들기 {

	static StringBuilder sb = new StringBuilder();
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			N = Integer.parseInt(br.readLine());
			
			dfs(1, 1, "1");
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int num, int len, String sum) {
		
		if(len==N) {
		  if(cal(sum) == 0) {
			sb.append(sum+"\n");
			
		  }
		  return;
		}
		
		dfs(num+1, len+1, sum+" "+(num+1));
		dfs(num+1, len+1, sum+"+"+(num+1));
		dfs(num+1, len+1, sum+"-"+(num+1));
	}
	
	static int cal(String str) {
		
		int result = 0;
		
		Queue<Character> cq = new ArrayDeque<>();
		
		str = str.replaceAll(" ", "");
		String[] line = str.split("[+-]");
		
		for(int i=0; i<str.length(); i++) {
			
			if(str.charAt(i) == '+') {
				cq.offer('+');
			}
			else if(str.charAt(i) == '-') {
				cq.offer('-');
			}
		}
		
		result = Integer.parseInt(line[0]);
		
		for(int i=1; i<line.length; i++) {
			
			int n = Integer.parseInt(line[i]);
		    
			if(cq.poll() == '+') {
				result += n;
			}
			else {
				result -= n;
			}
		}
		
		return result;
	}
}
