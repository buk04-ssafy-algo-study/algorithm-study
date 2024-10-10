package Week56;

import java.util.*;
import java.io.*;

public class 백준_10825_국영수 {

	static class Score implements Comparable<Score>{

		String name;
		int kor;
		int eng;
		int math;
		
		public Score(String name, int kor, int eng, int math) {
			
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.math = math;
		}
		
		@Override
		public int compareTo(Score o) {
			
			if(this.kor == o.kor) {

				if(this.eng == o.eng) {
					
					if(this.math == o.math) {
						return this.name.compareTo(o.name);
					}
					else return o.math - this.math;
				}
				else return this.eng - o.eng;
			}
			else return o.kor - this.kor;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Score> pq = new PriorityQueue<>(); 
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int i=0; i<N; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			String name = st.nextToken();
			int kor = Integer.parseInt(st.nextToken());
			int eng = Integer.parseInt(st.nextToken());
			int math = Integer.parseInt(st.nextToken());
			
			pq.offer(new Score(name, kor, eng, math));
		}
		
		int pqSize = pq.size();
		
		for(int i=0; i<pqSize; i++) {
			sb.append(pq.poll().name+ "\n");
		}
		System.out.println(sb);
	}
}
