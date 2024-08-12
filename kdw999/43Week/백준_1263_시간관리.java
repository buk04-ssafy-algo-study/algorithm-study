package Week43;

import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
	
	int t;
	int s;
	
	public Node(int t, int s) {
		this.t = t;
		this.s = s;
	}

	@Override
	public int compareTo(Node o) {
		return o.s - this.s;
	}
}

public class 백준_1263_시간관리 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
		Node[] arr = new Node[N];
		
		for(int i=0; i<N; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			arr[i] = new Node(t, s);
			
		}
		
		Arrays.sort(arr);

		int time = arr[0].s;
		
		for(int i=0; i<N; i++) {
			
			// 내림차순 정렬된 상태, 작업 마무리 시간 - 걸리는 시간
			time = Math.min(arr[i].s, time) - arr[i].t;
			
			if(time < 0) {
				time = -1;
				break;
			}
		}
		
		System.out.println(time);
	}
}
