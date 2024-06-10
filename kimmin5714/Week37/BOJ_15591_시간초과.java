package Week37;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15591_시간초과 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N+1][N+1];

		initArr(arr, N);

		for(int i=1;i<N;i++){
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			arr[p][q] = r;
			arr[q][p] = r;
		}

		saveUsado(arr,N);
		for(int i=0;i<Q;i++){
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			sb.append(findVideo(arr, k,v, N)+"\n");
		}
		System.out.println(sb);
	}

	private static int findVideo(int[][] arr, int k, int v, int N) {
		int cnt = 0;
		for(int i=1;i<=N;i++)
			if(arr[i][v]>=k) cnt++;
		return cnt;
	}

	private static void saveUsado(int[][] arr, int N) {
		for(int i=1;i<=N;i++){
			for(int j=1;j<=N;j++){
				if(arr[i][j] == 0){
					int min = findMin(arr, i, j, N);
					arr[i][j] = arr[j][i] = min;
				}
			}
		}
	}

	private static int findMin(int[][] arr, int i, int j, int N) {
		int min = Integer.MAX_VALUE;
		for(int r=1;r<=N;r++){
			if(arr[r][i] == 0 || arr[r][j] == 0) continue;
			min = Math.min(min, arr[r][i]);
			min = Math.min(min, arr[r][j]);
		}
		return min;
	}

	private static void initArr(int[][] arr, int N) {
		for(int i=0;i<=N;i++){
			for(int j=0;j<=N;j++)
				if(i==j || i==0 || j==0) arr[i][j] = -1;
		}
	}
}
