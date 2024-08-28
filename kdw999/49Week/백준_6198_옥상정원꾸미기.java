package Week49;

import java.io.*;
import java.util.*;

public class 백준_6198_옥상정원꾸미기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Stack<Long> building = new Stack<>();

		Long result =0L;
		
		for(int i=1; i<=N; i++) {
			
			Long height = Long.parseLong(br.readLine());
			
			// 스택에 값이 있고 가장 최근에 저장한 높이가 현재 높이보다 작다면 안보이기에 스택에서 빼줌
			while(!building.isEmpty() && building.peek() <= height) {
				building.pop();
			}
			
			// 현재 높이보다 큰 높이들은 앞서 스택에 저장되어있기에 현재 높이보다 큰 높이들이 스택 사이즈 만큼 있다는 뜻
			// 그만큼 스택에 저장
			result += building.size();
			building.add(height); // 현재 높이 저장
		}
		
		System.out.println(result);
	}
}
