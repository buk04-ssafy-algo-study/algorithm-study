// https://www.acmicpc.net/problem/2473
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		long[] answer = new long[3];
		long answerSum = Long.MAX_VALUE;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);

		for(int i=0;i<N-2;i++) {
			int l = i+1;
			int r = N-1;

			while(l<r) {
				long sum = (long)(arr[l]+arr[r])+arr[i];

				if(Math.abs(answerSum)>Math.abs(sum)) {
					answerSum = sum;
					answer[0] = arr[i];
					answer[1] = arr[l];
					answer[2] = arr[r];
				}
				if(sum<0) l++;
				else r--;
			}
		}

		Arrays.sort(answer);
		for(long i:answer) {
			System.out.print(i+" ");
		}
	}
	
}
