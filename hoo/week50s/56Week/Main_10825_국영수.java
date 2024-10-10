package SSAFY.study.algo.week50s.week56;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_10825_국영수 {

    static class Grade implements Comparable<Grade> {
        String name;
        int korean;
        int english;
        int math;

        public Grade(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(Grade g) {
            if (this.korean == g.korean) {  // 국어 점수가 같다면
                if (this.english == g.english) {    // 그 와중에 영어 점수도 같다면
                    if (this.math == g.math) {  // 그 와중에 수학 점수도 같다면
                        return this.name.compareTo(g.name); // 이름 사전순 정렬(String 객체의 compareTo 이용)
                    }

                    return g.math - this.math;  // 수학 점수 내림차순
                }

                return this.english - g.english;    // 영어 점수 오름차순
            }

            return g.korean - this.korean;  // 국어 점수 내림차순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Grade> pq = new PriorityQueue<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Grade(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(pq.poll().name).append("\n");
        System.out.println(sb);
    }

}
