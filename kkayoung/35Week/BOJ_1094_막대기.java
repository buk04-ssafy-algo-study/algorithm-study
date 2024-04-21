// https://www.acmicpc.net/problem/1094
import java.io.*;
/*
64(1000000) => 1개
32(0100000) => 1개
48(0110000) => 2개
23(0010111) => 4개 
*/
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		int X = Integer.parseInt(br.readLine());
		int cnt = 0;

		for(int i=0;i<=6;i++) {
			int n = (int)Math.pow(2,i);
			if((X&n)==n) cnt++;
		}
		System.out.println(cnt);
	}

}
