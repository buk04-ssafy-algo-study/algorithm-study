// https://www.acmicpc.net/problem/9251
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] first = br.readLine().toCharArray();
    		char[] second = br.readLine().toCharArray();
    		int firstLen = first.length;
    		int secondLen = second.length;
    
    		int[][] dp = new int[secondLen][firstLen];
    		
    		for(int i=0;i<firstLen;i++) {
    			if(second[0]==first[i]) dp[0][i] = 1;
    			else if(i>0) dp[0][i] = dp[0][i-1];
    		}
    
    		for(int i=1;i<secondLen;i++) {
    			if(first[0]==second[i]) dp[i][0] = 1;
    			else dp[i][0] = dp[i-1][0];
    		}
    
    		for(int i=1;i<secondLen;i++) {
    			char secondChar = second[i];
    			for(int j=1;j<firstLen;j++) {
    				char firstChar = first[j];
    				
    				if(firstChar == secondChar) {
    					dp[i][j] = Math.max(dp[i-1][j-1]+1, dp[i][j-1]);
    				} else {
    					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
    				}
    			}
    		}

		    bw.write(String.valueOf(dp[secondLen-1][firstLen-1]));
        bw.flush();
        bw.close();
    }
}
