package Week15;

import java.util.*;
import java.io.*;

public class 백준_1107_리모컨 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 목표 채널
		int M = Integer.parseInt(br.readLine()); // 고장난 버른 갯수
		int curCh = 100;
		
		boolean[] brokenButton = new boolean[10];
		// 고장난 버튼이 있을 때만
		if(M > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
		    
			while(st.hasMoreTokens()) {
				int button = Integer.parseInt(st.nextToken());
				if(button >=0 && button <= 9) {
					brokenButton[button] = true; // 부서진 버튼임을 표시
				}
			}
		}
		
		
		String makeCh = "";
		int click=0;
		int minClick= Math.abs(N-curCh); // +, -버튼으로만 채널을 옮겼을 경우, 
		// ex) 101번 채널의 경우, 101번 3번 누르는 것보다 + 1번 누르는게 더 빠름
		
		// 목표 채널이 100인 경우를 위해 시작부터 채널 체크
		if(curCh == N) {
			System.out.println(minClick);
		}
		else {
			
			String goalCh = String.valueOf(N);
			
			for(int i=0; i<goalCh.length(); i++) {
				int num = goalCh.charAt(i)-48; // 채널 1개씩 누르기
			
			if(num >= 0 && num <= 9) {
				if(brokenButton[num]) { // 목표 채널 앞 번호부터 누르다가 해당 번호가 박살난 번호라면
					
					// 위 아래 1개씩 누르며 되는 번호 찾기
					int idx=1;
					while(true) {
						// +1 버튼은 되는지
						
						if(num+idx <= 9 && !brokenButton[num+idx]) {
							num = num+idx;
							break;
						}
						
						// -1 버튼은 되는지
						if(num-idx >= 0 && !brokenButton[num-idx]) {
							num = num-idx;
							break;
						}
						idx++;
					}
					makeCh += String.valueOf(num);
					
				}
				
				// 누를 수 있는 버튼이면 누르기
				else {
					makeCh += String.valueOf(num);
				}
				
				click++; // 채널 1개 누름
			}
			}
			
			int makeChNum = Integer.parseInt(makeCh); // 어느정도 목표 채널과 가깝게 누른 채널
			
			if(N >= makeChNum) {
				for(int i=makeChNum; i<=N; i++) {
					String num = String.valueOf(i);
					
					
					boolean flag = false; // 부서진 버튼 만나면
					for(int j=0; j<num.length(); j++) {
						if(brokenButton[num.charAt(j)-48]) {
							flag = true;
							break;
						}
					}
					
					if(!flag) {
						int totalClick = Math.abs(N-i)+click;
						minClick = Math.min(totalClick, minClick);
					}
				}
			}
			else if(N < makeChNum) {
				for(int i=makeChNum; i>=N; i--) {
					String num = String.valueOf(i);
					
					
					boolean flag = false; // 부서진 버튼 만나면
					for(int j=0; j<num.length(); j++) {
						if(brokenButton[num.charAt(j)-48]) {
							flag = true;
							break;
						}
					}
					
					if(!flag) {
						int totalClick = Math.abs(N-i)+click;
						minClick = Math.min(totalClick, minClick);
					}
				}
			}
			
			System.out.println(minClick);
		}
	
	} // main
}