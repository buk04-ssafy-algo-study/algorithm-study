package Week59;

import java.util.*;
import java.io.*;

public class 백준_14247_자르기나무 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] tree = new int[N][2];
		
		for(int i=0; i<2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				tree[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		long sum = 0;
		int idx = 0;
		
		Arrays.sort(tree, (a, b) -> Integer.compare(a[1], b[1]));
		
		while(idx < N) {
			
			sum += (long)(tree[idx][0] + (idx * tree[idx][1]));
			idx++;
		}
		System.out.println(sum);
	}
}
