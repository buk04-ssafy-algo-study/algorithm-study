package SSAFY.study.algo.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_실버2_28066_타노스는요세푸스가밉다 {

    static int N, K;
    static Queue<Integer> squirrels;

    public static void main(String[] args) throws IOException {
        init();
        while (squirrels.size() >= K) {
            thanos();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(squirrels.poll());
        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        squirrels = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) { // 청설모 초기화
            squirrels.offer(i);
        }
    }

    static void thanos() {
        squirrels.offer(squirrels.poll());

        for (int i = 0; i < K-1; i++) {
            if (squirrels.isEmpty()) break;
            squirrels.poll();
        }
    }

}
