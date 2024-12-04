package Week64;

import java.util.*;
import java.io.*;

public class 백준_1911_흙길보수하기 {
	
	static class Pool implements Comparable<Pool>{
		
		int start, end;
		
		public Pool(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Pool o) {
			return this.start - o.start;
		}
	}
	
	static int N, L, num;
	static Pool[] pos;  
	public static void main(String[] args) throws IOException {
		
		init();
		solve();
		print();
		
	} // main
	
	static void solve() {
		
		int curLength = 0; // 널빤지로 덮은 마지막 위치
		
		
		// 마지막 웅덩이 덮을 때 까지
		for(int i=0; i<pos.length; i++) {
			
			int start = pos[i].start;
			int end = pos[i].end;
			
			if (curLength < start) {
                // 현재 덮은 범위가 웅덩이의 시작보다 이전이면 새 널빤지를 시작해야 함
                curLength = start;
            }
			int needLength = end - curLength + 1; // 현재 덮을 웅덩이 길이
			
			// 이전에 덮은 널빤지가 엄청 길어서 end - curLength가 음수가 될 수도 있음
			if(needLength > 0) {
				int needPlank = needLength / L; // 필요 널빤지 갯수
				if(needLength % L > 0) {
					needPlank++;
				}
				num += needPlank;
				curLength += (needPlank * L); 
			}
		}
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		pos = new Pool[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
				
				int start =  Integer.parseInt(st.nextToken());
				int end =  Integer.parseInt(st.nextToken())-1;
				
				pos[i] = new Pool(start, end);
		}
		
		Arrays.sort(pos);
		
//		for(int i=0; i<N; i++) {
//			System.out.println(pos[i].start + " / " + pos[i].end);
//		}
	}
	
	static void print() {
		
		System.out.println(num);
	}
}
