package SSAFY.study.algo.week32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_골드4_10830_행렬제곱 {

    static int N;
    static long B;
    static int[][] originMatrix;
    static final int MOD = 1_000;

    public static void main(String[] args) throws IOException {
        init();
        StringBuilder sb = new StringBuilder();
        int[][] answer = doPow(originMatrix, B);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(answer[i][j] % MOD).append(" ");  // B가 1인 경우 때문에 정답 출력 시에도 1000으로 나누어 주어야...
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void init () throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        originMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                originMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static int[][] multiplyMatrix(int[][] inputMatrix1, int[][] inputMatrix2) { // 행렬 곱 -> aij = (bi1 * c1j) + (bi2 * c2j) + (bi3 * c3j) + ... + (bik * ckj)
        int[][] retMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    retMatrix[i][j] += inputMatrix1[i][k] * inputMatrix2[k][j];
                    retMatrix[i][j] %= MOD;
                }
            }
        }

        return retMatrix;
    }

    static int[][] doPow(int[][] matrix, long powerNumber) {    // 분할 정복을 이용한 거듭 제곱 그 자체(숫자에서 행렬로 바뀐 것 뿐)
        if (powerNumber == 1) return matrix;

        int[][] temp = doPow(matrix, powerNumber/2);

        temp = multiplyMatrix(temp, temp);
        if (powerNumber % 2 == 0) return temp;
        else return multiplyMatrix(temp, matrix);
    }

}
