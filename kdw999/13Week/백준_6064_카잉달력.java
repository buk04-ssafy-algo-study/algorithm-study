package Week13;

import java.util.*;
import java.io.*;

public class 백준_6064_카잉달력 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테케
		
        for(int t=0; t<T; t++) {
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	int M = Integer.parseInt(st.nextToken());
        	int N = Integer.parseInt(st.nextToken());
        	int x = Integer.parseInt(st.nextToken())-1;
        	int y = Integer.parseInt(st.nextToken())-1;
        	
        	
        	int result = -1;
        	
        	for(int k=x; k<=N*M; k+=M) {
        		
        		if((k % N == y)) {
        			result = k+1;
        			break;
        		}
        	}
        	
        	sb.append(result+"\n");
        }
        System.out.println(sb);
	}
}
