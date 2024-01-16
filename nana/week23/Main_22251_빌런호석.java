import java.util.Scanner;

public class Main {
    // N층까지 이용 가능
    // K 자리의 수
    // 최대 P 개 반전
    // 실제 X층
    private static int N, K, P, X;
    private static int[][] floor = {
            {1, 1, 1, 0, 1, 1, 1},
            {0, 0, 1, 0, 0, 1, 0},
            {1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 1},
            {0, 1, 1, 1, 0, 1, 0},
            {1, 1, 0, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 1, 0},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1}
    };

    private static String makeNum(int num) {
        String res = "";

        for (int i = 0; i < K - String.valueOf(num).length(); i++) {
            res += "0";
        }

        return res + String.valueOf(num);
    }

    private static boolean countFlip(String standard, String num) {
        int count = 0;

        // 총 K 자리의 수 동안 다른 부분이 있다면 count++
        for (int i = 0; i < K; i++) {
            int s = standard.charAt(i) - '0';
            int n = num.charAt(i) - '0';

            for (int j = 0; j < 7; j++) {
                if (floor[s][j] != floor[n][j]) {
                    count++;
                }
            }
        }

        if (count >= 1 && count <= P) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        P = sc.nextInt();
        X = sc.nextInt();

        int res = 0;

        // 3자리 수를 나타내는 곳에 2자리 숫자가 들어가면 제일 앞에 0으로 채워야 한다.
        String standard = makeNum(X);
        // System.out.println(standard);

        // 모든 층 중에 X층 제외 P개 이하로 반전되는 층을 모두 세면 된다
        for (int i = 1; i <= N; i++) {
            String num = makeNum(i);

            if (countFlip(standard, num)) {
                // System.out.println(num);
                res++;
            }

        }

        // System.out.println("standard: " + standard);

        System.out.println(res);
    }
}
