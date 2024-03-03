// https://www.acmicpc.net/problem/1744
import java.io.*;
import java.util.*;

public class Main {

	static int N, zeroCnt;
	static PriorityQueue<Integer> positive, negative;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		positive = new PriorityQueue<>(Collections.reverseOrder());
		negative = new PriorityQueue<>();

		for(int i=0;i<N;i++) {
			int n = Integer.parseInt(br.readLine());
			if(n>0) positive.offer(n);
			else if(n<0) negative.offer(n);
			else zeroCnt++;
		}
			
		// output
		bw.write(String.valueOf(solution()));
		bw.flush();
		bw.close();
	}

	static int solution() {
    // 수열 길이가 1일 때
		if(N==1) return (positive.size()>0) ? positive.poll() : negative.poll();

		int result = 0;
    // 음수 * 음수
		while(negative.size()>1) {
			result += negative.poll() * negative.poll();
		}

    // 양수 * 양수
    // 단, n*1보다 n+1 값이 더 크다
		while(positive.size()>1) {
			int a = positive.poll();
			int b = positive.poll();
			if(a==1 || b==1) result += (a+b);
			else result += a * b;
		}
		
		if(negative.isEmpty()){ // 음수 없음
			if(!positive.isEmpty()) result += positive.poll();
		}
		else { // 음수 존재
			if(!positive.isEmpty()) {
				if(zeroCnt==0) { // 양수 있고 0 없음
					result += negative.poll();
					result += positive.poll();
				} else { // 양수 있고 0 있음
					result += positive.poll();
				}
			}
			else { // 양수 없음
				if(zeroCnt==0) result += negative.poll();
			}
		}
		return result;
	}	
}
