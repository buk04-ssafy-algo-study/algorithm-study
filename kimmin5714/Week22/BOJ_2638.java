import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Position {
	int r;
	int c;

	Position(int x, int y) {
		this.r = x;
		this.c = y;
	}
}
public class BOJ_2638 {
	static int n,m,arr[][],cnt,res;
	static int dr[]= {-1,1,0,0}, dc[]= {0,0,-1,1};
	static boolean visited[][];
	static List<Position> cheese;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];

		int res = 0;
		cheese = new ArrayList<Position>();

		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) cheese.add(new Position(i,j));
			}
		}
		cnt = cheese.size();
		res = 0;

		//치즈 녹는 거 반복
		while(cnt>0) {
			res++;
			visited = new boolean[n][m];

			//외부 공기 구분
			outCheck();
			melting();//cnt가 0일 때도 돌아야하는데 안돌아갔음
		}
		System.out.println(res);
	}
	private static void melting() {
		for(int k=cheese.size()-1;k>=0;k--) { //모든 치즈 검사
			int outCnt=0;
			int r = cheese.get(k).r;
			int c = cheese.get(k).c;

			for(int i=0;i<4;i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];

				if(arr[nr][nc] == 2) outCnt++;
			}
			if(outCnt>=2) {
				arr[r][c] = 0; //치즈 녹음
				cheese.remove(k); //치즈 목록에서 삭제
				cnt--;
			}
		}
	}
	private static void outCheck() {
		Queue<Position> q = new ArrayDeque<>();
		q.offer(new Position(0,0));
		visited[0][0] = true;
		arr[0][0] = 2;

		while(!q.isEmpty()) {
			Position tmp = q.poll();
			int r = tmp.r;
			int c = tmp.c;

			for(int i=0;i<4;i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];

				if(nr<0 || nr>=n || nc<0 || nc>=m 
						|| visited[nr][nc] || arr[nr][nc]==1) continue; //arr[nr][nc]!=0 일 때 continue하면 무한루프..

				arr[nr][nc] = 2;
				q.offer(new Position(nr,nc));
				visited[nr][nc] = true; //방문 체크
			}
		}
	}

}
