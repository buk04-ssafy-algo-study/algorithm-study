// 인접 행렬 만들어서 자기보다 무거운 수는 1로 초기화
// 다 만들면 자신의 행 방향은 자기 보다 큰 수만 1로 되있고 열 방향은 자기 보다 작은 수만 1로 되어있다.
// 1부터 N까지 행, 열 탐색하면서 상관관계가 몇 개 만들어지는 지 갯수 세기

package backjoon;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] matrix;
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		matrix = new int[N+1][N+1];
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			matrix[to][from] = 1;
		}
		
		for(int i=1; i<=N; i++) {
			
			int sum=0;
			int rcnt = row(i);
			int ccnt = column(i);
			sum = N-(rcnt+ccnt)-1; // 본인 포함한 걸 생각해야해서 -1
			sb.append(sum+"\n");
		}
		System.out.println(sb);
	} // main
	
	public static int row(int i) {
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(i); // 시작 인덱스 담기
		// 상관관계 방문 표시
		boolean[] visit = new boolean[N+1];
		
		visit[i] = true;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			
			int temp = q.poll();
			for(int t=1; t<=N; t++) {
				
				if(matrix[temp][t] == 1 && !visit[t]) {
					q.offer(t); // 연결된 부모 넣기
					visit[t] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static int column(int i) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(i); // 시작 인덱스 담기
        boolean[] visit = new boolean[N+1];
		
		visit[i] = true;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			
			int temp = q.poll();
			for(int t=1; t<=N; t++) {
				
				if(matrix[t][temp] == 1 && !visit[t]) {
					q.offer(t); // 연결된 부모 넣기
					visit[t] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}
}
