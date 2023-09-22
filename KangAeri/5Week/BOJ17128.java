package probs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ17128 {
	private static StringBuilder sb = new StringBuilder();
	private static int[] score;
	private static int[] tmp;
	private static int[] trick;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=0, q=0, i=0, j=0,sum=0, res=0;
		String[] in;
		in = br.readLine().split(" ");
		
		n = Integer.parseInt(in[0]);
		q = Integer.parseInt(in[1]);
		score = new int[n];
		tmp = new int[n];
		in = br.readLine().split(" ");
		for(i=0; i<n; i++) {
			score[i] = Integer.parseInt(in[i]);
		}
		trick = new int[q];
		in = br.readLine().split(" ");
		for(i=0; i<q; i++) {
			trick[i] = Integer.parseInt(in[i])-1;
		}
		sum=0;
		//initial sum
		for(i=0; i<n; i++) {
			tmp[i] = score[i]*score[(i+1)%n]*score[(i+2)%n]*score[(i+3)%n];
			sum += tmp[i];
			//System.out.println(tmp[i]);
		}
		
		//System.out.println(sum);
		///////////
		for(i=0; i<q; i++) {
			for(j=0;j<4;j++) {
				sum-= tmp[(trick[i]-j+n)%n];
				tmp[(trick[i]-j+n)%n]*=(-1);
				sum+= tmp[(trick[i]-j+n)%n];
			}
			sb.append(sum + "\n");
		}
		
		System.out.println(sb);
		
		
		
	}
}
