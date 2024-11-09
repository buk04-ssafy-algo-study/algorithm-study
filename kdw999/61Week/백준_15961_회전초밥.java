package Week61;

import java.io.*;
import java.util.*;

public class 백준_15961_회전초밥 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 회전 초밥 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		// k개 연속으로 먹고 k개 안에 쿠폰 번호가 포함되있는지도 체크 
		int maxSushiDish = 0;
		int currentSushiDish = 0;
		
		int[] sushi = new int[d+1]; // 고른 초밥 몇 갠지 체크하는 배열
		int[] sushiSequence = new int[N]; // 회전초밥 순서
		for(int i=0; i<N; i++) {
			sushiSequence[i] = Integer.parseInt(br.readLine());
		}
		
        for(int i=0; i<N+k; i++) {
			
        	int sushiNum = 0;
        	if(i < N) sushiNum = sushiSequence[i];
        	else sushiNum = sushiSequence[i-N];
			
			if(sushi[sushiNum] == 0) {
				sushi[sushiNum]++;
				currentSushiDish++;
			}
			else if(sushi[sushiNum] >= 1) {
				sushi[sushiNum]++;
			}
		    
			if(i>=k) {
				sushi[sushiSequence[i-k]]--;
				if(sushi[sushiSequence[i-k]] == 0) currentSushiDish--;
			}
			
			if(sushi[c] == 0) {
				maxSushiDish = Math.max(maxSushiDish, currentSushiDish+1);
			//	System.out.println("쿠폰 접시 없을 때 i : "+i +" / 쿠폰 접시 수 : "+ sushi[k] + " / 접시 번호: "+ sushiNum + " / 접시 갯수: "+ sushi[sushiNum]);
			}
			
			maxSushiDish = Math.max(maxSushiDish, currentSushiDish);
//			System.out.println("i : "+i +" / 현재 접시 수 : "+ currentSushiDish + " / 현재 번호 접시 수 : "+ sushi[sushiNum] + " / 현재 접시 번호 : " + sushiNum);
		}
        
        System.out.println(maxSushiDish);
	}
}
