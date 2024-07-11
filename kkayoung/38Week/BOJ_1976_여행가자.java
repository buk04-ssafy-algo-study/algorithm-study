// https://www.acmicpc.net/problem/1976
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int MAX = 201;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] dist = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				if(Integer.parseInt(st.nextToken())==1) {
					dist[i][j] = 1;
				} else {
					dist[i][j] = MAX;
				}
			}
		}

		// floyd warshall
		for(int k=1;k<=N;k++) {
			for(int i=1;i<=N;i++) {
				dist[i][i] = 0;
				for(int j=1;j<=N;j++) {
					if(k==i || k==j || i==j) continue;
					if(dist[i][k]+dist[k][j]<dist[i][j]) {
						dist[i][j] = dist[i][k]+dist[k][j];
					}
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int[] route = new int[M];
		for(int i=0;i<M;i++) {
			route[i] = Integer.parseInt(st.nextToken());
		}
		String answer = "YES";
		for(int i=0;i<M-1;i++) {
			int src = route[i];
			int dst = route[i+1];
			if(dist[src][dst]>=MAX) {
				answer = "NO";
				break;
			}
		}
		System.out.println(answer);
	}

}
