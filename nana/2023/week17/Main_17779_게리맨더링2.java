import java.util.*;
import java.io.*;

public class Main_17779_게리맨더링2 {

    private static int N, result, total;
    private static int[][] city;
    private static int[] population;

    private static void calc(int x, int y, int d1, int d2) {
        boolean[][] border = new boolean[N][N];
        population = new int[5];

        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }

        // 1구역
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (border[i][j]) break;
                population[0] += city[i][j];
            }
        }

        // 2구역
        for (int i = 0; i <= x + d2; i++) {
            for (int j = N - 1; j > y; j--) {
                if (border[i][j]) break;
                population[1] += city[i][j];
            }
        }

        // 3구역
        for (int i = x + d1; i < N; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (border[i][j]) break;
                population[2] += city[i][j];
            }
        }

        // 4구역
        for (int i = x + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= y - d1 + d2; j--) {
                if (border[i][j]) break;
                population[3] += city[i][j];
            }
        }

        // 5구역
        population[4] = total;

        for (int i = 0; i < 4; i++) {
            population[4] -= population[i];
        }

        Arrays.sort(population);

        result = Math.min(result, population[4] - population[0]);

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        city = new int[N][N];
        result = Integer.MAX_VALUE;
        total = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                total += city[i][j];
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x + d1 + d2 >= N) continue;
                        if (y - d1 < 0 || y + d2 >= N) continue;

                        calc(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(result);

    }
}