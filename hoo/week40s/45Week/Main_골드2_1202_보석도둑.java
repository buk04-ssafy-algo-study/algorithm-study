package SSAFY.study.algo.week45;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_골드2_1202_보석도둑 {

    static class Jewel implements Comparable<Jewel> {
        int weight;
        int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel j1) {
            if (this.weight == j1.weight) return j1.value - this.value; // 무게가 같은 경우 가치에 대해 내림차순 정렬(가치가 높은 걸 더 먼저 담아야 하므로)

            return this.weight - j1.weight; // 기본적으로는 무게에 대해 오름차순 정렬
        }

        @Override
        public String toString() { return this.weight + " " + this.value; }
    }

    static int N, K;
    static PriorityQueue<Jewel> pq;
    static int[] capacities;

    public static void main(String[] args) throws IOException {
        init();
        doSteal();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        capacities = new int[K];
        for (int i = 0; i < K; i++) capacities[i] = Integer.parseInt(br.readLine());
        Arrays.sort(capacities);    // 보석들을 무게에 대해 오름차순 정렬했음. 가방 당 한 개의 보석만 담을 수 있고 보석의 가치가 가장 높도록 담는 게 목표임. 우선 순위 큐 정렬 순서에 따라 가방도 용량(무게) 기준으로 정렬해주자.
    }

    static void doSteal() {
        long answer = 0;
        int capacity, tempMax;  // tempMax = 각 가방 별 담을 수 있는 최대 보석의 무게
        PriorityQueue<Integer> valuePq = new PriorityQueue<>(Comparator.reverseOrder()); // 현재 가방 무게 기준으로 담을 수 있는 보석 들의, "가치"를 우선 순위(가치 내림차순)로 하는 큐
        for (int i = 0; i < K; i++) {   // 가방에 대해서 수행
            capacity = capacities[i];
            while (!pq.isEmpty() && pq.peek().weight <= capacity) valuePq.offer(pq.poll().value);

            if (!valuePq.isEmpty()) answer += valuePq.poll();
        }
        System.out.println(answer);
    }

}
