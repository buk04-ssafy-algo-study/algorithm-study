package Week11;

import java.util.*;
import java.io.*;

public class 백준_16922_로마숫자만들기 {
	
	static int[] romeNum; // 로마 숫자
	static int[] pickNum; // 뽑은 숫자
	static int N;
	static Set<Integer> set;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		pickNum = new int[N];
		romeNum = new int[]{1, 5, 10, 50};
		set = new HashSet<>();
		
		comb(0, 0);
		
		int cnt =0;
		for(int s : set) {
			cnt++;
		}
		System.out.println(cnt);
		
	}
	
	public static void comb(int cnt, int start) {
		
		if(cnt == N) {
		  	
			int sum = 0;
			for(int i=0; i<N; i++) {
				sum += pickNum[i];
			}
//			System.out.println(sum);
			set.add(sum);
			return;
		}
		
		for(int i=start; i<romeNum.length; i++) {
			pickNum[cnt] = romeNum[i];
//			System.out.println(cnt + " / " + romeNum[i]);
			comb(cnt+1, i);
		}
	}
}
