// https://www.acmicpc.net/problem/1027
import java.io.*;
import java.util.*;

public class Main {	

	static Stack<Double> st = new Stack<>(); // 기울기를 저장할 배열
	static int[] heights;
	static int N;
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int answer = Integer.MIN_VALUE;

		// input
		N = Integer.parseInt(br.readLine());
		heights = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}

		// count
		for(int idx=0;idx<N;idx++){
			int count = 0;
			if(idx>0) count += cntLeft(idx);
			if(idx<N-1) count += cntRight(idx);
			answer = Math.max(answer, count);
		}

		// output
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}	

	static int cntLeft(int idx) { // 왼쪽 고층건물들 중 볼 수 있는 건물 개수 리턴
		int res = 1;
		
		for(int i=idx-1;i>=0;i--) {
			if(st.size()==0) {
				st.push(calSlope(idx, i));
				continue;
			}
			double slope = calSlope(idx, i);
			if(st.peek()>slope) { // 스택 top에 저장된 기울기가 더 커야 현재 건물을 볼 수 있음
				res++;
				st.push(slope);
			} else {
				continue;
			}
		}
		emptyStack();

		return res;
	}

	static int cntRight(int idx) { // 오른쪽
		int res = 1;
		
		for(int i=idx+1;i<N;i++) {
			if(st.size()==0) {
				st.push(calSlope(idx, i));
				continue;
			}
			double slope = calSlope(idx, i);
			if(st.peek()<slope) { // 스택 top에 저장된 기울기가 작아야 현재 건물을 볼 수 있음
				res++;
				st.push(slope);
			} else {
				continue;
			}
		}
		emptyStack();
		
		return res;
	}

	static double calSlope(int src, int dst) {
		int xdiff = dst-src;
		int ydiff = heights[dst]-heights[src];
		return (1.0*ydiff)/xdiff;
	}

	static void emptyStack() {
		while(!st.isEmpty()) st.pop();
	}
}
