// https://www.acmicpc.net/problem/14719
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int total = 0;

		st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken()); // 세로
		int W = Integer.parseInt(st.nextToken()); // 가로
		
		int[] heights = new int[W];

		st = new StringTokenizer(br.readLine());
		for(int i=0;i<W;i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<W-1;i++) {
			int left = -1; 
			int right = -1;

			for(int l=0;l<i;l++) {
				left = Math.max(left, heights[l]);
			}

			for(int r=i+1;r<W;r++) {
				right = Math.max(right, heights[r]);
			}

			if(heights[i]<left && heights[i]<right) {
				total = total + Math.min(left, right)-heights[i];
			}
		}

		System.out.println(total);
	}

}
