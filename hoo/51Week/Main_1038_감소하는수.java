package SSAFY.study.algo.week51;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1038_감소하는수 {

    static PriorityQueue<Long> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        for (int i = 0; i <= 9; i++) {
            combination(0, 0, i+1, new boolean[10]);
        }
        while (N > 0) {
            pq.poll();
            N--;
        }
        if (pq.isEmpty()) System.out.println(-1);
        else System.out.println(pq.poll());
    }

    static void combination(int start, int count, int need, boolean isVisited[]) {
        if (count == need) {    // 원하는 개수만큼 뽑았으면
            String temp = "";
            for (int i = isVisited.length - 1; i >= 0; i--) if (isVisited[i]) temp += i;
            pq.offer(Long.parseLong(temp));
        }

        for (int i = start; i <= 9; i++) {
            isVisited[i] = true;
            combination(i+1, count+1, need, isVisited);
            isVisited[i] = false;
        }
    }

}
