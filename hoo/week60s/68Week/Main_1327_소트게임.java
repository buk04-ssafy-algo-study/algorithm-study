package SSAFY.study.algo.week60s.week68;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1327_소트게임 {

    static int N, K;
    static String numbers;

    public static void main(String[] args) throws IOException {
        init();
        makeAscending();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        numbers = "";
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) numbers += Integer.parseInt(st.nextToken());
    }

    static void makeAscending() {    // prevIndex : 그 전에 뒤집었던 인덱스 똑같이 뒤집으면 반복이니까 그 전 뒤집은 건 안뒤집게 하기 위해 파라미터로 넘겨줌
        Queue<String> q = new ArrayDeque<>();
        q.offer(numbers);
        Map<String, Integer> isChecked = new HashMap<>();
        isChecked.put(numbers, 0);

        String now;
        int depth = 0;
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int d = 0; d < qSize; d++) {
                now = q.poll();
                if (isAscending(now)) {
                    System.out.println(depth);
                    return;
                }

                for (int i = 0; i <= N-K; i++) {
                    String flippedNumbers = flip(now, i);
                    if (isChecked.containsKey(flippedNumbers)) continue;

                    q.offer(flippedNumbers);
                    isChecked.put(flippedNumbers, depth+1);
                }
            }
            depth++;
        }

        System.out.println(-1);
    }

    static boolean isAscending(String nowNumbers) {
        for (int i = 0; i < N-1; i++) if (Integer.parseInt(String.valueOf(nowNumbers.charAt(i))) > Integer.parseInt(String.valueOf(nowNumbers.charAt(i+1)))) return false;    // 오름차순 아니면 바로 거짓 반환

        return true;
    }

    static String flip(String nowNumbers, int flipIndex) {   // 뒤집을 자릿수를 기반으로 뒤집어주는 함수
        Stack<Character> stack = new Stack<>();
        for (int i = flipIndex; i < flipIndex+K; i++) stack.push(nowNumbers.charAt(i));

        String flippedNumbers = nowNumbers.substring(0, flipIndex);
        for (int i = flipIndex; i < flipIndex+K; i++) flippedNumbers += stack.pop();
        for (int i = flipIndex+K; i < nowNumbers.length(); i++) flippedNumbers += nowNumbers.charAt(i);

        return flippedNumbers;
    }

}
