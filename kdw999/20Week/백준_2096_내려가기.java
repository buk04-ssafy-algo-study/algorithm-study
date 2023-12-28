package Week20;

import java.util.*;
import java.io.*;

public class 백준_2096_내려가기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][3];
		int[][] min = new int[N][3];
		int[][] max = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
        for(int i=0; i<N; i++) {
			for(int j=0; j<3; j++) {
				
				// 첫 줄 최소, 최대 지정
				if(i==0){
					min[i][j] = arr[i][j];
					max[i][j] = arr[i][j];
				}
				
				// 첫 번째 수에서 출발 
				else if(j==0) {
					
				min[i][j] = Math.min(arr[i][j]+min[i-1][j], arr[i][j]+min[i-1][j+1]);
				max[i][j] = Math.max(arr[i][j]+max[i-1][j], arr[i][j]+max[i-1][j+1]);
				
				}
				
				// 세 번째 수에서 출발
				else if(j==2) {
					
				min[i][j] = Math.min(arr[i][j]+min[i-1][j], arr[i][j]+min[i-1][j-1]);
				max[i][j] = Math.max(arr[i][j]+max[i-1][j], arr[i][j]+max[i-1][j-1]);
						
				}
				
				// 두 번째 수에서 출발
				else {
				
				min[i][j] = Math.min(arr[i][j]+min[i-1][j-1], Math.min(arr[i][j]+min[i-1][j] ,arr[i][j]+min[i-1][j+1]));
				max[i][j] = Math.max(arr[i][j]+max[i-1][j-1], Math.max(arr[i][j]+max[i-1][j] ,arr[i][j]+max[i-1][j+1]));
							
				  }
			}
		}
		
        int maxNum = Math.max(max[N-1][0], Math.max(max[N-1][1], max[N-1][2]));
        int minNum = Math.min(min[N-1][0], Math.min(min[N-1][1], min[N-1][2]));
        
        System.out.println(maxNum+ " "+ minNum);
	}
}
