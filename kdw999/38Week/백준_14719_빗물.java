import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int H = Integer.parseInt(st.nextToken());
    	int W = Integer.parseInt(st.nextToken());
    	
    	int[] block = new int[W];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<W; i++) block[i] = Integer.parseInt(st.nextToken());
    	
    	int rain = 0;
    	
    	for(int i=1; i<W-1; i++) {
    		
    		int leftMaxHeight = 0;
    		int rightMaxHeight = 0;
    		
    		for(int j=0; j<i; j++) {
    			leftMaxHeight = Math.max(block[j], leftMaxHeight);
    		}
    		
    		for(int j=i+1; j<W; j++) {
    			rightMaxHeight = Math.max(block[j], rightMaxHeight);
    		}
    		
    		if(block[i] < leftMaxHeight && block[i] < rightMaxHeight) rain += Math.min(leftMaxHeight, rightMaxHeight) - block[i];
    	}
    	System.out.println(rain);
    	}
    }
   