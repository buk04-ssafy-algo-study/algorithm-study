// https://www.acmicpc.net/problem/6236
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] cost = new int[N];
		int left = -1;
		for(int i=0;i<N;i++){
			cost[i] = Integer.parseInt(br.readLine());
			left = Integer.max(left, cost[i]); // 이분탐색 탐색 범위
		}

		int right=100_000*10_000;
		int ans = 0;
		while(left<=right) {
			int withdrawl = (left+right)/2; // 인출 금액
			int cnt = 1; // 인출 횟수
			int money = withdrawl;
			for(int c:cost) {
				if(c>money){
					money = withdrawl;
					cnt++;
				}
				money -= c;
			}
			if(cnt<=M) {
				ans = withdrawl;
				right = withdrawl-1;
			} else {
				left = withdrawl+1;
			}
		}
		// output
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
	}
}
