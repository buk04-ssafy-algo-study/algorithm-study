package SSAFY.study.algo.week60s.week65;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_17952_과제는끝나지않아 {

    static class Subject {
        int type;
        int score;  // 과제의 만점
        int needTime;   // 과제 수행에 걸리는 시간

        public Subject(int type, int score, int needTime) {
            this.type = type;
            this.score = score;
            this.needTime = needTime;
        }

        @Override
        public String toString() { return this.type + " " + this.score + " " + this.needTime; }
    }

    static int N;
    static List<Subject> subjectList;

    public static void main(String[] args) throws IOException {
        init();
        calcScores();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        subjectList = new ArrayList<>();    // N개의 과제를 저장하는 리스트
        StringTokenizer st;
        int type;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            type = Integer.parseInt(st.nextToken());
            if (type == 0) subjectList.add(new Subject(0, 0, 0));
            else if (type == 1) subjectList.add(new Subject(type, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    static void calcScores() {
        int score = 0;  // 성애가 과제 수행을 통해 얻은 점수
        Stack<Subject> stack = new Stack<>();   // 진행 중인 과제를 담을 스택

        int index = 0;
        Subject nowSubject = new Subject(0, 0, 0);  // 현재 진행 중인 과제
        while (N >= 0 && index < subjectList.size()) {
            if (subjectList.get(index).type != 0) { // 이번 분에 받은 과제 타입이 1이면 이 과제를 수행해야 함
                if (index != 0 && nowSubject.needTime != 0) stack.push(nowSubject); // 제일 첫 과제 제외하고는 진행 중인 과제 스택에 보관
                nowSubject = subjectList.get(index++);
            } else {    // 이번 분에 받은 과제의 타입이 0이라면 진행 중이던 과제 그대로 진행
                if (nowSubject.needTime == 0 && !stack.isEmpty()) nowSubject = stack.pop(); // 그 와중에 현재 진행 중인 과제가 끝난 상태라면 이전에 수행 중이던 과제 가져와서 수행
                index++;
            }

            nowSubject.needTime--;
            if (nowSubject.needTime == 0) score += nowSubject.score;    // 해당 과제를 모두 끝냈다면 점수얻음
            N--;    // 남은 시간 감소 처리
        }

        System.out.println(score);
    }

}
