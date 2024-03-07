import java.util.*;
import java.io.*;

class Pos {
    int r;
    int c;
    int dist;
    
    public Pos(int r, int c, int dist){
        this.r = r;
        this.c = c;
        this.dist = dist;
    }
}

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N, M;
    
    public int solution(int[][] maps) {
        
        int answer = 987654321;
        N = maps.length;
        M = maps[0].length;
        boolean flag = false;
        
        boolean[][] visited = new boolean[N][M];
        Queue<Pos> q = new ArrayDeque<>();
        
        q.offer(new Pos(0, 0, 1));
        visited[0][0] = true;
        
        while(!q.isEmpty()){
            
            Pos p = q.poll();
            if(p.r == N-1 && p.c == M-1){
                answer = Math.min(answer, p.dist);
            }
            for(int i=0; i<4; i++){
                int nr = dr[i] + p.r;
                int nc = dc[i] + p.c;
                
                if(nr >= N || nr < 0 || nc >= M || nc < 0 || visited[nr][nc] == true) continue;
                if(maps[nr][nc] == 0) continue;
                
                visited[nr][nc] = true;
                q.offer(new Pos(nr, nc, p.dist+1));
                
                
            }      
        }
        if(answer == 987654321) return -1;
        return answer;
    }
}