// https://www.acmicpc.net/problem/2631
import java.io.*;
import java.util.*;

public class Main {	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		int N = Integer.parseInt(br.readLine());
		int[] child = new int[N];
		for(int i=0;i<N;i++){
			child[i] = Integer.parseInt(br.readLine());
		}

		// longest
		int answer = Integer.MIN_VALUE;
		int[] seq = new int[N]; // 가장 긴 증가하는 수열
		for(int i=0;i<N;i++) {
			seq[i] = 1; // init
			for(int j=0;j<i;j++) {
				if(child[j]<child[i]) {
					seq[i] = Math.max(seq[i],seq[j]+1);
				}
			}
			answer = Math.max(answer, seq[i]);
		}
		answer = N-answer;
		// output
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}
}
