package SSAFY.study.algo.week40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드5_2668_숫자고르기 {

    static int N;
    static HashMap<Integer, Integer> map;
    static List<Integer> answer;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        init();
        boolean[] isChecked = new boolean[N+1];
        for (int i = 1; i < N+1; i++) {
            isChecked[i] = true;
            dfs(i, isChecked, i);
            isChecked[i] = false;
        }
        Collections.sort(answer);
        sb.append(answer.size()).append("\n");
        for (int i = 0; i < answer.size(); i++) sb.append(answer.get(i)).append("\n");
        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new HashMap<>();
        for (int i = 1; i < N+1; i++) map.put(i, Integer.parseInt(br.readLine()));
        answer = new ArrayList<>();
    }

    static void dfs(int n, boolean[] isChecked, int target) {
        if (!isChecked[map.get(n)]) {
            isChecked[map.get(n)] = true;
            dfs(map.get(n), isChecked, target);
            isChecked[map.get(n)] = false;  // 자바에서 배열은 참조타입이므로 파라미터로 변수를 전달한 경우에도 모든 경로 탐색을 위해서는 꼭 방문 체크 해제를 처리해주어야 함.
        }

        if (map.get(n) == target) answer.add(target);
    }

}
