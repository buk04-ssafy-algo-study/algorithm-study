package Week61;

import java.io.*;
import java.util.*;

class Ladder {
	
	int floor, barLength, d, barLeft, barRight;
	
	public Ladder(int floor, int barLength, int d, int barLeft, int barRight) {
		this.floor = floor;
		this.barLength = barLength;
		this.d = d;
		this.barLeft = barLeft;
		this.barRight = barRight;
	}
}

public class 백준_2528_사다리2 {

	static Ladder[] map;
	static int N, L, curFloor, minTime;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new Ladder[N+1];
		curFloor = 1;
		minTime = 0;
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int floor = i;
			int barLength = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int barLeft = (d == 0) ? 0 : L-barLength;
			int barRight = (d == 0) ? barLength : L;
			
			map[i] = new Ladder(i, barLength, d, barLeft, barRight);
			
		}
		
		while(true) {
			
			if(curFloor == N) break;
				
				int curFloorBarMaxDist = L-map[curFloor].barLength;
				int nextFloorBarMaxDist = L-map[curFloor+1].barLength;
				
				int[] curPoint = moveBar(minTime, curFloor, 
						curFloorBarMaxDist);
				
				int[] nextPoint = moveBar(minTime, curFloor+1, 
						nextFloorBarMaxDist);
				
				if(moveFloor(curPoint[0], curPoint[1], nextPoint[0], nextPoint[1])) continue;
				minTime++;
				
			
		} // while
		
		System.out.println(minTime);
	}
	
	static boolean moveFloor(int cfbl, int cfbr, int nfbl, int nfbr) {
		
		// 철수가 포함된 막대의 왼쪽과 오른쪽이 다음 층 막대 범위안에 포함된다면
		if ((nfbl <= cfbl && cfbl <= nfbr) || (nfbl <= cfbr && cfbr <= nfbr) ||
            (cfbl <= nfbl && nfbr <= cfbr)) {
            curFloor++;
            return true;
        }
		else {
			return false;
		}
	}
	
	static int[] moveBar(int distance, int curf, int cfmd) {
		
		int curFloorLeftPoint = map[curf].barLeft;
	    int curFloorRightPoint = map[curf].barRight;

	    if (cfmd == 0) {
	        return new int[]{curFloorLeftPoint, curFloorRightPoint};
	    }
		
			// (움직인 횟수 / 최대 이동거리) = 짝, 홀수에 따라 d 방향에 따라 좌표 증감
			int rest = distance % cfmd; // 최대거리 움직이고 남은 거리
			
			// 좌 -> 우
			if(map[curf].d == 0) {
				
				if((distance / cfmd) % 2 == 0) {
					curFloorLeftPoint = map[curf].barLeft + rest;
					curFloorRightPoint = map[curf].barRight + rest;
				}
				else {
					curFloorLeftPoint = (L - map[curf].barLength) - rest;
					curFloorRightPoint = L - rest;
				}
			}
			
			// 우 -> 좌
			else {
				
				if((distance / cfmd) % 2 == 0) {
					curFloorLeftPoint = map[curf].barLeft - rest;
					curFloorRightPoint = map[curf].barRight - rest;
				}
				else {
					curFloorLeftPoint = 0 + rest;
					curFloorRightPoint = map[curf].barLength + rest;
				}
			}
			return new int[] {curFloorLeftPoint, curFloorRightPoint};
	}
}