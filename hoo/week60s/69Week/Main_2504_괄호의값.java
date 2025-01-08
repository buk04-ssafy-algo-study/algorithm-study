package SSAFY.study.algo.week60s.week69;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_2504_괄호의값 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        if (input.length() == 1 || input.charAt(0) == ')' || input.charAt(0) == ']') {  // 애초에 틀린 입력은 바로 0출력 후 종료
            System.out.println(0);
            return;
        }

        System.out.println(calcParentheses(input));
    }

    static int calcParentheses(String input) {
        Stack<Character> parenthesesStack = new Stack<>();  // 괄호를 저장하는 스택

        int parenthesesValue = 0;
        int tempValue = 1;
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == '(' || c == '[') { // 여는 괄호인 경우, 임시 값에 해당 괄호에 해당하는 값 곱해줌
                parenthesesStack.push(c);
                tempValue *= (c == '(')? 2:3;
            } else {
                if (parenthesesStack.isEmpty() || !isRightParentheses(parenthesesStack.peek(), c)) return 0; // 불가능한 경우 바로 0반환

                if ( (c == ')' && input.charAt(i-1) == '(') || (c == ']' && input.charAt(i-1) == '[') ) parenthesesValue += tempValue;  // 분배법칙? 느낌으로다가 밖에 둘러싸고 있는 값이 안에 곱해진다고 생각하고 더해줌. 근데 중복으로 값이 더해지는 문제가 발생하므로 바로 괄호가 닫히는 경우에만 더해줘야 함.
                parenthesesStack.pop(); // 괄호쌍에 해당하는 여는 괄호 뽑아냄
                tempValue /= (c == ')')? 2:3;   // 사용한 괄호의 값만큼 임시값 나눠줌
            }
        }

        return (!parenthesesStack.isEmpty())? 0:parenthesesValue;  // 모든 괄호 다 확인했는데 스택에 남은 괄호 있으면 올바른 괄호열이 아니므로 0 반환
    }

    static boolean isRightParentheses(char uponStack, char c) {
        if ( (c == ')' &&  uponStack == '(') || (c == ']' && uponStack == '[') ) return true;
        return false;
    }

}
