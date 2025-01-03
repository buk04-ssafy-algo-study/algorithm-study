package study.week21;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_1744_수묶기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        PriorityQueue<Integer> positive = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> negative = new PriorityQueue<>();

        int zero = 0;

        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            if (num > 0) {
                positive.add(num);
            } else if (num < 0) {
                negative.add(num);
            } else {
                zero++;
            }
        }

        int result = 0;

        while (negative.size() > 1) {
            result += negative.poll() * negative.poll();
        }

        while (positive.size() > 1) {
            int a = positive.poll();
            int b = positive.poll();

            // 곱하는 수가 1인 경우 곱하는 것보다 더하는 것이 더 큰 수
            if (a == 1 || b == 1) result += a + b;
            else result += a * b;
        }

        if (negative.isEmpty()) {
            if (!positive.isEmpty()) {
                result += positive.poll();
            }
        } else { // 음수가 존재할 때
            if (!positive.isEmpty()) {
                if (zero == 0) {
                    result += negative.poll() + positive.poll();
                } else { // 0이 존재한다면 음수*0 이기 때문에 더하는 수 없음
                    result += positive.poll();
                }
            } else {
                if (zero == 0)
                    result += negative.poll();
            }
        }

        System.out.println(result);
    }
}
