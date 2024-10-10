package SSAFY.study.algo.week50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PGMS_징검다리 {

    public int solution(int distance, int[] rocks, int n) {
        int answer = findMaxMin(distance, rocks, n);

        return answer;
    }

    int findMaxMin(int distance, int[] rocks, int n) {
        Arrays.sort(rocks); // 내가 하려는 방법은 시작지점부터 끝지점까지 각 바위에 대해 순차적으로 사이 거리를 측정하는 것이므로 순서대로 정렬해줌.
        int answer = 0;

        int lower = 1;  // 매개변수 탐색에 사용할 하한선
        int upper = distance;   // 상한선
        int mid;    // 매개변수 -> 이 값을 기준으로 직전 바위와 거리 차이가 더 큰지를 비교, 차이가 작은 바위는 파괴 처리
        while (lower <= upper) { // 이분탐색을 통해 매개변수(이 값을 기준으로 바위 사이의 거리를 )를 지정해줌.
            mid = (lower + upper) / 2;
            if (isPossible(distance, rocks, n, mid) <= n) {
                answer = mid;
                lower = mid + 1;  // 현재 매개변수를 통해 바위를 파괴했을 때 조건인 n을 만족하거나 더 작은 횟수를 파괴했음. 사이의 거리를 더 넓혀서(구할 정답이 거리의 최솟값 중 최댓값이므로) 후의 조건도 탐색하기
            }
            else upper = mid - 1;   // n보다 더 많은 바위를 파괴했음. 매개변수인 사이 거리를 좁혀볼 필요가 있음.
        }

        return answer;
    }

    int isPossible(int distance, int[] rocks, int n, int mid) { // 매개변수(바위 사이의 거리)를 기준으로 가능한 경우인 지 판단, 나는 앞과 거리 차이가 매개변수보다 적게 나면 파괴하는 바위로 상정함. -> 사실은 이 부분이 핵심임. 이 부분을 위해 이분탐색을 "이용해서" 매개변수를 찾는 것임.
        List<Integer> nonDistroyed = new ArrayList<>(); // "파괴되지 않은" 바위를 저장해줄 리스트
        nonDistroyed.add(0);    // 첫 바위와 시작점도 비교하기 위해 시작점을 우선 넣어줌
        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i] - nonDistroyed.get(nonDistroyed.size()-1) >= mid) nonDistroyed.add(rocks[i]); // 매개변수로 주어진 거리보다 길거나 같으면 바위를 살려놓음
        }
        if (distance - nonDistroyed.get(nonDistroyed.size()-1) < mid) nonDistroyed.remove(nonDistroyed.size()-1);  // 마지막 도착지와도 비교, 매개변수보다 차이가 적으면 직전 바위도 파괴 처리 / 반목문에서 파괴되지 않은 바위를 저장하다가 여기서는 파괴를 한다니 논리상 일관성이 없긴 한 듯. 문제 풀이가 아니라 실제 서비스 개발이라면 더 고려해봐야 할 부분.

        return rocks.length - (nonDistroyed.size() - 1);    // 최초에 시작 지점 넣어준 것 제외하고 파괴된 바위 수 반환
    }

}
