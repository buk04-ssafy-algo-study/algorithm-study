package Week61;

import java.io.*;
import java.util.*;

class Ladder {
	
	int d, barLeft, barRight;
	
	public Ladder(int d, int barLeft, int barRight) {
		this.d = d;
		this.barLeft = barLeft;
		this.barRight = barRight;
	}
}

public class 백준_2529_사다리 {

	static Ladder[] map;
	static int N, L, curFloor, minTime;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new Ladder[N+1];
		curFloor = 1;
		minTime = -1;
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int barLength = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int barLeft = (d == 0) ? 0 : L-barLength;
			int barRight = (d == 0) ? barLength : L;
			
			map[i] = new Ladder(d, barLeft, barRight);
			
		}
		
		while(true) {
			
			minTime++;
			moveFloor();
			moveBar();
			if(curFloor == N) break;
			
		} // while
		
		System.out.println(minTime);
	}
	
	static void moveFloor() {
		
		while(true) {
		
		if(curFloor == N) return;
		int cfbl = map[curFloor].barLeft;
		int cfbr = map[curFloor].barRight;
		int nfbl = map[curFloor+1].barLeft;
		int nfbr = map[curFloor+1].barRight;
		
		// 철수가 포함된 막대의 왼쪽과 오른쪽이 다음 층 막대 범위안에 포함된다면
		 if ((nfbl <= cfbl && cfbl <= nfbr) || (nfbl <= cfbr && cfbr <= nfbr)) {
             curFloor++;
         } else if (cfbl >= nfbl && cfbr <= nfbr) {
             curFloor++;
         } else if (cfbl < nfbl && cfbr > nfbr) {
             curFloor++;
         } else {
             return;  // 더 이상 이동할 수 없으면 종료
          }
		}
	}
	
	static void moveBar() {
		
		for(int i=1; i<=N; i++) {
			
			Ladder curFloor = map[i];
			
			if(curFloor.d == 0) {
				curFloor.barLeft++;
				curFloor.barRight++;
				if(curFloor.barRight == L) curFloor.d = 1;
			}
			else {
				curFloor.barLeft--;
				curFloor.barRight--;
				if(curFloor.barRight == 0) curFloor.d = 0;
			}
		}
 
	}
}
