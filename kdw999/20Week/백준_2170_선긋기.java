package Week20;

import java.util.*;
import java.io.*;

class Line implements Comparable<Line> {
	
	int x, y;
	public Line(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int compareTo(Line o) {
		
		if(this.x == o.x) return o.y - this.y;
		return this.x - o.x;
	}
}

public class 백준_2170_선긋기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Line> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pq.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		int sum = 0;
		int px = pq.peek().x;
		int py = pq.peek().y;
		
		pq.poll();
		
		while(!pq.isEmpty()) {
			Line cur = pq.poll();
			
			// 라인이 끊긴다면
			if(cur.x > py) {
				sum += py - px;
				py = cur.y;
				px = cur.x;
				continue;
			}
			
			// 라인 안끊기면 더 큰 선의 길이로 초기화
			py = Math.max(py, cur.y);
		}
		
		System.out.println(sum+ py-px);
	}
}
