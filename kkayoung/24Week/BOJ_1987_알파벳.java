// https://www.acmicpc.net/problem/1987
import java.io.*;
import java.util.*;

public class Main {
    
    static int R, C;
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    static boolean[] visited = new boolean[26];
    static int[][] map;
    static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
		    map = new int[R][C];

        for(int i=0;i<R;i++) {
            String line = br.readLine();
            for(int j=0;j<C;j++) {
                map[i][j] = line.charAt(j)-'A';
            }
        }
        
        visited[map[0][0]] = true;
        moveHorse(0,0,1);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    public static void moveHorse(int r, int c, int cnt) { // 행, 열, 방문한 칸 수
        answer = Math.max(answer, cnt);

        for(int d=0;d<4;d++) {
            int nr = r+dir[d][0];
            int nc = c+dir[d][1];

            if(0<=nr && nr<R && 0<=nc && nc<C && !visited[map[nr][nc]]) {
                visited[map[nr][nc]] = true;
                moveHorse(nr, nc, cnt+1);
                visited[map[nr][nc]] = false;
            }
        }
    }
}
