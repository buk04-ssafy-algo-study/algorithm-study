// https://www.acmicpc.net/problem/2638
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] paper;
    static Queue<int[]> q;
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    static int CHEESE = 1;
    static int EMPTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        q = new ArrayDeque<>();
        int answer = 0;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];

        for(int i=0;i<N;i++){ 
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int meltCnt = 0;
        while(true){
            meltCnt = findEdge();
            if(meltCnt==0) break;
            answer++;
        }
        
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    static int findEdge() {
        boolean[][] visited = new boolean[N][M];
        q.offer(new int[]{0,0});
        visited[0][0] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];

            for(int d=0;d<4;d++){
                int nr = r+dir[d][0];
                int nc = c+dir[d][1];

                if(0<=nr && nr<N && 0<=nc && nc<M && visited[nr][nc]==false) {
                    if(paper[nr][nc]==EMPTY) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }
                    else { // cheese
                        paper[nr][nc]++; // 외부 공기 개수 1 증가
                    }
                }
            }
        }
        
        return melt();
    }

    static int melt() { // 모서리 치즈 녹이기 + 1로 초기화
        int cheeseCnt = 0;
        for(int r=0;r<N;r++) {
            for(int c=0;c<M;c++) {
                if(paper[r][c]==0) continue; // empty
                else if(paper[r][c]>=3) { // edge
                    cheeseCnt++;
                    paper[r][c] = 0; // melt
                }
                else if(paper[r][c]>=1) {
                    paper[r][c] = 1; // init
                    cheeseCnt++;
                }
            }
        }
        return cheeseCnt;
    }
}
