package SSAFY.study.algo.week40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_골드4_17298_오큰수 {

    static int N;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        init();
        doNGE();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());
    }

    static void doNGE() {
        Stack<Integer> answer = new Stack<>();  // 정답 저장용 스택
        Stack<Integer> stack = new Stack<>();   // 로직 수행용 스택
        for (int i = N-1; i >= 0; i--) {
            if (stack.isEmpty()) {  // 스택 비어있으면 오큰수가 없는 경우임(그 전 숫자의 오른쪽 중에서는 가장 큰 수)
                answer.push(-1);
                stack.push(numbers[i]);  // 자기 자신 삽입
                continue;
            }

            if (numbers[i] < stack.peek()) {    // 스택 맨 위의 수가 지금 수보다 크면 오큰수임
                answer.push(stack.peek());
            }
            else {
                while (!stack.isEmpty() && numbers[i] >= stack.peek()) { // 스택의 가장 상위 수가 현재 숫자보다 클 때까지(오큰수가 나올 때까지) 계속 뽑음
                    stack.pop();
                }
                if (stack.isEmpty()) answer.push(-1);    // 스택이 빈다면 오큰수가 없는 경우임
                else answer.push(stack.peek());  // 스택이 안비면 오큰수 정답 스택에 저장
            }
            stack.push(numbers[i]);
        }

        StringBuilder sb = new StringBuilder();
        while (!answer.isEmpty()) sb.append(answer.pop()).append("\n");
        System.out.println(sb);
    }

}
