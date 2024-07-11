// https://www.acmicpc.net/problem/20056
import java.io.*;
import java.util.*;

class Main {

    static class Fireball {
        int r, c, m, s, d; // 행, 열, 질량, 속력, 방향
        Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static class Info {
        int s, d, fireballCnt, m;
        boolean isOdd, isSame;
        Info(int s, int d, int m, boolean isOdd) {
            this.s = s;
            this.d = d;
            this.m = m;
            this.isOdd = isOdd;
            this.fireballCnt = 1;
            this.isSame = true;
        }
        
    }
    static int[][] dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    static int N, M, K;
    static List<Fireball> fireballs = new ArrayList<>();
    static Info[][] info;
    static Queue<int[]> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        info = new Info[N][N];
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs.add(new Fireball(r, c, m, s, d));
        }

        while(K>0) {
            move();
            collect();
            K--;
        }
        
        int answer = 0;
        for(Fireball fireball : fireballs) {
            answer += fireball.m;
        }
        System.out.println(answer);

    }

    static void move() {
        for(Fireball fireball : fireballs) {
            fireball.r = (fireball.r+dir[fireball.d][0]*fireball.s)%N;
            fireball.c = (fireball.c+dir[fireball.d][1]*fireball.s)%N;
            if(fireball.r<0) fireball.r += N;
            if(fireball.c<0) fireball.c += N;

            if(info[fireball.r][fireball.c]==null) {
                info[fireball.r][fireball.c] = new Info(fireball.s, fireball.d, fireball.m, (fireball.d%2==0) ? false : true);
                q.offer(new int[]{fireball.r, fireball.c});
            } else {
                // already exist
                info[fireball.r][fireball.c].fireballCnt++;
                info[fireball.r][fireball.c].m += fireball.m;
                info[fireball.r][fireball.c].s += fireball.s;

                if(info[fireball.r][fireball.c].isSame) {
                    boolean isFireballOdd = (fireball.d%2 != 0) ? true : false;
                    boolean isInfoOdd = info[fireball.r][fireball.c].isOdd;

                    info[fireball.r][fireball.c].isSame = !(isFireballOdd != isInfoOdd);
                }
            }
            
        }
    }

    static void collect() {

        List<Fireball> newList = new ArrayList<>();
        
        while(!q.isEmpty()) {
            int[] coord = q.poll();
            int r = coord[0];
            int c = coord[1];

            if(info[r][c].fireballCnt==1) {
                newList.add(new Fireball(r, c, info[r][c].m, info[r][c].s, info[r][c].d));
            } else {
                // 2개 이상
                if(info[r][c].m/5 == 0) {
                    info[r][c] = null;
                    continue; // 소멸
                }

                int newM = info[r][c].m/5;
                int newS = info[r][c].s/info[r][c].fireballCnt;
                if(info[r][c].isSame) {
                    // 방향: 0,2,4,6
                    newList.add(new Fireball(r, c, newM, newS, 0));
                    newList.add(new Fireball(r, c, newM, newS, 2));
                    newList.add(new Fireball(r, c, newM, newS, 4));
                    newList.add(new Fireball(r, c, newM, newS, 6));
                } else {
                    // 방향: 1,3,5,7
                    newList.add(new Fireball(r, c, newM, newS, 1));
                    newList.add(new Fireball(r, c, newM, newS, 3));
                    newList.add(new Fireball(r, c, newM, newS, 5));
                    newList.add(new Fireball(r, c, newM, newS, 7));
                }
            }
            info[r][c] = null; // 초기화
        }
        fireballs = newList;
    }
}
