package SSAFY.study.algo.week60s.week65;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_16719_ZOAC {

    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        sb = new StringBuilder();
        boolean[] isUsed = new boolean[input.length()];
        doPrint(input, 0, input.length(), isUsed);
        System.out.println(sb);
    }

    static void doPrint(String input, int start, int end, boolean[] isUsed) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {    // 0번 인덱스에 들어있는 문자 사전 순 오름차순 정렬
                return o1[0] - o2[0];
            }
        });    // 현재 범위에서 가장 사전순 앞인 단어와 그의 인덱스 배열을 타입으로 갖는 pq
        for (int i = start; i < end; i++) {
            if (isUsed[i]) continue;
            pq.offer(new int[] {input.charAt(i)-0, i});
        }
        if (pq.isEmpty()) return;   // 기저, 만약 탐색 범위 내에 담을 문자가 없었을 경우(탐색할 범위가 0이었거나, 그 범위 내 모든 문자가 다 쓰인 경우) 함수 바로 종료

        int[] selected = pq.poll(); // 해당 범위에서 사전 순 가장 앞 글자 선택
        isUsed[selected[1]] = true;
        printUsed(input, isUsed);   // 선택된 문자들 순서대로 출력

        doPrint(input, selected[1]+1, end, isUsed);
        doPrint(input, start, selected[1]+1, isUsed);
    }

    static void printUsed(String input, boolean[] isUsed) { // 어떤 문자 출력할 지 정했으면 선택된 문자들 출력해주는 함수
        for (int i = 0; i < isUsed.length; i++) if (isUsed[i]) sb.append(input.charAt(i));
        sb.append("\n");
    }

}
