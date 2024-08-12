// https://www.acmicpc.net/problem/17298
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Stack<Integer> s = new Stack<>();

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] answer = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int idx=0;idx<N;idx++) {
			A[idx] = Integer.parseInt(st.nextToken());
		}

		for(int idx=0;idx<N;idx++) {
			if(s.size()==0) {
				s.push(idx);
			} else {
				while(s.size()>0 && A[s.peek()]<A[idx]) {
					answer[s.pop()] = A[idx];
				}
				s.add(idx);
			}
		}

		while(!s.isEmpty()) {
			int i = s.pop();
			answer[i] = -1;
		}

		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb.toString());
	}

	
}
