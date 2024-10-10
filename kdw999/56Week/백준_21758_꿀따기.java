package Week56;

import java.util.*;
import java.io.*;

// 꿀벌 2마리, 꿀통 1개 총 3개 중 2개의 위치가 양 끝에 고정

public class 백준_21758_꿀따기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] place = new int[N];
		int honeySum = 0;
		
		for(int i=0; i<N; i++) {
			place[i] = Integer.parseInt(st.nextToken());
			honeySum += place[i];
		}
		
		int maxHoney = 0;
	
		// 꿀벌 1마리 왼쪽 고정, 꿀통 오른쪽 고정
		int leftBeeFix = honeySum - place[0]; // 왼쪽이 고정일 때 꿀벌이 캐는 꿀
		int moveBee = leftBeeFix; // 왼쪽 1칸이 꿀벌 고정이고 다른 칸들은 남은 꿀벌 한마리가 움직이면서 꿀을 캐야함

		for(int i=1; i<N-1; i++) {
			
        	int fixBeeSum = leftBeeFix - place[i]; // 왼쪽 고정인 벌은 움직이는 벌이 위치한 꿀 양만 빼주면 됨
        	moveBee = moveBee - place[i]; // 이동하는 벌은 이전 칸의 꿀 양은 포함되지 않아야 한다.
        	
        	int fixMoveSum = fixBeeSum + moveBee;
        	
        	maxHoney = Math.max(maxHoney, fixMoveSum);
		}
		
        // 꿀벌 1마리 오른쪽 고정, 꿀통 왼쪽 고정
		int rightBeeFix = honeySum - place[N-1];
		moveBee = rightBeeFix;
		
		for(int i=N-2; i>0; i--) {
			
			int fixBeeSum = rightBeeFix - place[i];
			moveBee = moveBee - place[i];
			
			int fixMoveSum = fixBeeSum + moveBee;
			
			maxHoney = Math.max(maxHoney, fixMoveSum);
		}
		
		// 꿀벌 1마리 왼쪽 고정, 다른 1마리 오른쪽 고정, 꿀통이 사이에서 움직임
		leftBeeFix = 0; // 움직이는 꿀통의 꿀을 합침
		rightBeeFix = honeySum - place[N-1];
		
		for(int i=1; i<N-1; i++) {
			
			leftBeeFix = leftBeeFix + place[i];
			rightBeeFix = rightBeeFix - place[i-1];
			
			int leftRightSum = leftBeeFix + rightBeeFix;
			
			maxHoney = Math.max(maxHoney, leftRightSum);
		}
		
		System.out.println(maxHoney);
	}
}
