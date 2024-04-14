/* https://www.acmicpc.net/problem/16236
* 구우현
* 1. 처음에 상어보다 크기 작은 물고기 리스트에 담고 bfs 거리 계산하면서 리스트에 있는 것인지 확인함
*    -> bfs로 4방 탐색할 때 없는 경우 있음
*    -> bfs로 탐색하면서 조건에 맞는 경우만 리스트에 담는 것으로 해결
*    -> 이동은 크기가 같아도 이동 가능, 리스트에 담을 때는 크기가 같으면 안되고 작을 때만 가능)
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

class Shark {
	int r,c,size;
	public Shark(int r, int c, int size) {
		super();
		this.r = r;
		this.c = c;
		this.size = size;
	}
	@Override
	public String toString() {
		return "Shark [r=" + r + ", c=" + c + ", size=" + size + "]" +"\n";
	}
}
class Fish implements Comparable<Fish> {
	int r,c,size,d;

	public Fish(int r, int c, int size, int d) {
		super();
		this.r = r;
		this.c = c;
		this.size = size;
		this.d = d;
	}
	@Override
	public String toString() {
		return "Fish [r=" + r + ", c=" + c + ", size=" + size + ", d=" + d + "]" +"\n";
	}
	@Override
	public int compareTo(Fish o) {
		if(this.d == o.d) {
			if(this.r == o.r) 
				return this.c - o.c;
			else
				return this.r - o.r;
		}
		else
			return this.d - o.d;
	}
}
public class BOJ_16236 {
	static int n,m,map[][];
	static Shark shark;
	static List<Fish> fishes = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) 
					shark = new Shark(i,j,2);
				else if(map[i][j] != 0)
					fishes.add(new Fish(i,j,map[i][j],400));
			}
		}
		int time = 0;
		int eatCnt = 0;
		while(true) {
			//먹을 수 있는 물고기 찾기, 가장 가까운 물고기 찾기
			List<Fish> targetFish = new ArrayList<>();
			calDistance(targetFish);
			if(targetFish.size() == 0) break; //먹을 물고기 없으면 종료
			Collections.sort(targetFish);

			//물고기 먹고, 먹는 물고기 카운트
			//카운트가 자신의 크기와 같으면 1증가
			Fish eat = targetFish.get(0);
			fishes.remove(eat);
			map[eat.r][eat.c] = 0;
			eatCnt++;
			time+=eat.d;

			if(fishes.size() == 0) break;

			if(eatCnt == shark.size) {
				shark.size++;
				eatCnt = 0;
			}

			//상어위치 갱신
			map[shark.r][shark.c] = 0;
			shark.r = eat.r;
			shark.c = eat.c;
		}
		System.out.println(time);
	}
	private static void calDistance(List<Fish> targetFish) {
		int delr[] = {-1,1,0,0};
		int delc[] = {0,0,-1,1};
		int len[][] = new int[n][n];
		boolean visited[][] = new boolean[n][n];

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {shark.r,shark.c});
		visited[shark.r][shark.c] = true;

		while(!q.isEmpty()) {
			int[] cur = q.poll();

			for(int i=0;i<4;i++) {
				int nr = cur[0] + delr[i];
				int nc = cur[1] + delc[i];
				if(nr<0 || nr>=n || nc<0 || nc>=n || visited[nr][nc] || map[nr][nc]>shark.size) continue;

				if(map[nr][nc] != 0 && map[nr][nc] != shark.size) {
					Fish target = new Fish(nr,nc,map[nr][nc],len[cur[0]][cur[1]]+1);
					targetFish.add(target);
				}
				
				len[nr][nc] = len[cur[0]][cur[1]]+1;
				visited[nr][nc] = true;
				q.offer(new int[] {nr,nc});
			}
		}
	}
}
