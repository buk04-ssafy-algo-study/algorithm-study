// https://www.acmicpc.net/problem/18405
import java.io.*;
import java.util.*;

class Main {

    static int N, K, S;
    static PriorityQueue<Virus> q = new PriorityQueue<>();
    static class Virus implements Comparable<Virus> {
        int r, c, num, time;
        Virus(int r, int c, int num, int time) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.time = time;
        }
        @Override
        public int compareTo(Virus o) {
            if(this.time == o.time) return Integer.compare(this.num, o.num);
            return Integer.compare(this.time, o.time);
        }
    }
    static int[][] board;
    static int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}}; // u d l r

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N+1][N+1];
        
        // 시험관 정보
        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]>0) {
                    q.offer(new Virus(i, j, board[i][j], 0));
                }
            }
        }

        // S초 뒤 x행 y열에 있는 바이러스의 번호
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        solution();

        System.out.println(board[X][Y]);
    }

    static int solution() {
        int result = 0;

        while(!q.isEmpty()) {
            Virus now = q.poll();
            int r = now.r;
            int c = now.c;

            for(int d=0;d<4;d++) {
                int nr = r+dir[d][0];
                int nc = c+dir[d][1];

                if(1<=nr && nr<=N && 1<=nc && nc<=N && board[nr][nc]==0 && now.time<S) {
                    board[nr][nc] = now.num;
                    q.offer(new Virus(nr, nc, now.num, now.time+1));
                }
            }
        }

        return result;
    }
}
