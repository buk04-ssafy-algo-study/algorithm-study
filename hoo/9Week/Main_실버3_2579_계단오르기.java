package SSAFY.study.algo.week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_실버3_2579_계단오르기 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // N: 1~300
        int[] stairs = new int[N];
        for (int i = 0; i < N; i++) stairs[i] = Integer.parseInt(br.readLine());    // 계단 초기화
        if (N == 1 || N == 2) { // N이 1, 2일 경우 점화식으로 일반화 불가, 연속 3개를 밟을 경우가 없으므로 계단 다 밟은 값이 정답
            printResult(N, stairs);
            return;
        } else if (N == 3) {    // N이 3일 때도 점화식으로 일반화가 불가.
            System.out.println(Math.max(stairs[0] + stairs[1], stairs[1] + stairs[2]));
            return;
        }

        int[] bestStep = new int[N];   // 각 계단에서의 최대 점수 기록할 배열
        bestStep[0] = stairs[0];    // 1번 계단까지의 최대 점수는 1번을 밟았을 때
        bestStep[1] = bestStep[0] + stairs[1];  // 2번 계단까지의 최대 점수는 1, 2번을 모두 밟았을 때
        bestStep[2] = Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]);   // 3번 계단까지의 최대 점수는 1을 밟고 3을 밟던지, 2를 밟고 3을 밟던지
        for (int i = 3; i < N; i++) {   // 3번 계단부터 최대 점수를 따져 봄
            bestStep[i] = Math.max(bestStep[i-2]+stairs[i], bestStep[i-3] + stairs[i-1] + stairs[i]);   // N번 계단까지의 최대 점수는 2번째 전까지의 최대값+N번 계단 or 3번째 전까지의 최대값+직전계단+N번 계단
        }
        System.out.println(bestStep[N-1]);
    }

    private static void printResult(int N, int[] stairs) {
        int result = 0;
        for (int i = 0; i < N; i++) result += stairs[i];
        System.out.println(result);
    }

}
