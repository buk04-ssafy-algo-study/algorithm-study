package SSAFY.study.algo.week60s.week62;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1398_동전문제 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            long price = Long.parseLong(br.readLine());    // 초콜릿의 가격
            sb.append(calcCoinCount(price)).append("\n");
        }
        System.out.println(sb);
    }

    static int calcCoinCount(long price) {
        int[] needCoins = makeNeedCoins();    // 1 ~ 99원까지의 필요 동전 최소 개수를 저장하고 있는 배열
        int coinCount = 0;
        while (price != 0) {    // 동전의 가치는 100의 거듭제곱을 규칙으로 가지고 있음. => 100의 거듭제곱이 규칙이므로 가격 역시 그 범위로 나누어 각각의 범위에 대해 판단 가능
            coinCount += needCoins[(int) (price % 100)];    // 현재 가격에 100의 거듭제곱을 나눈 나머지에 사용할 동전 카운트
            price /= 100;   // 다음 거듭 100의 거듭 제곱 단위 파악 위해 100으로 나누어줌
        }

        return coinCount;
    }

    static int[] makeNeedCoins() {  // 1~99원까지 1, 10, 25원짜리 동전을 최소 개수로 사용하여 만들 수 있는 경우의 수 계산
        int[] needCoins = new int[100];
        for (int i = 0; i < 100; i++) {
            if (i < 10) needCoins[i] = i;
            else if (i >= 10 && i < 25) needCoins[i] = Math.min(needCoins[i-10], needCoins[i-1]) + 1;   // 10원짜리 쓰는 것과 1원짜리 쓰는 것 비교
            else if (i >= 25) {
                needCoins[i] = Math.min(needCoins[i-25], needCoins[i-10]) + 1;  // 먼저 25원짜리 하나 쓰는 것과 10원짜리 쓰는 것을 비교
                needCoins[i] = Math.min(needCoins[i], needCoins[i-1]+1);    // 그 후 1원짜리 쓰는 것과 비교
            }

        }

        return needCoins;
    }

}
