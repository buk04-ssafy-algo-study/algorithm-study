package Week23;

import java.util.*;
import java.io.*;

public class 백준_22251_빌런호석 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 층 수 = 1 ~ 999999
		// 최대 자릿수 6
		// LED 최대 변환 횟수 42
		
		int N = Integer.parseInt(st.nextToken()); // 최대 층 수
		int K = Integer.parseInt(st.nextToken()); // 디스플레이 보이는 숫자 자릿수
		int P = Integer.parseInt(st.nextToken()); // LED 반전 갯수
		int X = Integer.parseInt(st.nextToken()); // 현재 층, 시작 층
		
		// 각 숫자에서 다른 숫자가 되기 위해 필요한 반전 횟수 배열
		int[][] makeNum = {
				{0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
				{4, 0, 5, 3, 2, 5, 6, 1, 5, 4},
				{3, 5, 0, 2, 5, 4, 3, 4, 2, 3},
				{3, 3, 2, 0, 3, 2, 3, 2, 2, 1},
				{4, 2, 5, 3, 0, 3, 4, 3, 3, 2},
				{3, 5, 4, 2, 3, 0, 1, 4, 2, 1},
				{2, 6, 3, 3, 4, 1, 0, 5, 1, 2},
				{3, 1, 4, 2, 3, 4, 5, 0, 4, 3},
				{1, 5, 2, 2, 3, 2, 1, 4, 0, 1},
				{2, 4, 3, 1, 2, 1, 2, 3, 1, 0},	
		};
		
		char[] curFloor = String.valueOf(X).toCharArray(); // 현재, 시작 층수를 한 자씩 자른 배열
		char[] startFloor = new char[K]; // 시작 층수를 담은 배열을 자릿수에 맞게 변환하기 위한 배열
		
		// 층 배열의 끝 인덱스부터 현재 층의 숫자를 알맞게 배치, 남는 앞 부분은 0
		for(int i=0; i<curFloor.length; i++) startFloor[K-1-i] = curFloor[curFloor.length-1-i];
		                                    // 006542
		                                    // 6542
		int cnt=0; // 경우의 수
		
		// 시작 층(X)에서 모든 층으로 만들기 위한 탐색하기
		for(int i=1; i<=N; i++) {
			if(i == X) continue; // 현재 층은 경우의 수에 포함하면 안됨
			
			int reverse = 0; // 반전 횟수
			
			char[] temp = String.valueOf(i).toCharArray(); // 현재 탐색 중인 층을 배열로 변환
			char[] searchFloor = new char[K]; // 탐색 중인 층을 자릿수에 맞게 변환하기 위한 배열
			for(int j=0; j<temp.length; j++) searchFloor[K-1-j] = temp[temp.length-1-j];
			
			// searchFloor 자릿수 만큼 탐색, 시작층과 탐색층을 비교하여 시작층을 탐색층을 바꾸는 작업
			for(int k=0; k<K; k++) {
				if(startFloor[k] == searchFloor[k]) continue; // 같은 자릿 수에 값이 같다면 바꿀 필요 X
				// 006542
				// 006372
				int r=0;
				int c=0;
				
				// 0 ~ 9 숫자 r을
				if(startFloor[k] == 0) r = 0; // 시작 층 배열의 자릿수가 0이면 그냥 0 저장
				else r = startFloor[k] - 48; // 다른 숫자면 아스키 코드에 맞게 저장
				
				// 0 ~ 9 숫자 c로 변환하기 위한 초기화
				if(searchFloor[k] == 0) c = 0;
				else c = searchFloor[k] - 48;
				
				reverse += makeNum[r][c]; // r행 c열의 값은 r값을 c로 변환한 횟수가 담김, 그만큼 더하기
				if(reverse > P) break; // 반전 횟수를 넘어가면 그만 탐색
			}
			
			if(reverse <= P) cnt++; // 반전 횟수를 넘지않고 하나의 경우의 수를 만들었다면 횟수+1;
		}
		System.out.println(cnt);
	}
}
