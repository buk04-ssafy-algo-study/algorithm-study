// https://www.acmicpc.net/problem/20165
import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int N, M, R, answer;
	static int[][] board;
	static char[][] status;
	static Map<Character, Integer> convertdir;
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; // N S W E

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		init();

		for(int round=0;round<R;round++){
			attack();
			defense();
		}

		// output
		print();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void attack() throws IOException {
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		int d = convertdir.get(st.nextToken().charAt(0));

		fall(r, c, d);
	}

	static void fall(int r, int c, int d) {
		if (r<0 || c<0 || r>=N || c>=M) { // out of range
			return;
		}
		if(status[r][c]=='F') { // 이미 쓰러진 도미노
			return;
		}
			
		answer++;
		int height = board[r][c];
		status[r][c] = 'F';
		for(int i=1;i<height;i++){
			switch(d){
				case 0: // N
					fall(r-i, c, d);
					break;
				case 1: // S
					fall(r+i, c, d);
					break;
				case 2: // W
					fall(r, c-i, d);
					break;
				case 3: // E
					fall(r, c+i, d);
					break;
			}
		}
	}

	static void defense() throws IOException {
		st = new StringTokenizer(br.readLine());
		status[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 'S';
	}

	static void print() {
		sb.append(answer).append("\n");
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				sb.append(status[i][j]).append(" ");
			}
			sb.append("\n");
		}
	}

	static void init() throws IOException {
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		status = new char[N][M];
		for(int r=0;r<N;r++){
			Arrays.fill(status[r], 'S');
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<M;c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		convertdir = new HashMap<>();
		convertdir.put('N',0);
		convertdir.put('S',1);
		convertdir.put('W',2);
		convertdir.put('E',3);
	}
}
