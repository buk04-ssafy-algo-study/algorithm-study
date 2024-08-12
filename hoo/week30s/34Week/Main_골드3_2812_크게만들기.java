package SSAFY.study.algo.week34;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드3_2812_크게만들기 {

    static int N, K;
    static String[] NArray;

    public static void main(String[] args) throws IOException {
        init();
        calc();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        NArray = br.readLine().split("");
    }

    static void calc() {
        StringBuilder sb = new StringBuilder();
        Deque<String> dq = new ArrayDeque<>();
        int haveToDel = K;  // 지워야 할 수 K로 초기화
        int index = 0;
        while (true) {
            if (haveToDel == 0 || index >= NArray.length) break;  // 지워야 할 수 0이면 멈추고 그 뒤 숫자는 다 출력해주기
            while (true) {  // N의 현재 원소와 크기가 같거나 큰 수가 나올 때까지 계속 반복
                if (dq.isEmpty() || haveToDel == 0) break;  // deque가 비어있거나 지워야 할만큼 지웠으면 반복 중단
                if (Integer.parseInt(dq.peekLast()) < Integer.parseInt(NArray[index])) {
                    dq.pollLast();    // deque의 마지막 원소가 N의 현재 원소보다 작으면 뽑아내고 N의 현재 원소를 넣어줌
                    haveToDel--;    // 지워야할 숫자 카운트 -1
                } else break;
            }
            dq.offerLast(NArray[index++]);
        }
        for (int i = 0; i < haveToDel; i++) dq.pollLast();  // 남은 지워야 할 수만큼 뒤에서 빼줌
        while (!dq.isEmpty()) sb.append(dq.pollFirst());
        while (index < NArray.length) sb.append(NArray[index++]);
        System.out.println(sb);
    }

}
