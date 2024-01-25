// https://www.acmicpc.net/problem/3967
import java.io.*;
import java.util.*;

public class Main {
    
    static char[][] board;
    static int emptyCnt = 0;
    static List<int[]> coord;
    static boolean[] used;
    static boolean find = false;
    static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

        board = new char[5][9];
        used = new boolean[12];
        coord = new ArrayList<>();

        for(int r=0;r<5;r++) {
            String line = br.readLine();
            board[r] = line.toCharArray();
            for(int c=0;c<9;c++) {
                if(board[r][c]=='.') continue;
                else if(board[r][c]=='x') { // empty
                    coord.add(new int[]{r,c});
                    emptyCnt++;
                } else { // alphabet
                    used[board[r][c]-'A'] = true;
                }
            }
        }

        fill(0);

		bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void fill(int x) {
        if(x==emptyCnt) {
            if(isMagicStar()) {
                for(int i=0;i<5;i++) {
                    for(int j=0;j<9;j++) {
                        sb.append(board[i][j]);
                    }
                    sb.append("\n");
                }
                find = true;
            }
            return;
        }

        for(int i=0;i<12;i++) {
            if(used[i]) continue;
            int[] now = coord.get(x);
            used[i] = true;
            board[now[0]][now[1]] = (char)('A'+i);
            fill(x+1);
            if(find) return; // 매직스타가 되는 경우를 찾았다면 리턴
            board[now[0]][now[1]] = '.';
            used[i] = false;
        }
    }

    static boolean isMagicStar(){
        // inverted triangle
        if(board[4][4]+board[3][5]+board[2][6]+board[1][7]-('A'*4) != 22) return false; // / 
        if(board[4][4]+board[3][3]+board[2][2]+board[1][1]-('A'*4) != 22) return false; // \
        if(board[1][1]+board[1][3]+board[1][5]+board[1][7]-('A'*4) != 22) return false; // -
        // triangle
        if(board[0][4]+board[1][3]+board[2][2]+board[3][1]-('A'*4) != 22) return false; // /
        if(board[0][4]+board[1][5]+board[2][6]+board[3][7]-('A'*4) != 22) return false; // \
        if(board[3][1]+board[3][3]+board[3][5]+board[3][7]-('A'*4) != 22) return false; // -
        return true;
    }
}
