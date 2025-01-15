package Week70;

import java.io.*;
import java.util.*;

public class 백준_2666_벽장문의이동 {

	static int N, result, openDoor, openDoor2;
	static int[] doorOrder;
	public static void main(String[] args) throws IOException {
	
		init();
		// 열린 문 2개와 열려는 문 순서로 재귀
		System.out.println(moveDoor(openDoor, openDoor2, 1));
	}
	
	private static int moveDoor(int od, int od2, int idx){
		
		// 문 다 열면 끝
		if(idx >= doorOrder.length) return 0;
		
		// 열려는 문과 열린 문 2곳의 길이 계산
		int len1 = Math.abs(od-doorOrder[idx]);
		int len2 = Math.abs(od2-doorOrder[idx]);
		
		// 문을 열기 위한 이동 횟수 + 새로 열린 문과 기존에 열렸던 문, 다음에 열려는 문 인덱스로 재귀
		return(Math.min(len1+moveDoor(od2, doorOrder[idx], idx+1), 
				len2+moveDoor(od, doorOrder[idx], idx+1)));
	}
	
	private static void init() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 문 갯수
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		openDoor = Integer.parseInt(st.nextToken()); // 초기 열린문1
		openDoor2 = Integer.parseInt(st.nextToken()); // 초기 열린문2
		
		int M = Integer.parseInt(br.readLine()); // 여는 문 갯수
		doorOrder = new int[M+1];
		
		for(int i=1; i<=M; i++) {
			doorOrder[i] = Integer.parseInt(br.readLine());
		}
	}
}
