package SSAFY.study.algo.week42;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_골드4_1715_카드정렬하기 {

    static int N;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        init();
        if (N == 1) System.out.println(0);
        else sortCard();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
    }

    static void sortCard() {
        int answer = 0;
        while (!pq.isEmpty()) {
            int temp = pq.poll();
            if (!pq.isEmpty()) temp += pq.poll();
            if (!pq.isEmpty()) pq.offer(temp);
            answer += temp;
        }
        System.out.println(answer);
    }

}
