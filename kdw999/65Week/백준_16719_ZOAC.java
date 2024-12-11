package Week65;

import java.io.*;
import java.util.*;


// 처음엔 문자열을 왼쪽, 오른쪽만 붙는 걸로 착각해서 pq써서 매 번 가장작은 문자열을 찾으려고 했음 그러나 양 끝이 아니라 사이에도 문자가 들어와서 가장 작은 경우가 생기기 때문에 재귀를 통한 가장작은 값을 찾아야해줬음
public class 백준_16719_ZOAC {
	
	static String line;
	static boolean[] usedAlpha;

	public static void main(String[] args) throws IOException {
		
		init();
		solve(0, line.length()-1);
	 }
	
	static void solve(int left, int right) {
		
	  if(left > right) return;
	  
	  int minIdx = -1;
	  
	  for(int i=left; i<=right; i++) {
		  
		  if(!usedAlpha[i]) { // 사용안한 문자면
			  
			  // 현재 인덱스의 문자가 minIdx의 위치 문자보다 더 작은 값이면 가장 앞에 오는 문자 변경
			  if(minIdx == -1 || line.charAt(i) < line.charAt(minIdx)) {
				  minIdx = i;
			  }
		  }
	  }
	  
	  if(minIdx == -1) return;
	  
	  usedAlpha[minIdx] = true; // 쓴 문자 처리
	  
	  print();
	  
	  // ST <- A -> RTLINK
	  solve(minIdx+1, right); // 첫 번째 문자 중 제일 작은 문자를 찾으면 그 다음 제일 작은 문자열들은 오른쪽 탐색에서 나온다.
	  solve(left, minIdx-1); // 오른쪽 탐색 끝나면 이제 나머지는 왼쪽에서 작은 문자 붙이기
	}
	
	static void print() {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<line.length(); i++) {
			if(usedAlpha[i]) {
				sb.append(line.charAt(i));
			}
		}
		System.out.println(sb);
	}
	 
	
	static void init() throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine();
		usedAlpha = new boolean[line.length()];
		
	}
}
