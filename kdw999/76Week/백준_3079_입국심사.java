package Week76;

import java.io.*;
import java.util.*;

public class 백준_3079_입국심사 {

	static int N, M;
	static int[] checkpoint;
	
	public static void main(String[] args) throws IOException {
		 
		init();
		solve();
	}
	
	private static void solve() {
		
	    long low = 1; // 심사 최소 시간
		long high = (long)checkpoint[N-1] * M; // 심사 최대 시간
		
		// 최소 심사 시간을 찾기 위한 범위 줄이기
		while(low <= high) {
			
			long mid = (low + high) / 2; // 범위 반토막
			
			long checkPerson = 0; // 심사 통과한 인원
			
			for(int i=0; i<checkpoint.length; i++) {
				
				checkPerson += mid / checkpoint[i]; // 각 심사대에서 현재 mid시간 동안 몇 명 처리하는지
			
				if(checkPerson >= M) break;
			}
			
			if(checkPerson >= M) {
				high = mid-1; // M명을 충족하면 최대 시간 줄이기 (인원을 충족했더라도 더 적은 시간으로도 충족하는 경우를 찾아야함)
			}
			else low = mid+1; // 인원 충족이 안되면 최소 시간 증가시키기
		}
		
		System.out.println(low);
	}
	
	private static void init() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		checkpoint = new int[N];
		
		for(int i=0; i<N; i++) {
			checkpoint[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(checkpoint);
	}
}
