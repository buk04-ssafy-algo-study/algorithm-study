package Week17;

import java.util.*;
import java.io.*;

public class 백준_23814_아저는볶음밥이요 {

	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long D = Long.parseLong(br.readLine()); // 중복해서 받는 군만두 수
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());// 짜장
		long M = Long.parseLong(st.nextToken()); // 짬뽕
		long K = Long.parseLong(st.nextToken()); // 남은 인원
		
	    long[] mandu = new long[4];
		long[] bob = new long[4];
		
		long rn = N % D; // 초기 짜장 갯수에서 군만두 만들고 남은 갯수 -> D-rn = 초기 짜장갯수에서 새 군만두를 얻기위해 필요한 짜장 갯수
		long rm = M % D; // 초기 짬뽕 갯수에서 군만두 만들고 남은 갯수
		
		long maxMandu = (N+M+K)/D; // 최대 군만두 갯수는 N M K를 합친 수로 만들 수 있다.
		
		mandu[0] = N/D +M/D +K/D; // 볶음밥 주문을 건들지 않은 경우의 군만두 갯수
		bob[0] = K; // 볶음밥 주문을 건들지 않은 경우의 볶음밥
		
		mandu[1] = (N + (D-rn))/D + M/D + (K-(D-rn))/D; // 볶음밥 주문에서 빼서 짜장을 더 시켰을 경우 군만두 갯수
		bob[1] = K-(D-rn); // 볶음밥 주문에서 뺀 만큼 짜장에 더했으니 볶음밥 주문 갯수 빼줘야 함
		
		mandu[2] = N/D + (M + (D-rm))/D + (K-(D-rm))/D; // 짬뽕 경우
		bob[2] = K-(D-rm);
		
		mandu[3] = (N + (D-rn))/D + (M + (D-rm))/D + (K-(D-rn)-(D-rm))/D; // 짜장, 짬뽕 둘 다
		bob[3] = K-(D-rn) -(D-rm);
		
		long result = 0;
		for(int i=0; i<4; i++) {
			if(bob[i] > result && mandu[i] == maxMandu) {
				result = bob[i];
			}
		}
		System.out.println(result);
	}
}
