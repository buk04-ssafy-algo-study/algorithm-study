import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main_BOJ_트리의부모찾기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] trees = new int[N+1][N+1];
		for (int i = 0; i < N-1; i++) {
			String[] split = br.readLine().split(" ");
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			
			trees[x][y] = 1;
			trees[y][x] = 1;
		}
		
		int[] ans = bfs(N, trees);
		System.out.println(Arrays.toString(ans));
	}
	
	
	private static int[] bfs(int N, int[][] trees) {
		Queue<Integer> queue = new ArrayDeque<>();
		int v = 1;
		
		boolean[] isVisited = new boolean[N+1];
		int[] check = new int[N+1];
		queue.add(v);
		isVisited[v] = true;
		
		while(!queue.isEmpty()) {
			v = queue.poll();
			//방문 처리
			isVisited[v] = true;
			
			for (int i = 1; i <= N; i++) {
				if(!isVisited[i] && trees[v][i] == 1) {
					queue.add(trees[v][i]);
					check[i] = v;
				}
			}
		}
		return check;
	}
}
