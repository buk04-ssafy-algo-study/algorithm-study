package SSAFY.study.algo.week43;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_골드4_19942_다이어트 {

    static int N;
    static int[] minimumNutrition;  // 충족해야 하는 최소 값들 / 1: mp, 2: mf, 3: ms, 4: mv
    static int[][] ingredients; // 식재료 정보들
    static int minCost;
    static List<Integer> minCostIngredients;

    public static void main(String[] args) throws IOException {
        init();
        findMinCost(0, new int[5], new boolean[N]);
        StringBuilder sb = new StringBuilder();
        if (minCost == Integer.MAX_VALUE) sb.append(-1);
        else {
            sb.append(minCost).append("\n");
            for (int i = 0; i < minCostIngredients.size(); i++) sb.append(minCostIngredients.get(i) + 1).append(" ");
        }
        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        minimumNutrition = new int[4];
        int index = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) minimumNutrition[index++] = Integer.parseInt(st.nextToken());

        ingredients = new int[N][5];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) ingredients[i][j] = Integer.parseInt(st.nextToken());
        }
        minCost = Integer.MAX_VALUE;
        minCostIngredients = new ArrayList<>();
    }

    static void findMinCost(int now, int[] total, boolean[] isChecked) {
        if (total[4] >= minCost) return; // 이떄가지 최소 비용보다 크거나 같으면(최소 비용 같을 때 사전 순 정렬해라고 했으니까) 무조건 함수 종료
        if (isFulfilled(total)) {   // 기저, 조건이 갖춰졌을 경우
            minCost = total[4];
            minCostIngredients = new ArrayList<>();
            for (int i = 0; i < N; i++) if (isChecked[i]) minCostIngredients.add(i);

            return;
        }

        for (int n = now; n < N; n++) {
            isChecked[n] = true;
            for (int i = 0; i < 5; i++) total[i] += ingredients[n][i];
            findMinCost(n + 1, total, isChecked);
            isChecked[n] = false;
            for (int i = 0; i < 5; i++) total[i] -= ingredients[n][i];
        }
    }

    static boolean isFulfilled(int[] total) {
        for (int i = 0; i < 4; i++) if (total[i] < minimumNutrition[i]) return false;   // 최소 조건 하나라도 충족 안하면 false
        return true;
    }

}
