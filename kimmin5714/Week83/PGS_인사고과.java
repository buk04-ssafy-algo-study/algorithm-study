import java.util.Arrays;

public class PGS_인사고과 {
    public int solution(int[][] scores) {
        int answer = 1;
        int[] me = scores[0]; // 완호 점수
        int max = 0; // 최대 동료 점수

        Arrays.sort(scores, (a, b) -> { // 근무 태도 점수 내림차순, 동료 평가 점수 오름차순 정렬
            if (b[0] == a[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });

        for (int i = 0; i < scores.length; i++) {
            if (scores[i][1] < max) { // 현재 동료 평가 점수가 동료 최대 점수보다 작으면 인센티브 x
                if (scores[i][0] == me[0] && scores[i][1] == me[1]) { // 완호이면 -1 출력
                    return -1;
                }
                continue;
            }

            max = Math.max(max, scores[i][1]); // 동료 최대 점수 갱신

            if (scores[i][0] + scores[i][1] > me[0] + me[1]) // 합이 완호보다 크면 등수 밀림
                answer++;
        }

        return answer;
    }
}
