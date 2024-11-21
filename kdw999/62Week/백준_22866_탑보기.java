package Week62;

import java.io.*;
import java.util.*;

public class 백준_22866_탑보기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] buildings = new int[N+1];
		int[] near = new int[N+1];
		int[] bs = new int[N+1];
		
		Arrays.fill(near, -100001);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) buildings[i] = Integer.parseInt(st.nextToken());
		
		// 오 탐
		Stack<Integer> stack = new Stack<>();
		for(int i=1; i<=N; i++) {
			while(!stack.isEmpty() && buildings[stack.peek()] <= buildings[i]) {
				stack.pop();
			}
			
			if(!stack.isEmpty()) {
				bs[i] += stack.size();
				near[i] = stack.peek();
			}
			stack.push(i);
		}
		
		// 왼 탐
		stack = new Stack<>();
		for(int i=N; i>0; i--) {
			while(!stack.isEmpty() && buildings[stack.peek()] <= buildings[i]) {
				stack.pop();
			}
			
			if(!stack.isEmpty()) {
				bs[i] += stack.size();
				if(stack.peek()-i < i-near[i]) near[i] = stack.peek();
			}
			stack.push(i);
		}
		 
		for(int i=1; i<=N; i++) {
			if(bs[i] > 0) System.out.println(bs[i]+" "+near[i]);
			else System.out.println(0);
		}
	}
}
