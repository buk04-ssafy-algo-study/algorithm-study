package Week68;

import java.io.*;
import java.util.*;

public class 백준_1327_소트게임 {

	static int N, K, result;
	static String str;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		 
		init();
		solve();
		print();
	}
	
	private static void solve() {

		// 뒤집으면서 만들어지는 문자열들을 Queue에 넣고 꺼내면서 문자열 뒤집기
		Queue<String> q = new ArrayDeque<>();
		
		// 만들어진 문자열들의 중복을 거르기 위해 Set 활용 
		Set<String> visited = new HashSet<>();
		
		String start = str; // init에서 설정한 문자열 초기 상태
		
		q.add(start);
		visited.add(start); // 뒤집으면서 만들어진 문자열의 중복을 거르기 위해 set에 저장
		
		result = 0;
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			
			// 현재 Queue에 저장된 문자열들만 뒤집기 위해 사이즈만큼만 반복
			for(int i=0; i<size; i++) {
				
				String cur = q.poll(); // 문자열 꺼내기
//				System.out.println(cur);
				   
				   // 오름차순 정렬된 상태인지 확인
				   if(isAscendingOrder(cur)) return;
				
				   for(int j=0; j<= N-K; j++) {
					   
					   String next = reverse(cur, j);
					   
					   // 만들어진 적 없는 문자열이면 Queue와 Set에 추가
					   if(!visited.contains(next)) {
						   visited.add(next);
						   q.add(next);
					   }
				   }
			}
		 
		    result++; // 뒤집은 횟수 +1
		} // while
		
		result = -1; // 오름차순 정렬으로 메서드가 종료되지 않았다면 오름차순이 안된다는 것
	}
	
	private static String reverse(String s, int startIdx) {
		
		char[] charArr = s.toCharArray();
		int left = startIdx;
		int right = startIdx + K - 1;
		
		// right 인덱스가 더 클 때까지 문자열 뒤집기
		while(left < right) {
			char temp = charArr[left];
			charArr[left] = charArr[right];
			charArr[right] = temp;
			left++;
			right--;
		}
		
		return new String(charArr);
	}
	
	private static boolean isAscendingOrder(String s) {
		
		for(int i=s.length()-1; i>0; i--) {
			if(s.charAt(i) < s.charAt(i-1)) return false;
		}
		return true;
	}
	
	private static void print() {
		System.out.println(result);
	}
	
	private static void init() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
        // 배열을 문자열로 변환
		StringBuilder sb = new StringBuilder();
		for(int num : arr) sb.append(num);
		
		str = sb.toString(); // 문자열 변환
	}
}
