import java.util.*;

class Solution {
    
    static String answer;
    static int[] dirRow = {1, 0, 0, -1};    // 하 좌 우 상(사전 순 탐색을 위함)
    static int[] dirCol = {0, -1, 1, 0};
    static String[] dirString = {"d", "l", "r", "u"};
    static int n, m, r, c;
    static int k;   // 조건 k
    static boolean finded;  // 정답 찾았는지 여부 저장
    
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = "impossible";
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;
        this.finded = false;
        
        int initial_dist = Math.abs(x-r) + Math.abs(y-c);
        if (k < initial_dist || (initial_dist%2 != k%2)) return answer;    // 초기에 주어진 이동횟수가 거리보다 작으면 바로 종료
        dfs(0, x, y, "");
        
        return answer;
    }
    
    static boolean isOuted(int row, int col) {
        if ((1 <= row && row <= n) && (1 <= col && col <= m)) return false;
        return true;
    }
    
    static void dfs(int cnt, int x, int y, String way) {   // x, y: 현재 위치, way: 현재까지 이동한 경로
        if (finded) return; // 정답 찾았으면 종료
        
        int dist = Math.abs(x-r) + Math.abs(y-c);   // 도착점까지의 거리
        if (dist == 0 && cnt == k) {   // 도착점에 도착했을 때
            finded = true;  // 맨 처음 도달한 경로를 정답이라고 판단, 정답 찾았다고 처리
            answer = way;
            
            return;
        } else if (dist > 0) {    // 도착점에 도착 못했을 때
            if (dist > (k-cnt)) return;   // 남은 횟수가 도착점까지의 거리보다 짧으면 도착 못함 -> 중단
            else if (((k-cnt)-dist) % 2 != 0) return; // 남은 횟수 - 도착점까지의 거리가 짝수가 아니어도 도착 못함 -> 중단
        }
        
        for (int i = 0; i < 4; i++) {
            int nextRow = x + dirRow[i];
            int nextCol = y + dirCol[i];
            String nextDir = String.valueOf(dirString[i]);
            
            if (!isOuted(nextRow, nextCol)) dfs(cnt+1, nextRow, nextCol, way+nextDir);
        }
    }
    
}
