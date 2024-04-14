import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
class Point {
	int r, c;

	public Point(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Point [r=" + r + ", c=" + c + "]";
	}

}
public class BOJ_18405 {
	public static Map<Integer, List<Point>> map;
	public static boolean[][] visited;
	public static int N, arr[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		map = new HashMap<>();

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]!=0) {
					if(map.get(arr[i][j])==null) {
						map.put(arr[i][j], new ArrayList<>());
						map.get(arr[i][j]).add(new Point(i,j));	
					}else {
						map.get(arr[i][j]).add(new Point(i,j));
					}
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());

		for(int i=0;i<S;i++) {
			visited = new boolean[N][N];
			//map은 key를 기본적으로 오름차순 정렬
			for(Integer key : map.keySet()) 
				bfs(key);			
		}
		System.out.println(arr[X-1][Y-1]);
	}

	private static void bfs(Integer key) {
		List<Point> point = map.get(key);
		List<Point> newPoint = new ArrayList<>();
		
		int delr[] = {-1,1,0,0};
		int delc[] = {0,0,-1,1};

		for(int p=0;p<point.size();p++) {
			Point cur = point.get(p);
			visited[cur.r][cur.c] = true;

			for(int i=0;i<4;i++) {
				int nr = cur.r+delr[i];
				int nc = cur.c+delc[i];

				if(nr<0 || nr>=N || nc<0 || nc>=N || arr[nr][nc]!= 0 || visited[nr][nc]) continue;

				arr[nr][nc] = key;
				newPoint.add(new Point(nr,nc));
				visited[nr][nc] = true;
			}
		}
		
		map.put(key, newPoint);
	}
}
