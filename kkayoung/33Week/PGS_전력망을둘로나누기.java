// https://school.programmers.co.kr/learn/courses/30/lessons/86971
// 완전탐색
class Solution {
    
    static boolean[][] adjArr;
    static boolean[] visited;
    static int cnt, N;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        N = n;
        adjArr = new boolean[n+1][n+1];
        // 인접 행렬 만들기
        for(int[] arr:wires) {
            adjArr[arr[0]][arr[1]] = true;
            adjArr[arr[1]][arr[0]] = true;
        }
        
        // 개수 
        for(int i=0;i<wires.length;i++) {
            // 간선 숨기기
            int v = wires[i][0];
            int w = wires[i][1];
            adjArr[v][w] = false;
            adjArr[w][v] = false;
            // 1번 노드가 포함된 전력망의 송전탑 개수 구하기
            visited = new boolean[n+1];
            visited[1] = true;
            cnt = 0;
            calculate(1);
            
            answer = Math.min(answer, Math.abs(cnt-(n-cnt)));
            // 간선 복구
            adjArr[v][w] = true;
            adjArr[w][v] = true;
        }
        
        return answer;
    }
    
    static void calculate(int now) {

        for(int i=1;i<=N;i++) {
            if(!adjArr[now][i] || visited[i]) continue;
            visited[i] = true;
            calculate(i);
            visited[i] = false;
        }
        cnt++;

    }
}
