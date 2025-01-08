import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<String> stack = new Stack<>();

        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            switch (str.substring(i,i+1)) {
                case "(":
                    stack.push("(");
                    break;
                case ")":
                    if(stack.isEmpty()) {
                        res = -1;
                        break;
                    }
                    else if (!stack.isEmpty() && stack.peek().equals("(")) { // 직전 괄호가 열린 괄호인 경우
                        stack.pop();
                        stack.push("2");
                    } else if (!stack.isEmpty() && isNum(stack.peek())) { // 직전에 괄호가 아니라 숫자인 경우
                        int tmp = 0;
                        while (!stack.isEmpty() && !stack.peek().equals("(")) { // 열린 괄호가 나올 때까지 숫자 더해서 계산
                            if(!isNum(stack.peek())) { // 스택이 비지 않았는데 숫자가 아니라면 잘못된 괄호
                                res = -1;
                                break;
                            }
                            int num = Integer.parseInt(stack.pop());
                            tmp += num;
                        }
                        if(res == -1) break;
                        if (stack.isEmpty()) { // 숫자를 모두 처리했는데 스택이 비었다면 열린 괄호가 없는 것이므로 잘못된 괄호
                            res = -1;
                            break;
                        }

                        tmp *= 2;
                        stack.pop();
                        stack.push(String.valueOf(tmp));

                    } else if (!stack.isEmpty() && !isNum(stack.peek())) { // 직전에 닫힌 괄호인 경우
                        res = -1;
                        break;
                    }
                    break;
                case "[":
                    stack.push("[");
                    break;
                case "]":
                    if(stack.isEmpty()) {
                        res = -1;
                        break;
                    }
                    else if (!stack.isEmpty() && stack.peek().equals("[")) { // 직전에 열린 괄호인 경우
                        stack.pop();
                        stack.push("3");
                    } else if (!stack.isEmpty() && isNum(stack.peek())) { // 직전에 숫자인 경우
                        int tmp = 0;
                        while (!stack.isEmpty() && !stack.peek().equals("[")) { // 열린 괄호가 나올 때까지 숫자 더해서 계산
                            if(!isNum(stack.peek())) { // 스택이 비지 않았는데 숫자가 아니라면 잘못된 괄호
                                res = -1;
                                break;
                            }
                            int num = Integer.parseInt(stack.pop());
                            tmp += num;
                        }
                        if(res == -1) break;
                        if (stack.isEmpty()) { // 숫자를 모두 처리했는데 스택이 비었다면 열린 괄호가 없는 것이므로 잘못된 괄호
                            res = -1;
                            break;
                        }
                        tmp *= 3;
                        stack.pop();
                        stack.push(String.valueOf(tmp));

                    } else if (!stack.isEmpty() && !isNum(stack.peek())) { // 직전에 닫힌 괄호인 경우
                        res = -1;
                        break;
                    }
                    break;
            }
        }

        if (res == -1) { // 올바른 괄호가 아닌 경우
            res = 0;
        } else {
            while (!stack.isEmpty()) {
                if (!isNum(stack.peek())) { // 스택에 괄호가 남아있으면 올바른 괄호가 아님
                    res = 0;
                    break;
                }
                res += Integer.parseInt(stack.pop());
            }
        }
        System.out.print(res);
    }

    private static boolean isNum(String peek) {
        if (peek.equals("(")|| peek.equals(")") || peek.equals("[") || peek.equals("]")) return false;
        return true;
    }
}
