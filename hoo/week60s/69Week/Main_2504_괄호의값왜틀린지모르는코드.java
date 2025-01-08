package SSAFY.study.algo.week60s.week69;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_2504_괄호의값왜틀린지모르는코드 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(calcParentheses(input));
    }

    static int calcParentheses(String input) {
        Stack<char[]> parenthesesStack = new Stack<>();  // 괄호를 저장하는 스택
        if (input.charAt(0) == ')' || input.charAt(0) == ']') return 0; // 첫 괄호부터 닫는 괄호면 바로 0 반환하고 종료
        parenthesesStack.push(new char[] {input.charAt(0), 0});

        int parenthesesValue = 0;
        int index = 1;
        Stack<Integer> tempValueStack = new Stack<>();  // 괄호에 쌓여있는 올바른 괄호쌍으로 만들어진 숫자를 저장하는 스택
        while (index < input.length()) {
            if (input.charAt(index) == '(' || input.charAt(index) == '[') parenthesesStack.push(new char[] {input.charAt(index), (char) index++});    // 여는 괄호는 바로 넣고 넘어감
            else {
                int tempValue = 1;
                if (index - parenthesesStack.peek()[1] != 1) {  // 만약 이번 괄호가 품고있던 다른 괄호쌍이 있으면 그 값들의 합을 tempValue로
                    int valueInTempValueStack = 0;
                    while (!tempValueStack.isEmpty()) valueInTempValueStack += tempValueStack.pop();
                    tempValue = valueInTempValueStack;
                }
                int pairIndexValue = parenthesesStack.peek()[1] + index;    // 연속으로 닫히는 괄호라면 두 괄호의 인덱스를 더한 값이 계속 같아야 함.
                while (true) {  // 닫는 괄호를 마주친 경우
                    char c = input.charAt(index);
                    if (parenthesesStack.isEmpty() || !isRightParentheses(parenthesesStack.peek()[0], c)) return 0; // 옳지 않은 괄호열이면 바로 0 반환
                    if (parenthesesStack.peek()[1] + index != pairIndexValue) break;  // 올바른 괄호쌍이긴 하지만 연속으로 닫히는 괄호가 아니라면 중단
                    parenthesesStack.pop();
                    tempValue *= (c == ')')? 2:3;    // 괄호 유형에 따라 값 더해줌

                    index++;
                    if (index == input.length()) break;   // 모든 괄호를 다 봤다면 중단
                    c = input.charAt(index);
                    if (c == '(' || c == '[') break;    // 다음 괄호가 여는 괄호면 반복 중단
                }
                tempValueStack.push(tempValue);
            }
        }
        while (!tempValueStack.isEmpty()) parenthesesValue += tempValueStack.pop();

        return (!parenthesesStack.isEmpty())? 0:parenthesesValue;  // 모든 괄호 다 확인했는데 스택에 남은 괄호 있으면 올바른 괄호열이 아니므로 0 반환
    }

    static boolean isRightParentheses(char uponStack, char c) {
        if ( (c == ')' &&  uponStack == '(') || (c == ']' && uponStack == '[') ) return true;
        return false;
    }

}
