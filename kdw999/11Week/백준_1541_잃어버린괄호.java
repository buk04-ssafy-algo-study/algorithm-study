package Week11;

import java.util.*;
import java.io.*;

public class 백준_1541_잃어버린괄호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer mst = new StringTokenizer(br.readLine(), "-"); // - 기준으로 문자열 쪼개기
		
		int sum = 100000;
		
		// 토큰이 있다면 계속 돌기
		while(mst.hasMoreTokens()) {
		
			int div = 0;
			
			StringTokenizer pst = new StringTokenizer(mst.nextToken(), "+"); // +로 쪼개기
			
			while(pst.hasMoreTokens()) div += Integer.parseInt(pst.nextToken());
			
			// -뒤에 오는 숫자들을 다 더해야 하는데 제일 처음에 오는 숫자들은 무조건 +라 -나오기 전까지 나온 수들을 합하고 초기화 
			if(sum == 100000) sum = div;
			else sum -= div; // - 이후 나온 숫자들을 괄호쳐서 빼버리면 값 최소
		}
		System.out.println(sum);

	}
}
