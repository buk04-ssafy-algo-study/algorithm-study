package Week66;

import java.io.*;
import java.util.*;

public class 백준_9082_지뢰찾기 {
	
	static int[] firstRow;
	static String[] secondRow;
	static int N;
	static BufferedReader br;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			
			init();
			int result = mine();
			
			sb.append(result+"\n");
		}
		System.out.println(sb);
 	} // main
	
	static int mine() {
		
		 int answer = 0;

	        for (int j = 0; j < N; j++) {
	            if (j == 0 && firstRow[j] != 0 && firstRow[j + 1] != 0) {
	                firstRow[j] -= 1;
	                firstRow[j + 1] -= 1;
	                answer++;
	            } else if (j == (N - 1) && firstRow[j - 1] != 0 && firstRow[j] != 0) {
	                firstRow[j - 1] -= 1;
	                firstRow[j] -= 1;
	                answer++;
	            } else if (1 <= j && j <= (N - 2) && firstRow[j - 1] != 0 && firstRow[j] != 0 && firstRow[j + 1] != 0) {
	                firstRow[j - 1] -= 1;
	                firstRow[j] -= 1;
	                firstRow[j + 1] -= 1;
	                answer++;
	            }
	        }

	        return answer;
	} // mine
	
	static void init() throws IOException {
		
		N = Integer.parseInt(br.readLine());
		firstRow = new int[N];
		secondRow = new String[N];
		
		String line = br.readLine();
		
		for(int i=0; i<line.length(); i++) {
			firstRow[i] = line.charAt(i) - '0';
		}
		
		line = br.readLine();
		for(int i=0; i<line.length(); i++) {
			secondRow[i] = String.valueOf(line.charAt(i));
		}
	} // init
}
