package Week14;/*
* 구현
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
class Ball2 {
	int r,c,m,s,d;

	public Ball2(int r, int c, int m, int s, int d) {
		super();
		this.r = r;
		this.c = c;
		this.m = m;
		this.s = s;
		this.d = d;
	}
	public Ball2(Ball2 ball) {
		super();
		this.r = ball.r;
		this.c = ball.c;
		this.m = ball.m;
		this.s = ball.s;
		this.d = ball.d;
	}
	@Override
	public String toString() {
		return "Ball [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]" +"\n";
	}
}
public class BOJ_20056_마법사상어와파이어볼 {
	static int n,m,k,result,cntBall[][];
	static int delr[] = {-1,-1,0,1,1,1,0,-1}, delc[] = {0,1,1,1,0,-1,-1,-1};
	static List<Ball2> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		cntBall = new int[n][n];

		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new Ball2(r,c,v,s,d));
		}
		for(int i=0;i<k;i++) {
			cntBall = new int[n][n];
			move();
			divide();
		}
		for(int i=0;i<list.size();i++) 
			result += list.get(i).m;

		System.out.println(result);
	}
	private static void move() {
		for(int i=0;i<list.size();i++) {
			Ball2 ball = list.get(i);

			int nr = (ball.r + n + delr[ball.d] * (ball.s%n)) % n;
			int nc = (ball.c + n + delc[ball.d] * (ball.s%n)) % n;
			ball.r = nr;
			ball.c = nc;		

			//list.add(new Ball(ball.r,ball.c,ball.m,ball.s,ball.d));
			cntBall[ball.r][ball.c]++;
		}
		//System.out.println(q);
	}
	private static void divide() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(cntBall[i][j] >= 2) {
					//해당 칸에 있는 볼 리스트 찾기
					//볼 리스트의 질량, 속력, 방향 구하기
					findBall(i,j);
				}
			}
		}

	}
	private static void findBall(int r, int c) {
		List<Ball2> tmp = new ArrayList<>();

		for(int i=list.size()-1;i>=0;i--) {
			if(list.get(i).r == r && list.get(i).c == c) {
				tmp.add(new Ball2(list.get(i)));
				list.remove(i);
			}
		}
		int v = 0, s = 0, d = 1;
		int even = 0, odd = 0;
		for(int i=0;i<tmp.size();i++) {
			v+=tmp.get(i).m;
			s+=tmp.get(i).s;
			if(tmp.get(i).d %2 == 0) 
				even++;
			else
				odd++;
		}
		v/=5;
		if(v == 0) return;

		s/=tmp.size();
		if(even == tmp.size() || odd == tmp.size()) d = 0;

		for(int i=0;i<4;i++)
			list.add(new Ball2(r,c,v,s,d+i*2));
	}
}
