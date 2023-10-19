package Week11;

import java.util.*;
import java.io.*;

public class 백준_28066_타노스는요세푸스가밉다 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1; i<=N; i++) q.offer(i);
		
		int firstP=0;
		
		// 남은 청설모가 K보다 크거나 같아야 청설모 줄이기
		while(q.size() >= K) {
			
			// 첫번째 청설모 이후부터 삭제시키기
			firstP = q.poll();
			
			int cnt = 0;
			while(cnt < K-1) {
				q.poll();
				cnt++;
			}
			
			q.offer(firstP);
		}
		
		// 남은 설모가 K보다 작을 때
		System.out.println(q.poll());
	}
}
