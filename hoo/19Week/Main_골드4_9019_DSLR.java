package SSAFY.study.algo.week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드4_9019_DSLR {

    static int A;
    static int B;
    static int minCount;    // 최소 횟수 저장할 변수
    static String command;  // 최소 횟수에서의 명령어
    static boolean[] isVisited; // 이미 만들었던 숫자인지 체크

    static class DSLR {
        int number; // 그 시점의 숫자
        int count;  // 횟수
        String command;   // 쓰인 명령어들

        public DSLR(int number, int count, String command) {
            this.number = number;
            this.count = count;
            this.command = command;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            DSLR initDSLR = init(br);
            bfs(initDSLR);
            sb.append(command);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static DSLR init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        minCount = Integer.MAX_VALUE;
        command = "";
        isVisited = new boolean[10001];
        DSLR initDSLR = new DSLR(A, 0, "");

        return initDSLR;
    }

    /*static int rotateA(int a, String command) {   // 여기서 시간을 엄청 잡아먹는 듯..?
        String aString = String.valueOf(a);
        if (a < 1000 && a >= 100) aString = "0" + aString;  // 회전 전 자릿수에 따른 0 추가 로직
        else if (a < 100 && a >= 10) aString = "00" + aString;
        else if (a < 10 && a >= 0) aString = "000" + aString;
        Deque<String> deque = new ArrayDeque<>();   // 우측 회전, 좌측 회전을 처리하기 위해 덱을 이용
        for (int i = 0; i < aString.length(); i++) {    // 회전을 위해 덱에 저장
            deque.offer(String.valueOf(aString.charAt(i)));
        }

        if (command.equals("L")) {
            deque.offer(deque.poll());
        } else if (command.equals("R")) {
            deque.offerFirst(deque.pollLast());
        }

        String result = "";
        while (!deque.isEmpty()) result += deque.poll();

        return Integer.parseInt(result);
    }*/

    static Queue insertToQueue(Queue queue, DSLR now) {
        int nowNumber = now.number;
        int nowCount = now.count;
        String nowCommand = now.command;

        // 1. D
        int caseD = (nowNumber * 2) % 10000;
        if (!isVisited[caseD]) {
            isVisited[caseD] = true;
            queue.offer(new DSLR(caseD, nowCount + 1, nowCommand+"D"));
        }

        // 2. S
        int caseS = nowNumber == 0 ? 9999:nowNumber-1;
        if (!isVisited[caseS]) {
            isVisited[caseS] = true;
            queue.offer(new DSLR(caseS, nowCount + 1, nowCommand+"S"));
        }

        // 3. L
//        int caseL = rotateA(nowNumber, "L");
        int caseL = (nowNumber % 1000) * 10 + (nowNumber/1000);
        if (!isVisited[caseL]) {
            isVisited[caseL] = true;
            queue.offer(new DSLR(caseL, nowCount + 1, nowCommand+"L"));
        }

        // 4. R
//        int caseR = rotateA(nowNumber, "R");
        int caseR = (nowNumber % 10) * 1000 + (nowNumber/10);
        if (!isVisited[caseR]) {
            isVisited[caseR] = true;
            queue.offer(new DSLR(caseR, nowCount + 1, nowCommand+"R"));
        }

        return queue;
    }

    static void bfs(DSLR initDSLR) { // A가 B와 같아질 때까지 시도하는 함수
        Queue<DSLR> queue = new ArrayDeque<>();
        queue.offer(initDSLR);
        isVisited[A] = true;

        while(!queue.isEmpty()) {
            DSLR now = queue.poll();
            if (now.number == B) {
                if (now.count < minCount) {
                    minCount = now.count;
                    command = now.command;
                }
                continue;
            }
            queue = insertToQueue(queue, now);
        }
    }

}
