// https://www.acmicpc.net/problem/1461
import java.io.*;
import java.util.*;

public class Main {	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// input
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer> positive = new ArrayList<>(); // 양수
		List<Integer> negative = new ArrayList<>(); // 음수
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int n = Integer.parseInt(st.nextToken());
			if(n>0) positive.add(n);
			else negative.add(-n);
		}
		
		// 내림차순 정렬
		Collections.sort(positive, Collections.reverseOrder());
		Collections.sort(negative, Collections.reverseOrder());
		
		int p=0, n=0; // 양수 리스트 인덱스, 음수 리스트 인덱스
		int answer = 0;
		int psize = positive.size(); // 양수 리스트 크기
		int nsize = negative.size(); // 음수 리스트 크기
		while(p<psize || n<nsize) { // 원점에서 먼 곳부터 M개 지점 방문(왕복)
			if(p<psize) {			
				answer += positive.get(p)*2;
				p += M;
			}
			if(n<nsize) {
				answer += negative.get(n)*2;
				n += M;
			}
		}

		int pMax = 0;
		int nMax = 0;
		if(positive.size()>0) pMax = positive.get(0);
		if(negative.size()>0) nMax = negative.get(0);
		answer -= Math.max(pMax,nMax); // 원점에서 가장 먼 곳은 편도로 이동 (책을 모두 제자리에 놔둔 후에는 다시 0으로 돌아올 필요는 없다)
		// output
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}
}
