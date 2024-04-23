package algo;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

 
public class Main {
		
	public static void main(String[] args) throws IOException {
	 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String inputLine = br.readLine();
		String outputLine = br.readLine();
		
		int[] input1 = new int[N]; // 첫 번째 전구를 누른 상태의 배열
		int[] input2 = new int[N]; // 첫 번째 전구를 안누른 상태의 배열
		int[] output = new int[N]; // 목표 전구 상태
		
		for(int i=0; i<N; i++) {
			input1[i] = inputLine.charAt(i)-48;
			input2[i] = inputLine.charAt(i)-48;
			output[i] = outputLine.charAt(i)-48;
		}
		
		input1[0] = 1 - input1[0];
		input1[1] = 1 - input1[1];
		
		int cnt1=1;
		int cnt2=0;
		
		for(int i=1; i<N; i++) {
			
			 
			// i-1이 다른지만 비교하면서 스위치 누르기, i-1이 다른데 안누르고 넘어가면 i-1은 맞출 기회가 없음

			// 첫 전구 누른 배열
			if(input1[i-1] != output[i-1]) {
				
				cnt1++;
				
				input1[i-1] = 1 - input1[i-1];
				input1[i] = 1 - input1[i];
				if(i != N-1) input1[i+1] = 1 - input1[i+1];
			 
			}
			
			// 첫 전구 안누른 배열
			
            if(input2[i-1] != output[i-1]) {
				
				cnt2++;
				
				input2[i-1] = 1 - input2[i-1];
				input2[i] = 1 - input2[i];
				if(i != N-1) input2[i+1] = 1 - input2[i+1];
			 
			}
			 
		}
		
		// i-1가 다를 때만 스위치를 눌러서 마지막 전구는 달라도 눌러줄 방법이 없음, 
		// 그래서 반복문 끝나면 마지막 전구가 같은 지 비교 두 배열 다 다르다면 전구 상태 똑같이 못맞춰줌
		if(input1[N-1] != output[N-1] && input2[N-1] != output[N-1]) System.out.println(-1);
		else if(input1[N-1] == output[N-1] && input2[N-1] == output[N-1]) System.out.println(Math.min(cnt1, cnt2));
		else if(input1[N-1] == output[N-1]) System.out.println(cnt1);
		else System.out.println(cnt2);
	}

  }
