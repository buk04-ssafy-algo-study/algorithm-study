package Week49;

import java.io.*;
import java.util.*;

public class 백준_2529_부등호 {

	static int k;
	static boolean[] usedNum = new boolean[10];
	static String[] arrow;
	static List<String> madeNum = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		k = Integer.parseInt(br.readLine());
		arrow = new String[k];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<k; i++) arrow[i] = st.nextToken();
		
		// 맨 앞자리 0부터 숫자 붙여나가기
		for(int i=0; i<=9; i++) {
			
			usedNum[i] = true;
			searchNumber(i, 0, i+"");
			usedNum[i] = false;
		}
		
		// 만든 숫자 정렬
		Collections.sort(madeNum);
		System.out.println(madeNum.get(madeNum.size()-1));
		System.out.println(madeNum.get(0));
	}
	
	public static void searchNumber(int start, int cnt, String addNum) {
		
		// 부등호 수+1 되면 숫자 완성
		if(addNum.length() == k+1) {
			madeNum.add(addNum);
			return;
		}
		
		// 붙을 숫자 찾기
		for(int i=0; i<=9; i++) {
			
			// 사용안 한 숫자만
			if(!usedNum[i]) {
				
			// < 부등호
			if(arrow[cnt].equals("<")) {
				 
				// 조건 충족하면
				if(start < i) {
				 
					usedNum[i] = true;
					searchNumber(i, cnt+1, addNum+i);
					usedNum[i] = false;
				}
			}
			
			// > 부등호
			else {
			
			   if(start > i) {
				   usedNum[i] = true;
				   searchNumber(i, cnt+1, addNum+i);
				   usedNum[i] = false;
			   }
			}
		  }
		}
	}
}
