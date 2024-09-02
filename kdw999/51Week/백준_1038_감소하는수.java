package Week51;

import java.util.*;
import java.io.*;

public class 백준_1038_감소하는수 {

	static List<Long> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine());
	
		list = new ArrayList<>();
		
		for(Long i=0L; i<=9; i++) makingNum(i); 
		
		Collections.sort(list);
		
		if(N >= list.size()) {
			System.out.println(-1);
		}
		else {
			System.out.println(list.get(N));
		}
	}
	
	static void makingNum(Long num) {
		
		if(num > 9876543210L) return;
		
		list.add(num);
		for(Long i=0L; i<=9; i++) {
			
			// 현재 숫자의 끝자리 수보다 작은 수만 붙일 수 있음
			if(num % 10 > i) makingNum(num * 10 + i);
		}
	}
}
