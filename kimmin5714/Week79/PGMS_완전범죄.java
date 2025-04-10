import java.util.*;
class Solution {
    public int solution(int[][] info, int n, int m) {
        int answer = n;

        // 물것 갯수가 i이고 B의 흔적 갯수가 m개일 때, A의 최소 흔적 갯수
        int[][] dp = new int[info.length+1][m];

        // 초기화
        for (int i = 1; i <= info.length; i++) {
            Arrays.fill(dp[i], n);
        }

        for(int i=1;i<=info.length;i++) {
            int aTrace = info[i-1][0];
            int bTrace = info[i-1][1];

            for(int j=0;j<m;j++) {
                // a선택
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+aTrace);

                // b선택
                if(j+bTrace<m){
                    dp[i][j+bTrace] = Math.min(dp[i][j+bTrace], dp[i-1][j]);
                }
            }
        }

        for(int i=0;i<m;i++) {
            // 모든 물건 훔친 경우에 가장 적은 A 최소 흔적 갯수
            answer = Math.min(answer, dp[info.length][i]);
        }
        if(answer == n) answer=-1;
        return answer;
    }
}