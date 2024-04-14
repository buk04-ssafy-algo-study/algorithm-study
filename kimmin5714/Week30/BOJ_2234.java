package Week30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int r,c;
	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Point{" +
			"r=" + r +
			", c=" + c +
			'}';
	}
}
public class BOJ_2234 {
	static int n,m,arr[][],roomNum[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[m][n];
		for(int i=0;i<m;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		//방의 개수
		roomNum = new int[m][n];
		int num = 1;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(roomNum[i][j] == 0){
					bfs(i,j,num);
					num++;
				}
			}
		}

		//방의 넓이
		//벽 제거하여 얻을 수 있는 가장 넓은 방 크기
	}

	private static void bfs(int r, int c, int num) {
		int[] delr = {-1,1,0,0};
		int[] delc = {0,0,-1,1};

		Queue<Point> q = new ArrayDeque<Point>();
		q.offer(new Point(r,c));

		while(!q.isEmpty()){
			Point cur = q.poll();
			roomNum[cur.r][cur.c] = num;

			for(int i=0;i<4;i++){
				int nr = cur.r+delr[i];
				int nc = cur.c+delc[i];

				if(nr<0 || nr>=m || nc<0 || nc>=n || roomNum[nr][nc]!=0) continue;
				//서 북 동 남 1 2 4 8 더한 값


				q.offer(new Point(nr,nc));
			}
		}
	}
}
