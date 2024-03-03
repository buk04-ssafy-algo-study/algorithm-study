// https://school.programmers.co.kr/learn/courses/30/lessons/131704
import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        
        // 보조 컨베이어 벨트
        Stack<Integer> assistance = new Stack<>();
        // 트럭에 싣은 상자의 개수
        int answer = 0;
        // 택배 기사님이 원하는 상자 번호를 가져오기 위한 index
        int i = 0;
        
        // 반복문은 컨베이어 벨트를 의미 (가장 높은 상자 번호는 order의 length)
        for(int box = 1; box <= order.length; box++)
        {
            // 컨베이어 벨트에서 온 상자가 택배 기사님이 원하는 상자 번호가 아닌 경우
            if(order[i] != box)
            {
                // 보조 컨베이어 벨트에 상자 추가
                assistance.add(box);
                continue;
            }
            
            // 컨베이어 벨트에서 온 상자가 택배 기사님이 원하는 상자 번호일 경우
            
            // 택배 기사님이 원하는 다음 상자를 가져오고, 트럭에 싣은 상자 개수 증가
            i++;
            answer++;
            
            // 보조 컨베이어 벨트에 다음 상자가 있는지 확인
            while (assistance.size() != 0 && order[i] == assistance.peek())
            {
                // 보조 컨베이어 벨트에 만족하는 상자 번호가 있으므로 보조 컨베이어 벨트에서 빼고 트럭에 싣기
                assistance.pop();
                i++;
                answer++;
            }
        }

        return answer;
    }
}
