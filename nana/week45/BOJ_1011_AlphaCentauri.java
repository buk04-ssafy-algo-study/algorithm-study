// https://www.acmicpc.net/problem/1011
// Fly me to the Alpha Centauri

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {

            int x = sc.nextInt();
            int y = sc.nextInt();

            int d = y - x;  // 이동해야 하는 칸의 수
            int sqrt = (int) Math.sqrt(d);

            // 제곱수인 경우 2√d - 1
            // 제곱수와 제곱수 사이에 √d를 더한 값과 아닌 값은 1 차이남
            // 제곱수는 1, 3, 5, 7, 9 ... 순으로 count가 올라간다
            if (d == sqrt * sqrt)
                sb.append(2 * sqrt - 1).append("\n");
            else if (d <= sqrt * sqrt + sqrt) {
                sb.append(2 * sqrt).append("\n");
            } else {
                sb.append(2 * sqrt + 1).append("\n");
            }
        }

        System.out.println(sb);
    }
}
