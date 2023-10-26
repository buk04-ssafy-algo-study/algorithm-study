// https://www.acmicpc.net/problem/6064
// k를 M으로 나눈 나머지가 x와 같고, k를 N으로 나눈 나머지가 y와 같다면 k번째 해는 <x:y>다
// 나머지가 0이면 되지 않도록 하기 위해 k에 1을 빼고 나머지 연산을 한 후 1을 다시 더한다
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=0;tc<T;tc++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int answer = -1;
			boolean found = false;
			for(int k=x;k<=N*M;k+=M){
				
				if(((k-1)%M+1)==x && ((k-1)%N+1)==y){
					answer = k;
					found = true;
					break;
				}
			}
			System.out.println(answer);
		}
	}
}
