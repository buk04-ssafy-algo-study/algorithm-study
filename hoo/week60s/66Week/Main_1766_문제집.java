package SSAFY.study.algo.week60s.week66;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1766_문제집 {

    static class Problem implements Comparable<Problem> {
        int problemNumber;
        int prior;

        public Problem(int problemNumber, int prior) {
            this.problemNumber = problemNumber;
            this.prior = prior;
        }

        @Override
        public int compareTo(Problem p) {
            return this.problemNumber - p.problemNumber;    // 문제의 난이도가 번호 순이므로, 번호 기준 오름차순 정렬
        }

        @Override
        public String toString() { return this.problemNumber + " " + this.prior; }
    }

    static List<List<Problem>> problemList; // 해당 문제를 우선순위로 하는 문제들을 담은 리스트를 저장하고 있는 리스트

    public static void main(String[] args) throws IOException {
        int[] inCount = init(); // 문제들 리스트, 진입 차수 초기화
        judgeOrder(inCount);
    }

    static int[] init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 문제 수
        int M = Integer.parseInt(st.nextToken());   // 주어지는 우선 순위 수
        problemList = new ArrayList<>();
        for (int i = 0; i <= N; i++) problemList.add(new ArrayList<>());

        int[] inCount = new int[N+1];   // 각 문제 별 이전에 수행돼야하는 문제 수(진입 차수) 카운트
        int prior, problemNo;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            prior = Integer.parseInt(st.nextToken());   // 이번 문제 이전에 풀면 좋은 문제
            problemNo = Integer.parseInt(st.nextToken());

            problemList.get(prior).add(new Problem(problemNo, prior));
            inCount[problemNo]++;
        }

        return inCount;
    }

    static void judgeOrder(int[] inCount) {
        PriorityQueue<Problem> pq = new PriorityQueue<>();
        for (int i = 1; i < inCount.length; i++) {
            if (inCount[i] == 0) pq.offer(new Problem(i, 0));   // 우선 풀어줄 문제가 없는 문제들을 pq에 담아줌. 이들의 prior는 의미 없다는 의미에서 0으로 해줌.
        }

        int[] judgedOrder = new int[inCount.length-1];    // 순서가 결정된 문제 번호를 저장할 배열
        int judgedOrderIndex = 0;

        Problem now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            judgedOrder[judgedOrderIndex++] = now.problemNumber;  // 현재 문제의 우선 순위 문제들을 모두 해결한 문제, 순서에 적용해줌

            List<Problem> nextProblemList = problemList.get(now.problemNumber); // 현재 문제를 우선 순위로 둔 문제들 리스트
            for (Problem nextProblem : nextProblemList) {
                inCount[nextProblem.problemNumber]--;   // 우선순위 문제 개수 하나 감소처리
                if (inCount[nextProblem.problemNumber] == 0) pq.offer(nextProblem); // 모든 우선 순위 문제 처리한 문제는 우선 순위 큐에 삽입
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < judgedOrder.length; i++) sb.append(judgedOrder[i]).append(" ");
        System.out.println(sb);
    }

}
