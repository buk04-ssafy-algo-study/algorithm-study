/*
https://www.acmicpc.net/problem/16234
* bfs에서 큐에 넣을 때 방문 체크 해주어야 함 -> 메모리 초과 때문
  -> 큐에서 뺄 때 방문처리하면 같은 곳을 두 번 큐에 담을 수도 있음
* flag 반복문에서 사용시 반복문 시작에서 초기화 해야함!
* 일부 추출해서 처리하는 경우, list 등의 자료구조 활용할 것
  -> 전체 순회하면서 처리하면 필요 없는 다른 값까지 바꿀 수 있음 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int N,L,R, A[][], day, sum;
	static int[] delx = {-1,1,0,0}; //상하좌우
	static int[] dely = {0,0,-1,1};
	static boolean moveFlag, visited[][];
	static List<int[]> list;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine()," ");

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		A = new int[N][N];

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");			
			for(int j=0;j<N;j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while(true) {

			moveFlag=false;
			visited = new boolean[N][N];

			for(int i=0;i<N;i++) {			
				for(int j=0;j<N;j++) {
					if(!visited[i][j]) {
						bfs(i,j);
						if(list.size() > 1) { 
							renew(sum);
							moveFlag=true;
						}
					}
				}
			}
			if(!moveFlag) {
				System.out.println(day);
				return;
			}
			day++;
		}		
	}	

	private static void bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		list  = new ArrayList<>();
		sum = 0; 

		q.offer(new int[] {x,y});
		list.add(new int[] {x,y});

		visited[x][y]=true;
		sum+=A[x][y];

		while(!q.isEmpty()) {
			int tmp[] = q.poll();			
			int x2 = tmp[0];
			int y2 = tmp[1];

			for(int i=0;i<4;i++) {
				int nx = x2+delx[i];
				int ny = y2+dely[i];

				if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny]) continue;

				if(Math.abs(A[nx][ny]-A[x2][y2])<L || Math.abs(A[nx][ny]-A[x2][y2])>R) continue;

				q.offer(new int[] {nx,ny});
				list.add(new int[] {nx,ny});
				visited[nx][ny]=true;
				sum+=A[nx][ny];
			}			
		}
	}
	private static void renew(int s) {
		//System.out.println(list.size());
		for(int[] i : list) {
			A[i[0]][i[1]] = s/list.size();
		}
	}
}
