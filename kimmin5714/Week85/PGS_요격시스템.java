import java.util.Arrays;

public class PGS_요격시스템 {
    public int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);

        int missileEnd = 0;
        for (int[] target : targets) {
            if (missileEnd == 0) {
                answer++;
                missileEnd = target[1];
                continue;
            }

            if (target[0] < missileEnd) continue;

            answer++;
            missileEnd = target[1];
        }

        return answer;
    }
}
