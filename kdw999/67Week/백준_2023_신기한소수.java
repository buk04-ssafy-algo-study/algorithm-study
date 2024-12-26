package Week67;

import java.io.*;
import java.util.*;

public class 백준_2023_신기한소수 {

	static int N;
	static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {

		init();
		solve(0, N);
		print();
	}
	static void print() {
		
		Collections.sort(list);

		for(int n : list) {
			System.out.println(n);
		}
	}
	
	static void solve(int num, int n) {
		
		if(n==0) list.add(num);
		
		 for(int i=1; i<10; i++) {
			 int tmp = 10 * num + i;
			 if(n>0 && primeNumber(tmp)) {
				 System.out.println("찾는 중: "+num);
				 solve(tmp, n-1);
			 }
		 }
	}
	
	static boolean primeNumber(int num) {
		
		if(num <= 1) {
			return false;
		}
		
		for(int i=2; i<=Math.sqrt(num); i++) {
			if(num % i == 0) return false;
		}
		
		return true;
	}
	
	static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
	}
}
