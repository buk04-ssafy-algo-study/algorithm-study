package Week19;

import java.util.*;
import java.io.*;

public class 백준_1461_도서관 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 책 갯수
		int M = Integer.parseInt(st.nextToken()); // 한 번에 들 수 있는 책 갯수;
		
		List<Integer> plusList = new ArrayList<>();  // 양수 위치
		List<Integer> minusList = new ArrayList<>(); // 음수 위치
		
		st = new StringTokenizer(br.readLine());
		
		int maxNum = 0; // 양수, 음수 중 제일 먼 위치
		
		for(int i=0; i<N; i++) {
			
			int num = Integer.parseInt(st.nextToken());
			int absNum = Math.abs(num);
			
			if(absNum > maxNum) maxNum = absNum; // 제일 먼 위치 저장
			
			if(num > 0) plusList.add(num);
			else minusList.add(absNum);
		}
		
		// 거리 순으로 내림차순
		Collections.sort(plusList, Collections.reverseOrder());
		Collections.sort(minusList, Collections.reverseOrder());
		
		int result = 0;
		
		// 양수 위치
		for(int i=0; i<plusList.size(); i++) {
			
			// M권 갖다놓고 돌아오기는 * 2, 제일 먼 곳은 편도로 이동 후 종료
			if(i % M == 0 && plusList.get(i) == maxNum) result += plusList.get(i);
			else if(i % M == 0) result += plusList.get(i) * 2;
		}
		
		// 음수 위치
        for(int i=0; i<minusList.size(); i++) {
			
			if(i % M == 0 && minusList.get(i) == maxNum) result += minusList.get(i);
			else if(i % M == 0) result += minusList.get(i) * 2;
		}
        
        System.out.println(result);
	}
}
