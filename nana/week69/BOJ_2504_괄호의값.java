// (())[[]])([])[ 반례

import java.util.*;

class Main {

    private static Stack<Integer> stack;

    private static boolean solve(int open, int point) {  // 괄호형태, 더하거나 곱할 값

        int temp = 0;

        while (!stack.isEmpty() && stack.peek() > 0) {
            temp += stack.pop();
        }

        if (stack.isEmpty() || stack.pop() != open) {
            return false;
        }

        stack.push(temp == 0 ? point : point * temp);

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.next();
        stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            // 열린 괄호일 떄
            // ( -> -1, [ -> -2
            if (c == '(') {
                stack.push(-1);
                continue;
            } else if (c == '[') {
                stack.push(-2);
                continue;
            }

            if (stack.isEmpty()) {
                System.out.println(0);
                return;
            }

            // 닫힌 괄호일 때 : 중괄호와 대괄호 구분
            // () -> 2, [] -> 3
            // 스택의 가장 윗 부분 검사 -> 값 or 괄호

            if (c == ')') {
                if (!solve(-1, 2)) {
                    System.out.println(0);
                    return;
                }
            } else if (c == ']') {
                if (!solve(-2, 3)) {
                    System.out.println(0);
                    return;
                }
            }
        }

        // 결과 출력
        int res = 0;
        while (!stack.isEmpty()) {
            int now = stack.pop();

            if (now < 0) {
                System.out.println(0);
                return;
            }

            res += now;
        }
        System.out.println(res);
    }
}
