import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_BOJ_1389_케빈베이컨의6단계법칙{
	static boolean[] isVisited;
	static int[][] matrix;
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		
		matrix = new int[N+1][N+1];
		isVisited = new boolean[N+1];
		for (int i = 0; i < M; i++) {
			split = br.readLine().split(" ");
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			matrix[x][y] = 1;
            matrix[y][x] = 1;
        }
		int min = Integer.MAX_VALUE;
		int idx = 0;
		for (int i = N; i >= 1; i--) {
//			int tmp = bfs(i);
			int tmp = bfs(i);
			if(tmp <= min) {
                min = tmp;
				idx = i;
			}
//			System.out.println(i + " : " + tmp);
//			if(min > tmp) {
//				idx = i;
//			}
		}
		System.out.println(idx);
	}
	
	static int bfs(int v) {
		Queue<Integer> queue = new ArrayDeque<>();
		int n = matrix.length - 1;
		
		int sum = 0;
		queue.add(v);
		boolean[] isVisited = new boolean[N+1];
        isVisited[v]=true;
		int[] check = new int[N+1];
		while(!queue.isEmpty()) {
			v = queue.poll();
			sum += check[v];
			for (int i = 1; i <= n; i++) {
				if(matrix[v][i] == 1 && !isVisited[i]) {
					queue.add(i);
					isVisited[i] = true;
					check[i] = check[v] + 1;
				}
			}
		}
		return sum;
	}
}
