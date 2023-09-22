package SSAFY.study.algo.week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_실버3_9095_123더하기 {

    private static int N;   // N : 1 ~ 11

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            if (N == 1 || N == 2 || N == 3) {   // N이 1, 2, 3일 때는 점화식으로 일반화가 불가
                if (N == 1) sb.append(1);
                else if (N == 2) sb.append(2);
                else if (N == 3) sb.append(4);
                sb.append("\n");
                continue;
            }

            int answer = calcCase(N);
            sb.append(answer);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int calcCase(int N) {
        int[] count = new int[N+1];   // 나타낼 수 있는 경우의 수, 숫자 안 헷갈리기 위해 1부터 값 할당
        count[1] = 1;   // 1
        count[2] = 2;   // 2, (1, 1)
        count[3] = 4;   // 3, (2, 1), (1, 2), (1, 1, 1)
        for (int i = 4; i < N+1; i++) { // 4부터의 경우의 수 : 이전 세 수 경우의 수들의 합
            count[i] = count[i-1] + count[i-2] + count[i-3];
        }

        return count[N];
    }

}
