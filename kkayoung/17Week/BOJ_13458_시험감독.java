// https://www.acmicpc.net/problem/13458
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// input
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){ 
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long answer = 0L;
		for(int i=0;i<N;i++){ 
			A[i] -= B;
			answer++;
			if(A[i]<=0) continue;
			answer += (A[i]/C);
			if(A[i]%C>0) answer++;
		}
		// output
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}
}
