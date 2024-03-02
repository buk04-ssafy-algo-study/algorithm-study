// https://school.programmers.co.kr/learn/courses/30/lessons/49189?language=java
import java.util.*;

class Solution {
    
    static int[] dist;
    static List<Integer>[] adjList;
    static int answer = 0;
    static int INF = 50001;
    
    public int solution(int n, int[][] edge) {
        
        dist = new int[n+1];
        Arrays.fill(dist, INF);
        
        adjList = new List[n+1];
        for(int i=1;i<=n;i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int[] e:edge) {
            adjList[e[0]].add(e[1]);
            adjList[e[1]].add(e[0]);
        }
        
        dfs(1, 0);
        
        return findNode(dist);
    }
    
    public void dfs(int x, int d) {
        
        dist[x] = d;
        
        for(int adj:adjList[x]) {
            if(d+1 <dist[adj]) {
                dfs(adj, d+1);
            }
        }
    }
    
    public int findNode(int[] dist) {
        int maxValue = Integer.MIN_VALUE;
        int result = 0;
        for(int i=1;i<dist.length;i++) {
            if(dist[i]>maxValue) {
                maxValue = dist[i];
                result = 1;
            } else if(dist[i]==maxValue) result++;
        }
        return result;
    }
}
