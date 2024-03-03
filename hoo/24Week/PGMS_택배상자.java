package SSAFY.study.algo.week24;

import java.util.Stack;

public class PGMS_택배상자 {

    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> sub = new Stack<>();
        int idx = 0;

        for (int i = 1; i < order.length+1; i++) {
            if (order[idx] != i) {    // 택배 기사님이 원하는 박스가 아닌 경우
                sub.push(i);
                continue;
            }

            idx += 1;
            answer += 1;    // 원하는 박스인 경우 택배 실음

            while (sub.size() != 0 && order[idx] == sub.peek()) {
                sub.pop();
                idx += 1;
                answer += 1;
            }
        }

        return answer;
    }

}
