package probs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ14891 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=0, i=0,j=0, k=0, tmp=0, num=0, score=0;
		int[][] wheel = new int[4][8];
		int[] flag = new int[4]; //for store direction info
		String state;
		String[] in;
		
		for(i=0;i<4;i++) {
			state = br.readLine();
			for(j=0;j<8;j++) {
				wheel[i][j] = state.charAt(j)-48;
			}
		}
		
		n = Integer.parseInt(br.readLine());
	
		for(i=0; i<n;i++) {	
			in = br.readLine().split(" ");
			flag = new int[4];
			num = Integer.parseInt(in[0])-1;
			flag[num] = Integer.parseInt(in[1]);
			j=num-1;
			while(j>=0) {
				if(wheel[j+1][6]!=wheel[j][2]) {
					flag[j]=flag[j+1]*-1;
				}
				j--;
			}
			j=num+1;
			while(j<4) {
				if(wheel[j-1][2]!=wheel[j][6]) {
					flag[j]=flag[j-1]*-1;
				}
				j++;
			}			

			for(j=0;j<4;j++) {
				if(flag[j]==1){
					tmp = wheel[j][7];
					for(k=6; k>=0;k--) {
						wheel[j][k+1] = wheel[j][k];
					}
					wheel[j][0] = tmp;
					continue;
					
				} else if(flag[j]==-1) {
					tmp = wheel[j][0];
					for(k=1; k<8;k++) {
						wheel[j][k-1] = wheel[j][k];
					}
					wheel[j][7] = tmp;
				}
				
			}	
				
		} //end rotate cmd
		
		for(i=0;i<4;i++) {
			if(wheel[i][0]==1) {
				score+=(1<<i);
			}
		}
		
		
		System.out.println(score);
	}
}
