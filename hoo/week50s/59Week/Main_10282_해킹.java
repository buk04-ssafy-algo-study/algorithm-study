package SSAFY.study.algo.week50s.week59;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_10282_해킹 {

    static class Computer implements Comparable<Computer> {
        int from;
        int to;
        int time;

        public Computer(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Computer c) {
            return Integer.compare(this.time, c.time);
        }
    }

    static int n, d, c;
    static List<List<Computer>> computerList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int[] answer;
        for (int t = 0; t < T; t++) {
            init(br);
            answer = doHack(c);
            sb.append(answer[0] + " " + answer[1]).append("\n");
        }
        System.out.println(sb);
    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        computerList = new ArrayList<>();
        for (int i = 0; i < n+1; i++) computerList.add(new ArrayList<>());
        int a, b, s;
        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());   // a가 b를 의존하는 거임
            b = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            computerList.get(b).add(new Computer(b, a, s));
        }
    }

    static int[] doHack(int startComputer) {
        int[] time = new int[n+1];
        int INF_TIME = n*1_000 + 1;
        Arrays.fill(time, INF_TIME);
        time[startComputer] = 0;
        PriorityQueue<Computer> pq = new PriorityQueue<>();
        for (int i = 0; i < computerList.get(startComputer).size(); i++) {
            pq.offer(computerList.get(startComputer).get(i));
            time[computerList.get(startComputer).get(i).to] = computerList.get(startComputer).get(i).time;
        }

        Computer now;
        List<Computer> nextComputerList;
        while (!pq.isEmpty()) {
            now = pq.poll();

            nextComputerList = computerList.get(now.to);
            for (Computer next : nextComputerList) {
                if (time[next.to] > time[next.from] + next.time) {
                    time[next.to] = time[next.from] + next.time;
                    pq.offer(next);
                }
            }
        }
        int[] answer = new int[2];  // 0: 총 감염 컴퓨터 수, 마지막 컴퓨터 감염까지 걸리는 시간
        answer[0] = 1;  // 자기 자신도 포함해야 하므로 1부터 시작
        for (int i = 1; i <= n; i++) {
            if (i == startComputer || time[i] == INF_TIME) continue;    // 자기 자신이나 감염시킬 수 없는 컴퓨터는 건너 뜀
            answer[0]++;
            answer[1] = Math.max(answer[1], time[i]);
        }

        return answer;
    }

}
