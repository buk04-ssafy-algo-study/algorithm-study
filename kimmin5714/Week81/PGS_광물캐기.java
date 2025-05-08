import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PGS_광물캐기 {

    static class Mineral {
        private int diamond;
        private int iron;
        private int stone;

        public Mineral(int diamond, int iron, int stone) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
        }
    }

    static int[][] minus;
    static List<Mineral> list;

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        minus = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};

        int totalPicks = Arrays.stream(picks).sum();
        list = new ArrayList<>();

        // 5개씩 나누고, 다이아,철,돌로 캤을 때 드는 각각 피로도 계산
        for (int i = 0; i < minerals.length; i += 5) {
            if (totalPicks == 0) break;

            int dia = 0, iron = 0, stone = 0;
            for (int j = i; j < i + 5; j++) {
                if (j == minerals.length) break;

                String mineral = minerals[j];
                int val = mineral.equals("iron") ? 1 : mineral.equals("stone") ? 2 : 0;

                dia += minus[0][val];
                iron += minus[1][val];
                stone += minus[2][val];
            }

            list.add(new Mineral(dia, iron, stone));
            totalPicks--; // 곡괭이 모두 다쓰면 종료
        }

        // 제일 피로도 높은 순 정렬 (=돌로 캤을 때 피로도 내림차순)
        Collections.sort(list, ((o1, o2) -> (o2.stone - o1.stone))); 
        
        for (Mineral m : list) { // 다이아,철,돌 곡괭이 순서로 사용
            int dia = m.diamond;
            int iron = m.iron;
            int stone = m.stone;

            if (picks[0] > 0) {
                answer += dia;
                picks[0]--;
                continue;
            }
            if (picks[1] > 0) {
                answer += iron;
                picks[1]--;
                continue;
            }
            if (picks[2] > 0) {
                answer += stone;
                picks[2]--;
                continue;
            }
        }
        return answer;
    }
}