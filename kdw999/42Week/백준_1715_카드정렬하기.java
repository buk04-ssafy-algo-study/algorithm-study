package Week42;

import java.io.*;
import java.util.*;

public class 백준_1715_카드정렬하기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // 오름차순 정렬 
		
		for(int i=0; i<N; i++) pq.add(Integer.parseInt(br.readLine())); // 숫자 카드 넣기
		
		int result = 0;
		
		while(pq.size() > 1) {
			
			int sum = pq.poll() + pq.poll();
			result += sum;
			
			pq.offer(sum);
			
		}
		
		System.out.println(result);
	}
}
