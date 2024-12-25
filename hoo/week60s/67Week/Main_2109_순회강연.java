package SSAFY.study.algo.week60s.week67;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2109_순회강연 {

    static class Lecture implements Comparable<Lecture> {
        int pay;
        int due;

        public Lecture (int pay, int due) {
            this.pay = pay;
            this.due = due;
        }

        @Override
        public int compareTo(Lecture l) {
//            if (this.due == l.due) return l.pay-this.pay;   // 유효기간 같은 강의끼리는 pay 내림차순
//            return this.due - l.due;    // 유효기간 오름차순
            if (this.pay == l.pay) return this.due - l.due;
            return l.pay - this.pay;
        }
    }

    public static void main(String[] args) throws IOException {
        PriorityQueue<Lecture> pq = init();
        calcGain(pq);
    }

    static PriorityQueue<Lecture> init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Lecture> pq = new PriorityQueue<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        return pq;
    }

    static void calcGain(PriorityQueue<Lecture> pq) {
        int gain = 0;
        boolean[] isSelected = new boolean[10_001];    // 해당 날짜에 강의가 이미 선택됐는 지 여부 저장할 배열

        Lecture now;
        while (!pq.isEmpty()) {
            now = pq.poll();
            for (int i = now.due; i >= 1; i--) {    // 해당 강의를 끼워넣을 수 있는 날이 있는 지 여부 탐색
                if (!isSelected[i]) {   // 끼워넣을 수 있는 날짜 있으면 거기다가 해당 강의 할당
                    isSelected[i] = true;
                    gain += now.pay;
                    break;
                }
            }
        }

        System.out.println(gain);
    }

}
