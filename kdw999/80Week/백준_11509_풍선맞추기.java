package Week80;

import java.io.*;
import java.util.*;

public class 백준_11509_풍선맞추기 {
	
	static int N;
	static int[] H;
	static int[] arrow;
	
	public static void main(String[] args) throws IOException {
		init();
		solve();
		
	}
	
	private static void solve() {
		
		int result = 0;
		
		// 각 층 마다 화살을 누적해놓고 사용
		for(int i=0; i<N; i++) {
			
			int balloon = H[i];
			
			if(arrow[balloon] < 1) {
				arrow[balloon-1]++;
				result++;
			}
			else {
				arrow[balloon]--;
				arrow[balloon-1]++;
			}
		}
		
		System.out.println(result);
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = new int[N];
		arrow = new int[100000001];
		
		for(int i=0; i<N; i++) H[i] = Integer.parseInt(st.nextToken());
	}
}
