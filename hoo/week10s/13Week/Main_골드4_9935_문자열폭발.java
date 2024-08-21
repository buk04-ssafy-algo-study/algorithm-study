package SSAFY.study.algo.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_골드4_9935_문자열폭발 {

    static String inputString;
    static String bomb;
    static Stack<Character> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputString = br.readLine();
        bomb = br.readLine();
        stack = new Stack<>();
        doBomb();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            sb.append(stack.get(i));
        }
        if (sb.toString().equals("")) System.out.println("FRULA");
        else System.out.println(sb);
    }

    static void removeBomb() {
        for (int i = 0; i < bomb.length(); i++) {
            stack.pop();
        }
    }

    static void doBomb() {
        for (int i = 0; i < inputString.length(); i++) {
            stack.push(inputString.charAt(i));  // 문자 일단 스택에 넣어줌
            if (stack.size() >= bomb.length()) {    // 스택 크기가 폭탄 문자 길이보다 크거나 같으면
//                String temp = "";
                int sameCount = 0;
                for (int l = bomb.length(); l > 0; l--) {   // 뒤에서부터 비교, 폭탄 문자열있으면 그만큼 빼줌
//                    temp += stack.get(stack.size()-l);
                    if (stack.get(stack.size()-l).equals(bomb.charAt(bomb.length()-l))) sameCount += 1;
                }
//                if (temp.equals(bomb)) removeBomb();
                if (sameCount == bomb.length()) removeBomb();
            }
        }
    }

}
