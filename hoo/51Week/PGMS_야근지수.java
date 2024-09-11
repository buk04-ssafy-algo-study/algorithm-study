package SSAFY.study.algo.week51;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PGMS_야근지수 {

    public long solution(int n, int[] works) {
        long answer = calcMinStamina(n, works);

        return answer;
    }

    long calcMinStamina(int n, int[] works) {
        long answer = 0;
        int workTotal = 0;  // 모든 작업의 처리량을 더한 값, n시간 안에 모든 작업 처리가 가능한 경우를 판별하기 위함
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() { // 큰 값을 우선순위로 하는 우선 순위 큐
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        for (int i = 0; i < works.length; i++) {
            pq.offer(works[i]);
            workTotal += works[i];
        }
        if (workTotal <= n) return 0;   // n시간 안에 모든 작업 처리 가능한 경우 0 반환하고 함수 종료

        int now;
        while (n > 0 && !pq.isEmpty()) {
            now = pq.poll() - 1;
            if (now != 0) pq.offer(now);
            n--;
        }
        while (!pq.isEmpty()) answer += Math.pow((long) pq.poll(), 2);

        return answer;
    }

}
