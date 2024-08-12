import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static List<Point> clouds = new ArrayList<>();
	
	static int[] dy = {0,-1,-1,-1,0,1,1,1};
	static int[] dx = {-1,-1,0,1,1,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//처음 구름
		clouds.add(new Point(0, N-2));
		clouds.add(new Point(1, N-2));
		clouds.add(new Point(0, N-1));
		clouds.add(new Point(1, N-1));
		
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			moveClouds(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			addWaters();
			magic();
			newClouds();
		}
		
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result += map[i][j];
			}
		}
			
		System.out.println(result);
		br.close();

	}

	private static void moveClouds(int dir, int cnt) {
		int cloudsSize = clouds.size();
		for (int i = 0; i < cloudsSize; i++) {
			clouds.get(i).x = (clouds.get(i).x + (dx[dir-1]*cnt)%N + N) % N;
			clouds.get(i).y = (clouds.get(i).y + (dy[dir-1]*cnt)%N + N) % N;
		}
	}
	
	private static void addWaters() {
		int cloudsSize = clouds.size();
		for (int i = 0; i < cloudsSize; i++) {
			map[clouds.get(i).y][clouds.get(i).x] ++;
		}
	}
	
	private static void magic() {
		int cloudsSize = clouds.size();
		for (int i = 0; i < cloudsSize; i++) {
			int y = clouds.get(i).y;
			int x = clouds.get(i).x;
			for (int j = 1; j < 8; j+=2) {
				int ny = y + dy[j];
				int nx = x + dx[j];
				
				if(ny>=0 && ny<N && nx>=0 && nx<N)
					if(map[ny][nx] > 0) map[y][x]++;
			}
		}
	}

	private static void newClouds() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if(clouds.contains(new Point(x, y))) {
					clouds.remove(new Point(x, y));
				} else {
					if(map[y][x] >= 2) {
						clouds.add(new Point(x, y));
						map[y][x] -= 2;
					}
				}
			}
		}
	}
}
