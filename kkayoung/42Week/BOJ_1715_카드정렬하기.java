// https://www.acmicpc.net/problem/1715
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		int N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		int answer = 0;
		while(pq.size()>1) {
			int a = pq.poll();
			int b = pq.poll();
			answer += (a+b);
			pq.offer(a+b);
		}
		
		System.out.println(answer);
	}
	
}
