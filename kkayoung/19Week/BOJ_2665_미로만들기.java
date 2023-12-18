// https://www.acmicpc.net/problem/2665
import java.io.*;
import java.util.*;

public class Main {

	static int BLACK = 0;
	static int WHITE = 1;
	static class Loc{
		int r, c, breakCnt; // row, col, 검->흰 바꾼 횟수
		Loc(int r, int c, int breakCnt) {
			this.r = r;
			this.c = c;
			this.breakCnt = breakCnt;
		}
	}
	static Queue<Loc> q;
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] room = new int[n][n];
		int[][] cnt = new int[n][n]; // (r,c)까지의 검은 방을 흰 방으로 바꾸는 최소 횟수
		q = new ArrayDeque<>();

		// input
		for(int r=0;r<n;r++){
			Arrays.fill(cnt[r], Integer.MAX_VALUE); // cnt 배열에는 최솟값이 저장되어야 하므로 Integer.MAX_VALUE로 초기화
			String line = br.readLine();
			for(int c=0;c<n;c++){
				 room[r][c] = line.charAt(c)-'0';
			}
		}

		q.offer(new Loc(0,0,0));
		cnt[0][0] = 0;

		while(!q.isEmpty()){
			Loc now = q.poll();
			int r = now.r;
			int c = now.c;
			int bc = now.breakCnt;

			for(int d=0;d<4;d++) {
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				if(0<=nr && nr<n && 0<=nc && nc<n) {
					if(room[nr][nc]==WHITE) { // 다음 방이 흰색
						if(cnt[r][c]<cnt[nr][nc]) { // (r,c)에서 (nr,nc)로 갈 때 방 변경 횟수가 기존 방 변경 횟수가 더 적다면 cnt 갱신
							cnt[nr][nc] = cnt[r][c];
							q.offer(new Loc(nr,nc,bc));
						}
					} else { // black
						if(cnt[r][c]+1<cnt[nr][nc]) { // 검은 방을 흰 방으로 바꾸고 이동했을 때 변경횟수가 기존보다 적다면
							cnt[nr][nc] = cnt[r][c]+1; // 다음 방 변경횟수 = 현재 방 변경 횟수 + 1
							q.offer(new Loc(nr,nc, bc+1));
						}
					}
				}
			}
		}
		
		int answer = cnt[n-1][n-1];
		// output
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}
}
