import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] servers = new int[24]; // 해당 시간대에 증설된 서버 수

        for (int i = 0; i < 24; i++) {
            int server = players[i] / m;

            if (server == 0) continue; // 증설될 서버 없으면
            if (servers[i] >= server) continue; // 해당 시간대에 이미 증설된 서버있으면
            
            int add = server - servers[i];
            answer += add; // 증설 서버 수 추가

            int end = Math.min(24, i + k);

            for (int j = i; j < end; j++) {
                servers[j] += add;
            }
        }

        return answer;
    }
}