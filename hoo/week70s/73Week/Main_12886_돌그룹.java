package SSAFY.study.algo.week70s.week73;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_12886_돌그룹 {

    public static void main(String[] args) throws IOException {
        int[] initStones = init();
        System.out.println(judgeIsPossible(initStones)? 1:0);
    }

    static int[] init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] initStones = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) initStones[i] = Integer.parseInt(st.nextToken());

        return initStones;
    }

    static boolean judgeIsPossible(int[] initStones) {
        int sum = 0; // 돌 개수 총합
        for (int i = 0; i < 3; i++) sum += initStones[i];
        if (sum % 3 != 0) return false; // 3으로 안나누어떨어지면 불가능

        Queue<int[]> q = new ArrayDeque<>(); // 너비 우선 탐색을 통해 돌 그룹 나누어가며 탐색할 것이며, 그에 쓰일 큐
        boolean[][] isChecked = new boolean[1_501][1_501]; // A, B, C 개수에 따라 체크했던 경우인 지 저장하는 배열
        q.offer(initStones);
        isChecked[initStones[0]][initStones[1]] = true;

        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();
            if (now[0] == now[1] && now[1] == now[2]) return true; // 세 돌의 개수가 같으면 참 반환

            int[] next;
            for (int i = 0; i < 3; i++) { // 각 그룹을 뽑으며 개수가 차이나는 그룹에서 돌 이동 수행
                for (int j = i+1; j < 3; j++) {
                    next = Arrays.copyOf(now, now.length);
                    int nextI, nextJ;
                    if (next[i] > next[j]) {
                        nextI = next[i] - next[j]; // 큰쪽 돌
                        nextJ = next[j] + next[j]; // 갱신할 작은쪽 돌 개수
                    } else if (next[i] < next[j]) {
                        nextI = next[i] + next[i]; // 갱신할 작은쪽 돌 개수
                        nextJ = next[j] - next[i]; // 큰쪽 돌
                    } else continue; // 개수 같은 주머니는 건너 뜀
                    next[i] = nextI;
                    next[j] = nextJ;
                    if (isChecked[nextI][nextJ]) continue; // 이미 체크해봤던 조합이면 건너 뜀

                    q.offer(next);
                    isChecked[nextI][nextJ] = true;
                }
            }
        }


        return false;
    }

}
