// https://www.acmicpc.net/problem/1189
import java.io.*;
import java.util.*;

public class Main {

    static int answer, R, C, K;
    static char[][] map;
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int r=0;r<R;r++) {
            String line = br.readLine();
            for(int c=0;c<C;c++) {
                map[r][c] = line.charAt(c);
            }
        }

        map[R-1][0] = 'T';
        move(R-1, 0, 1);

        
        System.out.println(answer);
    }

    static void move(int r, int c, int dist) {
        if(dist>K) return;
        if(r==0 && c==C-1) {
            if(dist==K) answer++;
            return;
        }

        for(int d=0;d<4;d++) {
            int nr = r+dir[d][0];
            int nc = c+dir[d][1];

            if(0<=nr && nr<R && 0<=nc && nc<C && map[nr][nc]!='T') {
                map[nr][nc] = 'T';
                move(nr, nc, dist+1);
                map[nr][nc] = '.';
            }
        }
    }

}
