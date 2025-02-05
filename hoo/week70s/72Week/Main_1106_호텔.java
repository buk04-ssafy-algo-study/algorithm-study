package SSAFY.study.algo.week70s.week72;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1106_호텔 {

    static class CostAndCustomer implements Comparable<CostAndCustomer> {
        int cost;   // 지금까지 쓴 돈
        int customer;   // 지금까지 유치한 고객 수

        public CostAndCustomer(int cost, int customer) {
            this.cost = cost;
            this.customer = customer;
        }

        @Override
        public int compareTo(CostAndCustomer c) {
            if (c.customer == this.customer) return this.cost - c.cost;  // 유치한 고객 수가 같다면 비용 낮은 순 정렬

            return c.customer - this.customer;  // 유치한 고객 기준 내림차순 정렬
        }

        @Override
        public String toString() { return this.cost + " " + this.customer; }
    }

    static int C, N;
    static int minCost;
    static int[] isChecked; // 해당 고객 수에 대한 비용이 이미 체크됐는 지 여부 저장
    static List<CostAndCustomer> costAndCustomerList;
    static PriorityQueue<CostAndCustomer> costAndCustomersPq;

    public static void main(String[] args) throws IOException {
        init();
        calcMinCostToGetCustomer();
        System.out.println(minCost);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        minCost = Integer.MAX_VALUE;

        isChecked = new int[1_101]; // 왜 1100이냐? => 1000으로 하면 1원에 3명 가능할 때 딱 999명 채운 다음에 1002명을 채우는 경우를 고려 못해줌
        Arrays.fill(isChecked, Integer.MAX_VALUE);
        costAndCustomerList = new ArrayList<>();
        costAndCustomersPq = new PriorityQueue<>();
        int cost, customer;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost = Integer.parseInt(st.nextToken());
            customer = Integer.parseInt(st.nextToken());
            if (isChecked[customer] <= cost) continue;   // 이미 더 저렴한 금액에 고객 유치했으면 건너 뜀

            isChecked[customer] = cost;
            costAndCustomerList.add(new CostAndCustomer(cost, customer));
            costAndCustomersPq.offer(costAndCustomerList.get(costAndCustomerList.size()-1));
            if (customer >= C) minCost = Math.min(minCost, cost);
        }
        Collections.sort(costAndCustomerList);  // 기준에 따라 정렬
    }

    static void calcMinCostToGetCustomer() {
        CostAndCustomer now;
        while (!costAndCustomersPq.isEmpty()) {
            now = costAndCustomersPq.poll();

            for (CostAndCustomer c : costAndCustomerList) {    // 각 호텔 별 고객과 비용에 대해
                int multiplier = 1; // 배수 1부터 곱해가며 고객 수 초과 안할 때까지 삽입
                int nextCustomer, nextCost;
                while (true) {
                    nextCustomer = now.customer + (c.customer * multiplier);
                    nextCost = now.cost + (c.cost * multiplier);
                    if (nextCustomer > 1_100) break;    // 구하고자 하는 고객 수 넘었으면 멈춤
                    if (isChecked[nextCustomer] <= nextCost) {   // 이미 더 저렴한 비용으로 처리 가능하면 건너 뜀
                        multiplier++;
                        continue;
                    }
                    isChecked[nextCustomer] = nextCost;
                    costAndCustomersPq.offer(new CostAndCustomer(nextCost, nextCustomer));

                    if (nextCustomer >= C) minCost = Math.min(minCost, nextCost);
                    multiplier++;   // 배수해줄 숫자 +1
                }
            }
        }
    }

}
