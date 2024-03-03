// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB&categoryId=AV7GLXqKAWYDFAXB&categoryType=CODE&problemTitle=&orderBy=INQUERY_COUNT&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=4
import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] farm = new int[N][N];
			for(int r=0;r<N;r++) {
				String line = br.readLine();
				for(int c=0;c<N;c++) {
					farm[r][c] = line.charAt(c)-'0';
				}
			}
			sb.append(String.format("#%d %d\n", tc, getValue(farm, N)));
		}
		// output
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int getValue(int[][] farm, int N) {
		int result = 0;
		int start = N/2;
		for(int r=0;r<N;r++) {
			if(r<=N/2){
				for(int cnt=0;cnt<2*r+1;cnt++) {
					result += farm[r][start+cnt];
				}
			} else {
				for(int cnt=0;cnt<2*(N-r-1)+1;cnt++) {
					result += farm[r][start+cnt];
				}
			}
			if(r<N/2) {
				start --;
			} else {
				start ++;
			}
		}
		return result;
	}
}
