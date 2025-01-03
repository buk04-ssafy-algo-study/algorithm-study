// https://www.acmicpc.net/problem/14719
// 빗물

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int H = sc.nextInt();
        int W = sc.nextInt();

        int[] water = new int[W];

        for (int i = 0; i < W; i++) {
            water[i] = sc.nextInt();
        }

        int sum = 0;

        for (int i = 1; i < W - 1; i++) {

            // 매번 초기화 시키기
            int leftMax = 0;
            int rightMax = 0;

            int now = water[i];

            for (int left = 0; left < i; left++) {
                leftMax = Integer.max(leftMax, water[left]);
            }

            for (int right = i + 1; right < W; right++) {
                rightMax = Integer.max(rightMax, water[right]);
            }

            if (leftMax > now && rightMax > now) {
                // 둘 중 더 작은 높이를 기준으로 현재 값을 빼줌 = 빗물이 고이는 양
                sum += Integer.min(leftMax, rightMax) - now;
            }
        }

        System.out.println(sum);

    }
}
