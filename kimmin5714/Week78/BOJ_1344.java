import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1344 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double A = Integer.parseInt(br.readLine()) / 100.0;
        double B = Integer.parseInt(br.readLine()) / 100.0;
        double resA = 0.0, resB = 0.0;

        for (int i = 0; i <= 90 / 5; i++) {
            if (!isPrime(i)) { // 소수가 아닌 점수

                // A가 i번 이길 확률
                resA += combination(18, i) * Math.pow(A, i) * Math.pow(1 - A, 18 - i);

                // B가 i번 이길 확률
                resB += combination(18, i) * Math.pow(B, i) * Math.pow(1 - B, 18 - i);
            }
        }

        // 적어도 한 팀이 소수로 득점 = 1 - (두 팀 모두 소수로 득점하지 않을 확률)
        System.out.println(1 - (resA * resB));
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private static double combination(int n, int r) {
        // n! / (n-r)!r!
        return factorial(n) / (factorial(n - r) * factorial(r));
    }

    private static double factorial(int n) {
        double result = 1.0;
        for (int i = n; i >= 1; i--) {
            result *= i;
        }
        return result;
    }
}
