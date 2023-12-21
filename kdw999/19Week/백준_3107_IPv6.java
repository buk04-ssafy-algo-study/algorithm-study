package Week19;

import java.io.*;
import java.util.*;

public class 백준_3107_IPv6 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		  String[] input = br.readLine().split(":");
	        int part = input.length; // 입력값을 :로 쪼갠 인덱스 갯수 
	        for (String tmp : input) {
	            if(tmp.length() == 0) part--; // 값이 없는 인덱스면 갯수에서 뺌
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        int idx = 0; // input 인덱스
	        int cnt = 0; // 현재 값이 입력된 input 인덱스 갯수
	        
	        while ( idx < input.length ) {
	            String tmp = input[idx];
	            if ( tmp.length() == 0 && part < 8){ // 인덱스 갯수가 8개가 안되고 값도 없다면 0000으로 채워줌
	                sb.append("0000");
	                sb.append(":");
	                cnt++;
	                part++;
	            } else if ( tmp.length() == 0 ) {
	                idx++;
	                continue;
	            } else if ( tmp.length() != 0) { // 값이 있다면 빈 만큼 0채워주기
	                for(int i = 0 ; i < 4 - tmp.length() ; i++){
	                    sb.append("0");
	                }
	                sb.append(tmp);
	                idx++;
	                cnt++;
	                if(cnt < 8) sb.append(":"); // 마지막 인덱스가 아니면 콜론 붙여줌
	            }
	        }
	        
	        // 마지막에 :: 오는 경우
	        while (cnt < 8) {
	            sb.append("0000");
	            cnt++;
	            if(cnt < 8) sb.append(":");
	        }
	        System.out.println(sb);
	    }
}
