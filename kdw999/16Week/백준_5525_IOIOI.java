package Week16;

import java.io.*;
import java.util.*;

public class 백준_5525_IOIOI {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 필요 O갯수
		int M = Integer.parseInt(br.readLine()); // 문자열 길이
		char[] S = br.readLine().toCharArray(); // 문자열


		int result=0;
		int cnt=0;
		
		// I O I가 만들어지는 지 확인, 만들어지면 i를 2칸씩 띄우면서 O 양 옆에 I가 있는지 확인하면서 계속 IOI되는지 판단
	    for(int i=1; i<M-1; i++) {
	    	if(S[i-1] == 'I' && S[i] == 'O' && S[i+1] == 'I') {
	    		cnt++;
	    		
	    		if(cnt == N) { // O갯수가 N이랑 같아지면
	    			cnt--;
	    			result++;
	    		}
	    		i++;
	    	}
	    	else cnt =0;
	    }
	    
	    System.out.println(result);

	}

}
