package Week13;

import java.util.*;
import java.io.*;

public class 프로그래머스_미로탈출명령어 {
	
	static int[] dr = {1, 0, 0, -1};
	static int[] dc = {0, -1, 1, 0}; // 문자 순서 때문에 하좌우상 순으로
	static String[] dir = {"d", "l", "r", "u"};
	static int mapr, mapc;
	static int endr, endc;
	static String result = "";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 격자 행
		int m = Integer.parseInt(br.readLine()); // 격자 열
		int x = Integer.parseInt(br.readLine()); // 시작 지점 행
		int y = Integer.parseInt(br.readLine()); // 시작 지점 열
		int r = Integer.parseInt(br.readLine()); // 도착 지점 행
		int c = Integer.parseInt(br.readLine()); // 도착 지점 열
		int k = Integer.parseInt(br.readLine()); // 움직여야할 거리
		
		solution(n, m, x, y, r, c, k);
	}
	
	public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        mapr = n;
        mapc = m;
        endr = r;
        endc = c;
        int diff = Math.abs(r-x) + Math.abs(c-y); // 현재지점에서 도착지점 가는데 필요한 칸
        dfs(x, y, k, "", diff);
        if(result.equals("")) answer = "impossible";
        else answer = result;
        return answer;
    }
	
	public static boolean dfs(int r, int c, int k, String str, int diff) {
		
		// k만큼 움직여서 딱 도착지점에 도착했다면
		if(k==0 && diff==0) {
			result = str;
			return true;
		}
		
		// 하좌우상 탐색
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			// 격자 벗어나는지 체크, 현재 남은 칸이 움직여야할 칸 보다 작아야한다
			if(nr >= 1 && nr <= mapr && nc >= 1 && nc <= mapc && diff <= k ) {
				// 현재 남은 칸이랑 움직일 수 있는 칸이 짝수, 홀수로 일치해야 지점 딱 도착 가능
				if((diff % 2 == 0 && k % 2 ==0) || (diff % 2 == 1 && k % 2 == 1)) {
					// 다음 칸 이동, k감소, 남은 거리 감소
					if(dfs(nr, nc, k-1, str+dir[i], Math.abs(nr-endr) + Math.abs(nc-endc))) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
