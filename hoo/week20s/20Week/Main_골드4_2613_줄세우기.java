package SSAFY.study.algo.week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_골드4_2613_줄세우기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] children = new int[N];
        int[] lis = new int[N];
        for (int i = 0; i < N; i++) children[i] = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (children[j] < children[i]) lis[i] = Math.max(lis[i], lis[j] + 1);
            }
        }
        Arrays.sort(lis);
        System.out.println(N - lis[N-1]);
    }

}
