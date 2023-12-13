package Week17;

import java.io.*;
import java.util.*;

public class 백준_13458_시험감독 {

	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testRoom = Integer.parseInt(br.readLine());
		int[] people = new int[testRoom];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<people.length;i ++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		int mainDir = Integer.parseInt(st.nextToken());
		int subDir = Integer.parseInt(st.nextToken());
		
		long minDir = testRoom;
		
		for(int i=0; i<people.length; i++) {
			
			int rest = people[i] - mainDir;
			int needSubDir = rest / subDir;
			if(rest%subDir > 0) needSubDir++;
			if(needSubDir < 0 ) needSubDir = 0; 
			
			minDir += needSubDir;
		}
		
		System.out.println(minDir);
	}
}
