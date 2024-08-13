package SSAFY.study.algo.week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_골드4_1461_도서관 {

    static int N, M;
    static Integer[] bookAxisMinus; // Collections의 reverseOrder()를 사용하기 위해 wrapper class로 선언
    static Integer[] bookAxisPlus;
    static int answer;
    static boolean isLast;  // 맨 마지막 이동인지 체크

    public static void main(String[] args) throws IOException {
        init();
        checkDistance();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Integer[] bookAxis = new Integer[N];
        st = new StringTokenizer(br.readLine());
        int minusCount = 0;
        int plusCount = 0;
        for (int i = 0; i < N; i++) {
            int axis = Integer.parseInt(st.nextToken());
            if (axis < 0) minusCount += 1;
            else if (axis > 0) plusCount += 1;
            bookAxis[i] = axis;
        }
        Arrays.sort(bookAxis);  // 일단 오름차순 정렬, 그 후 음수 영역, 양수 영역 따로 담음

        bookAxisMinus = Arrays.copyOf(bookAxis, minusCount);
        Arrays.sort(bookAxisMinus, Collections.reverseOrder()); // 탐색 편하게 하기 위해 음수 절댓값 기준 오름차순으로 다시 정렬
        bookAxisPlus = Arrays.copyOfRange(bookAxis, minusCount, minusCount + plusCount);
        isLast = false;
        answer = 0;
    }

    static int plusDistance(Integer[] bookAxis, int axis) {
        if (isLast) answer += Math.abs(bookAxis[axis]);
        else answer += 2 * Math.abs(bookAxis[axis]);
        axis += M;

        return axis;
    }

    static void checkDistance() {   // 음수 좌표, 양수 좌표 모두 m으로 나누어 떨어지는지 체크, 안 나누어 떨어지면 m 안채우고 갔다왔을 때 나누어 떨어지게 하는 구간 체크
        int minusEndIdx = bookAxisMinus.length - 1;
        int plusEndIdx = bookAxisPlus.length - 1;
        int minusAxis = (bookAxisMinus.length % M) - 1;   // 인덱스 처리 위해 -1
        int plusAxis = (bookAxisPlus.length % M) - 1;
        if (minusAxis == -1) minusAxis += M;
        if (plusAxis == -1) plusAxis += M;

        while (true) {
            if (minusAxis > minusEndIdx && plusAxis > plusEndIdx) break;    // 종료 조건: 모든 책을 제자리에 뒀을 경우

            if (plusAxis > plusEndIdx) {  // 무조건 음수쪽 책 제자리에 두러 가기
                if (minusAxis == minusEndIdx) isLast = true;    // 마지막 두는 책인지 체크
                minusAxis = plusDistance(bookAxisMinus, minusAxis);
                continue;
            } else if (minusAxis > minusEndIdx) {  // 무조건 양수쪽 책 제자리에 두러 가기
                if (plusAxis == plusEndIdx) isLast = true;    // 마지막 두는 책인지 체크
                plusAxis = plusDistance(bookAxisPlus, plusAxis);
                continue;
            }

            if (Math.abs(bookAxisMinus[minusAxis]) < bookAxisPlus[plusAxis]) {
                minusAxis = plusDistance(bookAxisMinus, minusAxis);
            } else if (Math.abs(bookAxisMinus[minusAxis]) > bookAxisPlus[plusAxis]) {
                plusAxis = plusDistance(bookAxisPlus, plusAxis);
            }
        }
        System.out.println(answer);
    }

}
