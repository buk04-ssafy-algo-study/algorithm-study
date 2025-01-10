package Week65;

import java.io.*;
import java.util.*;

public class 백준_17952_과제는끝나지않아 {
	
	static class Homework {
		
		int score, time;
		
		public Homework(int score, int time) {
			this.score = score;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		
		homework();
	 }
	
	static int result=0;
	
	static void homework() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Stack<Homework> stack = new Stack<>();
		
		StringTokenizer st;
		
		for(int i=0; i<N; i++) {
			
		st = new StringTokenizer(br.readLine());
		
		int num = Integer.parseInt(st.nextToken());
		
		 if(num == 1) {
			
		   int score = Integer.parseInt(st.nextToken());
		   int time = Integer.parseInt(st.nextToken());
		   
		   time--;
		   
		   if(time == 0) result += score;
		   else stack.push(new Homework(score, time));
		   
		 }
		
		 else {
			
			if(stack.size() == 0) continue;
			
			Homework h = stack.pop();
			
			h.time--;
			if(h.time == 0) result += h.score;
			else stack.push(new Homework(h.score, h.time));
			
		 }
		
		}
		System.out.println(result);
	}
 
}
