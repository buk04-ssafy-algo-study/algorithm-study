// https://school.programmers.co.kr/learn/courses/30/lessons/1844?language=java
import java.util.*;

class Solution {
    
    static int EMPTY = 1;
    static int[][] visited;
    static Queue<int[]> q = new ArrayDeque<>();
    static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}}; // 동 서 남 북
    static int n, m;
    static int INF = 100*100+1;
    
    public int solution(int[][] maps) {
        int answer = 0;
        n = maps.length;
        m = maps[0].length;
        visited = new int[n][m];
        for(int i=0;i<n;i++) Arrays.fill(visited[i], INF);
        
        bfs(maps);
        
        return (visited[n-1][m-1]>=INF) ? -1 : visited[n-1][m-1];
    }
    
    public void bfs(int[][] maps) {
        visited[0][0] = 1;
        q.offer(new int[]{0,0});
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];
            
            for(int d=0;d<4;d++) {
                int nr = r+dir[d][0];
                int nc = c+dir[d][1];
                
                if(0<=nr && nr<n && 0<=nc && nc<m && maps[nr][nc]==EMPTY && visited[r][c]+1<visited[nr][nc]) {
                    visited[nr][nc] = visited[r][c]+1;
                    q.offer(new int[]{nr, nc});
                }
            }
            
        }
        
    }
}
