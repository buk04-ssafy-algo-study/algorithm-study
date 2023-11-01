// https://www.acmicpc.net/problem/2667
// bfs, 안전영역 문제와 비슷한 방법으로 해결함
// visited 배열 대신 방문한 좌표의 값을 BLANK로 변경해서 방문 여부 체크함 
import java.io.*;
import java.util.*;

public class Main {

	static final int BLANK = 0;
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	static List<Integer> answerList;
	static int[][] map;
	static int N;
	static Queue<int[]> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		answerList = new ArrayList<>();
		q = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();

		// input
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++){
			String line = br.readLine();
			for(int j=0;j<N;j++){
				map[i][j] = line.charAt(j)-'0';
			}
		}

		// bfs
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(map[i][j]==BLANK) continue;
				answerList.add(find(i,j));
			}
		}

		// output
        Collections.sort(answerList);
		sb.append(answerList.size()+"\n");
		for(int cnt:answerList){
			sb.append(cnt+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
	static int find(int r, int c){
    // (r,c)에서부터 bfs 탐색
    // (r,c)와 연결된 좌표들 중 값이 1인 칸의 개수 리턴
		map[r][c] = BLANK; // visited
		q.offer(new int[]{r,c});
		int result = 1;
		
		while(!q.isEmpty()){
			int[] now = q.poll();
			r = now[0];
			c = now[1];
			for(int d=0;d<4;d++){
				int nr = r+dir[d][0];
				int nc = c+dir[d][1];
				if(0<=nr && nr<N && 0<=nc && nc<N && map[nr][nc]!=BLANK){
					q.offer(new int[]{nr,nc});
					map[nr][nc] = BLANK;
					result++;
				}
			}
		}
		return result;
	}
}
