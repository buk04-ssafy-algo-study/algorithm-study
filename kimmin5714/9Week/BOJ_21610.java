/*
https://www.acmicpc.net/problem/21610
* 구름이 움직일 때, 범위를 벗어나면 다시 되돌아와야 함
  -> 범위 안에 들어올 때까지 반복
  -> 1보다 작으면 +n
  -> n보다 크면 -n
* 구름 빼고 바구니 물의 수를 더하기 위해 방문 배열 사용 
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Cloud {
	int x;
	int y;

	public Cloud(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {

	static int n, m, d, s;
	static int[][] map;
	static List<Integer>[] list;
	static int[] dx = {0,0, -1, -1, -1,0,1,1,1}, dy = {0,-1,-1,0,1,1,1,0,-1};
	static Queue<Cloud> clouds = new ArrayDeque<>();
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n + 1][n + 1];
		visited = new boolean[n+1][n+1];

		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		clouds.offer(new Cloud(n, 1));
		clouds.offer(new Cloud(n, 2));
		clouds.offer(new Cloud(n - 1, 1));
		clouds.offer(new Cloud(n - 1, 2));

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine()," ");
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());

			moveCloud(d, s);	
			//구름이 d[i]방향으로 s[i]칸 이동 
			//구름 칸 1씩 증가 

			crossCheck();
			//구름 칸 cross check
			//구름 칸마다 대각선을 확인해서 물이 있는 칸은 +1

			nextCloud();
			//구름 칸 제외 나머지 칸에서 2이상인 칸은 -2
		}
		int result = 0;
		for(int i=1;i<=n;i++) {
			for (int j = 1; j <= n; j++)
				result+=map[i][j];
		}
		System.out.println(result);
		//바구니 물의 합 출력		
	}
	private static void moveCloud(int d, int s) {

		//구름이 d[i]방향으로 s[i]칸 이동 
		//구름 칸 1씩 증가 
		for (Cloud cloud : clouds) {
			int nx = cloud.x + dx[d]*s;
			int ny = cloud.y + dy[d]*s;

			while(true) {
				if(nx>0 && nx<=n) break;
        
				if(nx<1) nx+=n;
				if(nx>n) nx-=n;
			}
			while(true) {
				if(ny>0 && ny<=n) break;
				
				if(ny<1) ny+=n;
				if(ny>n) ny-=n;
			}
			cloud.x = nx;
			cloud.y = ny;
			
			map[cloud.x][cloud.y]++;		
		}
	}	
	private static void crossCheck() {
		//구름 칸 cross check
		//구름 칸마다 대각선을 확인해서 물이 있는 칸은 +1
		int[] delx = {-1,-1,1,1}; 
		int[] dely = {-1,1,-1,1};


		while (!clouds.isEmpty()) {
			Cloud cloud = clouds.poll();
			int cnt = 0;
			visited[cloud.x][cloud.y] = true;

			for(int j=0;j<4;j++) {
				int nx = cloud.x+delx[j];
				int ny = cloud.y+dely[j];

				if(nx<1 || nx>n || ny<1||ny>n||map[nx][ny] == 0)continue;

				cnt++;
			}
			map[cloud.x][cloud.y] += cnt;
		}
	}
	private static void nextCloud() {
		for(int i=1;i<=n;i++) {
			for (int j = 1; j <= n; j++) {
				if(!visited[i][j] && map[i][j] >= 2) {
					clouds.offer(new Cloud(i,j));
					map[i][j]-=2;
				}
			}				
		}
		visited = new boolean[n+1][n+1];
	}
}
