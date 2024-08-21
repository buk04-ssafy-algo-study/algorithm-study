package SSAFY.study.algo.week37;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드4_10282_해킹 {

    static class Computer implements Comparable<Computer> {
        int no; // 컴퓨터 번호
        int depends;    // 의존하는 컴퓨터
        int seconds;    // 몇 초 후 감염되는지

        public Computer(int no, int depends, int seconds) {
            this.no = no;
            this.depends = depends;
            this.seconds = seconds;
        }

        @Override
        public int compareTo(Computer c) {
            return this.seconds - c.seconds;
        }
    }

    static List<List<Computer>> computerList;   // 컴퓨터 클래스를 타입으로 하는 연결리스트

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int n, d, c;    // n: 컴퓨터 개수, d: 의존성 개수, c: 감염당한 컴퓨터
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            int a, b, s;
            computerList = new ArrayList<>();
            for (int i = 0; i < n+1; i++) computerList.add(new ArrayList<>());
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                s = Integer.parseInt(st.nextToken());
                computerList.get(b).add(new Computer(a, b, s));
            }
            int[] ans = calcAffection(n, c);
            sb.append(ans[0]).append(" ").append(ans[1]).append("\n");
        }
        System.out.println(sb);
    }

    static int[] calcAffection(int n, int c) {  // 감염된 컴퓨터 수와 걸린 시간을 반환하는 함수
        int[] ans = new int[2];
        ans[0] = 1; // 최초 감염된 컴퓨터는 미리 카운트
        int[] cost = new int[n+1];  // 각 컴퓨터까지 감염을 시키는 데 걸리는 시간의 최소값 저장할 배열
        for (int i = 0; i < n+1; i++) if (i != c) cost[i] = Integer.MAX_VALUE;  // 최소값 저장 배열 초기화
        PriorityQueue<Computer> pq = new PriorityQueue<>();
        for (int i = 0; i < computerList.get(c).size(); i++) {
            Computer nextC = computerList.get(c).get(i);    // 최초 감염 컴퓨터가 영향을 미칠 수 있는 컴퓨터
            cost[nextC.no] = Math.min(cost[nextC.no], nextC.seconds);
            pq.offer(nextC);
        }

        while (!pq.isEmpty()) {
            Computer now = pq.poll();
            int nowNo = now.no;
            List<Computer> nowAffectsList = computerList.get(nowNo);    // 현재 컴퓨터가 영향을 끼칠 수 있는 컴퓨터들의 리스트
            for (int i = 0; i < nowAffectsList.size(); i++) {
                Computer next = nowAffectsList.get(i);
                if (cost[next.no] > cost[nowNo] + next.seconds) {   // 감염시키는 데 걸리는 시간이 더 짧으면 최소값 갱신 후 pq에 삽입
                    cost[next.no] = cost[nowNo] + next.seconds;
                    pq.offer(next);
                }
            }
        }

        for (int i = 0 ; i < n+1; i++) {    // 갱신된 비용 배열 돌면서 정답 저장
            if (i == c || cost[i] == Integer.MAX_VALUE) continue;
            ans[0] += 1;
            if (ans[1] < cost[i]) ans[1] = cost[i];
        }

        return ans;
    }

}
