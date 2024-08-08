// https://www.acmicpc.net/problem/8972
import java.io.*;
import java.util.*;

public class Main {

    static int R, C, jr, jc;
    static char[][] board;
    static Queue<int[]> mad;
    static int[][] dir = {{1,-1},{1,0},{1,1},{0,-1},{0,0},{0,1},{-1,-1},{-1,0},{-1,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        mad = new ArrayDeque<>();
        int moveCnt = 0;
        boolean lose = false;

        for(int r=0;r<R;r++) {
            String line = br.readLine();
            for(int c=0;c<C;c++) {
                board[r][c] = line.charAt(c);
                if(board[r][c]=='I') {
                    jr = r;
                    jc = c;
                } else if(board[r][c]=='R') {
                    mad.offer(new int[]{r, c});
                }
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        String cmd = br.readLine();
        for(char c: cmd.toCharArray()) {
            int d = c-'1';
            int nr = jr+dir[d][0];
            int nc = jc+dir[d][1];

            moveCnt++;
            if(board[nr][nc]=='R') {
                lose = true;
                break;
            }
            board[jr][jc] = '.';
            board[nr][nc] = 'I';
            jr = nr;
            jc = nc;

            int madCnt = mad.size();
            for(int i=0;i<madCnt;i++) {
                int[] arr = mad.poll();
                board[arr[0]][arr[1]] = '.';
                int[] next = findNextCoord(jr, jc, arr[0], arr[1]);
                nr = next[0];
                nc = next[1];
                if(board[nr][nc]=='I') {
                    lose = true;
                    break;
                }
                map.put(nr*C+nc, map.getOrDefault(nr*C+nc, 0)+1);
            }
            if(lose) break;

            for(int id: map.keySet()) {
                int value = map.get(id);
                if(value >= 2) continue;
                nr = id/C;
                nc = id%C;
                board[nr][nc] = 'R';
                mad.offer(new int[]{nr, nc});
            }
            map.clear();
        }

        if(!lose)
            printBoard();
        else 
            System.out.println("kraj "+moveCnt);
    }

    static int[] findNextCoord(int r1, int s1, int r2, int s2) {
        int[] result = new int[2];

        int dist = Integer.MAX_VALUE;

        for(int d=0;d<9;d++) {
            if(d==4) continue;
            int nr = r2+dir[d][0];
            int nc = s2+dir[d][1];
            int distance = Math.abs(r1-nr)+Math.abs(s1-nc);
            if(distance<dist) {
                dist = distance;
                result[0] = nr;
                result[1] = nc;
            }
        }

        return result;
    }
    
    static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for(int r=0;r<R;r++) {
            for(int c=0;c<C;c++) {
                sb.append(board[r][c]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
