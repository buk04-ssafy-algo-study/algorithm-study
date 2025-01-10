import java.util.*;

class Main {

    private static int N, K;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};  // 상하우좌
    private static long[] start;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        String order = sc.next();

        start = new long[2 * N];    // 대각선 시작 숫자

        start[0] = 1;

        int index = 1;

        for (int i = 1; i < N; i++) {
            start[i] = start[i - 1] + index++;
        }

        for (int i = N; i < 2 * N; i++) {
            start[i] = start[i - 1] + index--;
        }

        long res = 1;
        int x = 0, y = 0;   // 현재 위치 저장

        for (int i = 0; i < K; i++) {

            char dir = order.charAt(i);

            // 상 하 우 좌
            switch (dir) {
                case 'U':
                    x += delta[0][0];
                    y += delta[0][1];
                    break;
                case 'D':
                    x += delta[1][0];
                    y += delta[1][1];
                    break;
                case 'R':
                    x += delta[2][0];
                    y += delta[2][1];
                    break;
                case 'L':
                    x += delta[3][0];
                    y += delta[3][1];
                    break;
            }

            // 행+열 == 대각선 순번
            long point = start[x + y];

            if ((x + y) % 2 == 0) {
                if (x + y < N) {
                    res += point + y;
                } else {
                    res += point + Math.abs(N - x - 1);
                }
            } else {
                if (x + y < N) {
                    res += point + x;
                } else {
                    res += point + Math.abs(N - y - 1);
                }
            }

        }

        System.out.println(res);
    }
}
