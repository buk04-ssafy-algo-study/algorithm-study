// https://www.acmicpc.net/problem/11559
import java.io.*;
import java.util.*;

public class Main {

	static final char BLANK = '.';
	static int answer = 0;
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; // u d l r
	static char[][] board;
	static boolean[][] visited;
	static List<int[]> puyos = new ArrayList<>();
	static List<int[]> tmp = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		board = new char[12][6];
		for(int r=0;r<12;r++){
			board[r] = br.readLine().toCharArray();
		}

		boolean explodeContinue = false;
		do{
			explodeContinue = chain();
			if(explodeContinue) answer++;
		}while(explodeContinue);

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	static boolean chain(){ // 연쇄 한 번에 제거되는 블록이 존재하면 true 리턴
		boolean explode = false;
		puyos.clear(); // blank로 만들 좌표 리스트 초기화
		visited = new boolean[12][6];	// 방문 배열 초기화
		for(int r=11;r>=0;r--){
			for(int c=0;c<6;c++){
					
				if(board[r][c] != BLANK){
					char color = board[r][c];
					tmp.clear(); // r,c에서부터 같은 색깔블록들을 tmp 리스트에 넣음
					explode(r,c,color);
					if(tmp.size()>=4){
						puyos.addAll(tmp);
						explode = true;
					}
				}
			}
		}

		if(explode){
			// 뿌요 폭발
			for(int idx=0;idx<puyos.size();idx++){
				int[] coord = puyos.get(idx);
				board[coord[0]][coord[1]] = BLANK;
			}
			fall(); // 추락
		}
		return explode;
	}
    
	static void explode(int r, int c, char color){ // 같은 색깔 블록들을 탐색하며 좌표를 tmp 리스트에 추가
		tmp.add(new int[]{r, c});
		visited[r][c] = true;
 
		for(int d=0;d<4;d++){
			int nr = r+dir[d][0];
			int nc = c+dir[d][1];
			if(!outOfRange(nr,nc) && board[nr][nc]==color && !visited[nr][nc]){
				explode(nr, nc, color);
			}
		}

	}

	static void print(){
		for(int r=0;r<12;r++){
			System.out.println(Arrays.toString(board[r]));
		}
	}

	static void fall(){
		for(int c=0;c<6;c++){
			int top = 11;
			for(int r=11;r>=0;r--){
				if(board[r][c]!=BLANK){
					if(r==top){
						top--;
						continue;
					}
					board[top][c] = board[r][c];
					board[r][c] = BLANK;
					top--;
				}
			}
		}
	}

	static boolean outOfRange(int r, int c){
		if(0<=r && r<12 && 0<=c && c<6) return false;
		return true;
	}
	
}
