package Week73;

import java.io.*;
import java.util.*;

public class 백준_15565_귀여운라이언 {

	static int N, K;
	
	public static void main(String[] args) throws IOException {
		init();
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		String set = ""; // 전체 집합
 
		int result = 987654321;
		int idx = 0;
        int num = 0;		
//		while(st.countTokens() > 0) { // 토큰 갯수만큼 반복

		int[] doll = new int[N];
		for(int i=0; i<N; i++) {
			doll[i] =  Integer.parseInt(st.nextToken()); // 인형 정보
		}

		int left = 0;
        for(int right=0; right<doll.length; right++) {
			
        	if(doll[right] == 1) num++;


        	while (num >= K) {
                result = Math.min(result, right - left + 1); 

                if (doll[left] == 1) {
                    num--; // 왼쪽 포인터가 가리키는 인형이 라이언이면 개수 감소, 
                    //다음 라이언 인형을 만나면 left 포인터를 움직이는 반복문에서 탈출하기 위함
                }
                left++; // 왼쪽 포인터 이동
            }
		}
//		}
		
//		int rs = ryanList.size()-2;
//		// 라이언 위치로 라이언이 3개인 집합 길이 계산, 인덱스 2칸 차이나면 라이언이 3개
//		for(int i=0; i<rs; i++) {
//			result = Math.min((ryanList.get(i+2)- ryanList.get(i))+1, result);
//		}
		
		System.out.println(result == 987654321 ? -1 : result);
	}
}
