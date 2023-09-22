package probs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA2115 {

	private static StringBuilder sb = new StringBuilder();
	
	private static int[][] map;
	private static int[][] prof;
	private static int[] sel;
	private static int n=0,m=0,c=0,k=0;
	private static int i,j,len,max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tcase=0, t=0, sum=0, div=0;	
      
        String[] in;
        t = Integer.parseInt(br.readLine());
         
        for(tcase=1; tcase<=t; tcase++) {
        	 sb.append("#" + tcase + " ");   
        	 in =br.readLine().split(" ");
        	 n = Integer.parseInt(in[0]);
        	 m = Integer.parseInt(in[1]);
        	 c = Integer.parseInt(in[2]);
        	 k=(n-m+1)*n;
        	 div =(n-m+1); 
        	 map = new int[n][n];
        	 prof = new int[n][n];
        	
        	for(i=0; i<n; i++) {
        		in =br.readLine().split(" ");
        		for(j=0; j<n; j++) {
            		map[i][j] =Integer.parseInt(in[j]);
            	}
        	}
        	sel = new int[m+1]; //for combination
 	
        	for(i=0;i<n; i++) {
    			for(j=0; j<div; j++) {
					max=0;
					for(len=1;len<=m;len++) {
						combi(0,0);//possb? chk.
    				}
					prof[i][j]=max;
    			}
    		}

        	sum=0;        	
        	max=0;
    		for(i=0;i<k; i++) {
    			for(j=i+1; j<k; j++) {
    				if((i/div == j/div) && ((i%div+m)>=j%div)) {
    					continue;
    				}
    				sum=prof[i/div][i%div]+prof[j/div][j%div];
    				if(sum>max) {
    					max = sum;
    				}
    			}
    		}
        	
        	sb.append(max+"\n");
        }
        
        System.out.println(sb);
	}
	
	private static void combi(int cnt, int start) {
		if(cnt==len) {
			int s=0;
			int profit=0;
			for(int q=0;q<len;q++) {
				s+=map[i][j+sel[q]];
				profit+=map[i][j+sel[q]]*map[i][j+sel[q]];
			}
			if(s<=c && profit>max) {
				max=profit;
			}
			return;
		}
		for(int q=start; q<m; q++) {
			sel[cnt] = q;
			combi(cnt+1, q+1);
		}		
	}

		
}
