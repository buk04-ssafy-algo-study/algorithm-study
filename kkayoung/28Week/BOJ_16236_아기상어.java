// https://www.acmicpc.net/problem/16236
import java.io.*;
import java.util.*;

public class Main {

    static int N, babySharkR, babySharkC, babySharkSize;
    static int[][] ocean;
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    static Queue<int[]> q = new ArrayDeque<>();

    static class Shark implements Comparable<Shark>{
        int r, c, size, dist;
        Shark(int r, int c, int size, int dist) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.dist = dist;
        }
        @Override
        public int compareTo(Shark o) {
            if(this.dist != o.dist) return Integer.compare(this.dist, o.dist);
            else if(this.r != o.r) return Integer.compare(this.r, o.r);
            else return Integer.compare(this.c, o.c);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ocean = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                ocean[i][j] = Integer.parseInt(st.nextToken());
                if(ocean[i][j]==9) {
                    babySharkR = i;
                    babySharkC = j;
                }
            }
        }

        int answer = 0;
        babySharkSize = 2;
        int eatCnt = 0;
        while(true) {
            Shark shark = getShark(new Shark(babySharkR, babySharkC, babySharkSize, 0));
            if(shark==null) break;
            // move babyShark
            ocean[babySharkR][babySharkC] = 0;
            babySharkR = shark.r;
            babySharkC = shark.c;
            // bulk up
            eatCnt++;
            if(eatCnt==babySharkSize) {
                babySharkSize++;
                eatCnt=0; // init
            }
            answer+=shark.dist;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    static Shark getShark(Shark babyShark) {

        boolean[][] visited = new boolean[N][N];
        List<Shark> canEat = new ArrayList<>();
        q.offer(new int[]{babySharkR, babySharkC, babySharkSize, 0});
        visited[babyShark.r][babySharkC] = true;

        while(!q.isEmpty()) {
            int[] bs = q.poll();
            int r = bs[0];
            int c = bs[1];
            int size = bs[2];
            int dist = bs[3];

            for(int i=0;i<4;i++) {
                int nr = r+dir[i][0];
                int nc = c+dir[i][1];
                if(0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc] && size>=ocean[nr][nc]) {
                    visited[nr][nc] = true;
                    if(ocean[nr][nc]==0 || ocean[nr][nc]>=size) {
                        // empty || fish(can't eat)
                        q.offer(new int[]{nr, nc, size, dist+1});
                    } else {
                        // fish(can eat)
                        canEat.add(new Shark(nr,nc,ocean[nr][nc], dist+1));
                    }
                }
            }
        }

        if(canEat.size()==0) {
            return null;
        } else {
            Collections.sort(canEat);
            return canEat.get(0);
        }
    }
}
