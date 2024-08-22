package SSAFY.study.algo.week49;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_6198_옥상정원꾸미기 {

    static int N;
    static int[] heights;

    public static void main(String[] args) throws IOException {
        init();
        countCanSee();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }
    }

    static void countCanSee() {
        long answer = 0;    // 접근 방식: "현재 빌딩을 볼 수 있는" 빌딩을 카운트 하기
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {   // 모든 건물에 대해 수행
            while (!stack.isEmpty()) {  // 스택이 비거나
                if (heights[i] < stack.peek()) break;   // 지금 빌딩보다 높은 빌딩이 나올때까지
                stack.pop();    // 스택에 있는 빌딩들 모두 제거
            }
            answer += stack.size(); // 그 후 스택에 남아있는 빌딩이 현재 빌딩을 볼 수 있는 빌딩들임
            stack.push(heights[i]); // 그 후 해당 건물 스택에 넣어줌
        }

        System.out.println(answer);
    }

}
