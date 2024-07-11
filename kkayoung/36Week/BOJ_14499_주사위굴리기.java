// https://www.acmicpc.net/problem/14499
import java.util.*;
import java.io.*;

public class Main {

    static int N, M, x, y, K;
    static int[][] dir = {{0,1},{0,-1},{-1,0},{1,0}};// east west north south
    static int[] dice = {0, 1, 2, 3, 4, 5, 6};
    static int[] value = {0,0,0,0,0,0,0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<M;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        int r = x;
        int c = y;
        st = new StringTokenizer(br.readLine());
        for(int k=0;k<K;k++) {
            int d = Integer.parseInt(st.nextToken())-1;
            
            int nr = r+dir[d][0];
            int nc = c+dir[d][1];
            if(0<=nr && nr<N && 0<=nc && nc<M) {
                switch(d) {
                    case 0:
                        moveEast();
                        break;
                    case 1:
                        moveWest();
                        break;
                    case 2:
                        moveNorth();
                        break;
                    case 3:
                        moveSouth();
                        break;
                }
                if(map[nr][nc] == 0) { // 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
                    map[nr][nc] = value[dice[6]];
                } else {
                    // 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
                    value[dice[6]] = map[nr][nc];
                    map[nr][nc] = 0;
                }
                r = nr;
                c = nc;
                sb.append(value[dice[1]]).append("\n");
            }
            
        }

        System.out.println(sb.toString());
    
    }

    public static void moveEast() {
        int first = dice[1];
        int second = dice[2];
        int third = dice[3];
        int fourth = dice[4];
        int fifth = dice[5];
        int sixth = dice[6];
        dice[1] = fourth;
        dice[2] = second;
        dice[3] = first;
        dice[4] = sixth;
        dice[5] = fifth;
        dice[6] = third;
    }
    public static void moveWest() {
        int first = dice[1];
        int second = dice[2];
        int third = dice[3];
        int fourth = dice[4];
        int fifth = dice[5];
        int sixth = dice[6];
        dice[1] = third;
        dice[2] = second;
        dice[3] = sixth;
        dice[4] = first;
        dice[5] = fifth;
        dice[6] = fourth;
    }
    public static void moveNorth() {
        int first = dice[1];
        int second = dice[2];
        int third = dice[3];
        int fourth = dice[4];
        int fifth = dice[5];
        int sixth = dice[6];
        dice[1] = fifth;
        dice[2] = first;
        dice[3] = third;
        dice[4] = fourth;
        dice[5] = sixth;
        dice[6] = second;
    }
    public static void moveSouth() {
        int first = dice[1];
        int second = dice[2];
        int third = dice[3];
        int fourth = dice[4];
        int fifth = dice[5];
        int sixth = dice[6];
        dice[1] = second;
        dice[2] = sixth;
        dice[3] = third;
        dice[4] = fourth;
        dice[5] = first;
        dice[6] = fifth;
    }
}
