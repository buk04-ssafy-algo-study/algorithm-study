package boj_study.w3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main_1080_arraySwitch {
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		int n=0,m=0,i=0,j=0,p=0,q=0,cnt=0;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] sp = in.readLine().split(" ");
		n = Integer.parseInt(sp[0]);
		m = Integer.parseInt(sp[1]);
		int[][] arrA = new int[n][m];
		int[][] arrB = new int[n][m];
		
		//Read array A
		for(i=0;i<n;i++) {
			String rowread= in.readLine();
			for(j=0;j<m;j++) {
				arrA[i][j] = rowread.charAt(j)-48;
			}
		}
		
		//Read array B
		for(i=0;i<n;i++) {
			String rowread= in.readLine();
			for(j=0;j<m;j++) {
				arrB[i][j] = rowread.charAt(j)-48;
			}
		}
		
		//compute cnt
		cnt=0;
		for(i=0;i<n-2;i++) {
			for(j=0;j<m-2;j++) {
				if(arrA[i][j] == arrB[i][j]) {
					continue;
				}
				for(p=0;p<3;p++) {
					for(q=0;q<3;q++) {
						arrA[i+p][j+q]=-1*arrA[i+p][j+q]+1;
					}
				}
				cnt++;
			}
		}
		
		if(!Arrays.deepEquals(arrA,arrB)) {
			cnt=-1;
		}
		System.out.println(cnt);
		
	}//end main
}//end class
