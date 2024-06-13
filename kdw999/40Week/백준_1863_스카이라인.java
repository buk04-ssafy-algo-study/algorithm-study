package Week40;

import java.util.*;
import java.io.*;

// 고도 최대 5만번 변경됨
// X 가로, Y 세로

public class 백준_1863_스카이라인 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<n; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 최근 고도가 바뀐 층이 현재 고도가 바뀐 층보다 높을 경우
			while(!stack.isEmpty() && stack.peek() > y) {
				cnt++;
				stack.pop();
			}
			if(!stack.isEmpty() && stack.peek() == y) continue;
			
			if(y != 0) stack.push(y);
		}
		
		cnt += stack.size();
		
		System.out.println(cnt);
	}
}
