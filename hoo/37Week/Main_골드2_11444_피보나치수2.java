package SSAFY.study.algo.week37;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_골드2_11444_피보나치수2 {

    static long MOD = 1_000_000_007;
    static long[][] fibMatrix = {{1, 1}, {1, 0}};   // 피보나치 수열을 행렬 곱으로 일반화 할 수 있는 배열(fibMatrix^n = {{fib(n+1), fib(n))}, {fib(n), fib(n-1)}};
    static long n;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
        if (n == 1 || n ==2) System.out.println(1);
        else System.out.println(fibonacci(fibMatrix, n)[0][1]);
    }

    static long[][] matrixMultiply(long[][] m1, long[][] m2) {
        long[][] result = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k =0; k < 2; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                    result[i][j] %= MOD;
                }
            }
        }

        return result;
    }

    static long[][] fibonacci(long[][] mat, long powNumber) {
        if (powNumber == 1) return mat;

        long[][] temp = fibonacci(mat, powNumber/2);

        temp = matrixMultiply(temp, temp);
        if (powNumber%2 == 0) return temp;
        else return matrixMultiply(temp, mat);
    }

}
