// https://www.acmicpc.net/problem/3107
import java.io.*;
import java.util.*;

public class Main {	

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		String addr = br.readLine();
		if(addr.charAt(0)==':'&&addr.charAt(1)==':') {
			// 주소가 ::로 시작
			String[] tmp = addr.split("::")[1].split(":");
			addGroup(8-tmp.length);
			addZero(tmp);
		}
		else if(addr.charAt(addr.length()-1)==':'&&addr.charAt(addr.length()-2)==':') {
			// 주소가 ::로 끝남
			String[] tmp = addr.split("::")[0].split(":");
			addZero(tmp);
			sb.append(":");
			addGroup(8-tmp.length);
			sb.setLength(sb.length()-1); // 마지막 ':' 제거
		}
		else {
			String[] tmp= addr.split("::");
			if(tmp.length==1){
				// 주소에 :: 가 존재하지 않음
				addZero(tmp[0].split(":"));
			} 
			else {			
				// 주소 중간에 ::가 있음
				String[] left = tmp[0].split(":");
				String[] right = tmp[1].split(":");
				
				addZero(left);
				sb.append(":");
				addGroup(8-(left.length+right.length));
				addZero(right);
			}
			
		}
		
		// output
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void addGroup(int n) {
		// ::로 생략한 0000: 수만큼 0000:를 추가한다
		for(int i=0;i<n;i++){
			sb.append("0000:");
		}
	}

	static void addZero(String[] splitedAddr){ // 각 그룹이 4자리가 될 때까지 0 추가
		for(int i=0;i<splitedAddr.length;i++) {
			String hex = splitedAddr[i];
			for(int j=0;j<4-hex.length();j++){
				sb.append("0");
			}
			sb.append(hex);

			if(i==splitedAddr.length-1) break;
			sb.append(":");
		}
	}
}
