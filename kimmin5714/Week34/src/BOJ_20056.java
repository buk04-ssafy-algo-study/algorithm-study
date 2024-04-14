import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Ball {
	int r,c,m,s,d;

	@Override
	public String toString() {
		return "Ball [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]" +"\n";
	}

	public Ball(int r, int c, int m, int s, int d) {
		super();
		this.r = r;
		this.c = c;
		this.m = m;
		this.s = s;
		this.d = d;
	}
}
public class BOJ_20056 {
	private static List<Ball> ballList;
	private static int arr[][], N;
	private static int delr[] = {-1,-1,0,1,1,1,0,-1};
	private static int delc[] = {0,1,1,1,0,-1,-1,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		ballList = new ArrayList<>();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			ballList.add(new Ball(r,c,m,s,d));
		}

		for(int i=0;i<K;i++) {
			arr = new int[N+1][N+1];
			moveBall();
			for(int a=1;a<=N;a++) {
				for(int b=1;b<=N;b++) {
					if(arr[a][b]>=2) 
						createNewBall(a,b);					
				}
			}
		}

		int result = 0;
		for(int i=0;i<ballList.size();i++) 
			result += ballList.get(i).m;
		System.out.println(result);
	}
	private static void moveBall() {
		for(int i=0;i<ballList.size();i++) {
			Ball ball = ballList.get(i);

			int nr = ((ball.r - 1 + delr[ball.d] * ball.s) % N + N) % N + 1;
			int nc = ((ball.c - 1 + delc[ball.d] * ball.s) % N + N) % N + 1;

			ball.r = nr;
			ball.c = nc;
			arr[nr][nc]++;
		}		
	}
	private static void createNewBall(int nr, int nc) {
		int nm = 0, ns = 0;
		int cnt = 0, cntOdd = 0, cntEven=0; 

		for(int i=ballList.size()-1;i>=0;i--) {
			Ball ball = ballList.get(i);
			if(ball.r == nr && ball.c == nc) {
				cnt++;
				nm+=ball.m;
				ns+=ball.s;
				if(ball.d%2 == 0) cntEven++;
				else 
					cntOdd++;
				ballList.remove(i);
			}
		}
		if(nm/5 == 0) return;
		
		if(cntEven == cnt || cntOdd == cnt) {
			for(int i=0;i<=6;i+=2) 
				ballList.add(new Ball(nr,nc,nm/5,ns/cnt,i));
		}
		else {
			for(int i=1;i<=7;i+=2) 
				ballList.add(new Ball(nr,nc,nm/5,ns/cnt,i));
		}

		return;
	}
}
