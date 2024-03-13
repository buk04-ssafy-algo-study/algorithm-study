/* https://www.acmicpc.net/problem/19238
* 1. 시간 초과 
* - 택시 이동 할때마다 사람 리스트만큼 bfs 돌려서 시간 초과
* - 어짜피 bfs 는 완탐이므로 bfs 한 번만 돌려서 계산한 값 클래스에 저장
* 2. 태울 승객이나 목적지가 없는 경우 고려 : flag 이용
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Position implements Comparable<Position>{
	int r, c, d;
	int num;

	public Position(int r, int c, int d, int num) {
		super();
		this.r = r;
		this.c = c;
		this.d = d;
		this.num = num;
	}

	@Override
	public String toString() {
		return "Position [r=" + r + ", c=" + c + ", d=" + d + ", num=" + num + "]"+"\n";
	}

	@Override
	public int compareTo(Position o) {
		if(this.d==o.d) {
			if(this.r == o.r) 
				return this.c-o.c;
			else 
				return this.r-o.r;
		}
		return this.d-o.d;
	}
}
public class BOJ_19238 {
	static int n, m, k, map[][], result;
	static List<Position> human, destination;
	static boolean visited[][], flag;
	static int[] taxi = new int[2], delx = {-1,1,0,0}, dely = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];

		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		human = new ArrayList<>();
		destination = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		taxi[0] = Integer.parseInt(st.nextToken());
		taxi[1] = Integer.parseInt(st.nextToken());

		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int h1 = Integer.parseInt(st.nextToken());
			int h2 = Integer.parseInt(st.nextToken());
			int d1 = Integer.parseInt(st.nextToken());
			int d2 = Integer.parseInt(st.nextToken());

			Position h = new Position(h1,h2,99,i);
			Position d = new Position(d1,d2,99,i);

			human.add(h);
			destination.add(d);            
		}
		while(!human.isEmpty()) {
			goHuman(taxi, human); //택시랑 가장 가까운 승객 구하기
			if(!flag) {
				result = -1;
				break;
			}
			Position nearHuman = human.get(0);

			//가까운 승객한테 이동
			if(k-nearHuman.d >= 0)
				k -= nearHuman.d;
			else {
				result = -1;
				break;
			}

			//승객의 목적지로 이동
			Position target = destination.get(nearHuman.num);
			goDes(new int[] {nearHuman.r, nearHuman.c}, target);
			if(!flag) {
				result = -1;
				break;
			}
			if(k-target.d >= 0)
				k = k - target.d + target.d * 2;
			else {
				result = -1;
				break;
			}

			taxi[0] = target.r;
			taxi[1] = target.c;

			human.remove(nearHuman);
			result = k;
		}
		System.out.println(result);
	}
	private static void goHuman(int[] start, List<Position> target) {
		flag = false;
		int len[][] = new int[n+1][n+1];
		visited = new boolean[n+1][n+1];

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		visited[start[0]][start[1]] = true;

		while(!q.isEmpty()) {            
			int[] cur = q.poll();
			for(int i=0;i<target.size();i++) {
				if(target.get(i).r == cur[0] && target.get(i).c == cur[1]) {
					target.get(i).d = len[cur[0]][cur[1]];
					flag = true;
				}
			}

			for(int i=0;i<4;i++) {
				int nr = cur[0] + delx[i];
				int nc = cur[1] + dely[i];

				if(nr<1 || nr>n || nc<1 || nc>n || visited[nr][nc] ||map[nr][nc] == 1) continue;

				visited[nr][nc] = true;
				len[nr][nc] = len[cur[0]][cur[1]] +1;
				q.offer(new int[]{nr,nc});
			}
		}
		Collections.sort(target);
	}
	private static void goDes(int[] start, Position Des) {
		flag = false;
		int len[][] = new int[n+1][n+1];
		visited = new boolean[n+1][n+1];

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		visited[start[0]][start[1]] = true;

		while(!q.isEmpty()) {            
			int[] cur = q.poll();

			if(Des.r == cur[0] && Des.c == cur[1]) {
				Des.d = len[cur[0]][cur[1]];
				flag = true;
				return;
			}

			for(int i=0;i<4;i++) {
				int nr = cur[0] + delx[i];
				int nc = cur[1] + dely[i];

				if(nr<1 || nr>n || nc<1 || nc>n || visited[nr][nc] ||map[nr][nc] == 1) continue;

				visited[nr][nc] = true;
				len[nr][nc] = len[cur[0]][cur[1]] +1;
				q.offer(new int[]{nr,nc});
			}
		}
	}
}
