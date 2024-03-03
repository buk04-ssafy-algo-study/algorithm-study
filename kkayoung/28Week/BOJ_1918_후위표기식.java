// https://www.acmicpc.net/problem/1918
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Map<Character, Integer> rank = new HashMap<>();
        rank.put('+', 1);
        rank.put('-',1);
        rank.put('*',2);
        rank.put('/',2);
        rank.put('(', 0);
        rank.put(')', 0);

        String exp = br.readLine();
        Stack<Character> s = new Stack<>();

        for(int i=0;i<exp.length();i++) {
            char c = exp.charAt(i);
            // System.out.println("c: "+c+",answer: " +sb.toString());

            if('A'<=c && c<='Z') {
                // alphabet  피연산자 -> 출력
                sb.append(c);
            } else {
                // operator
                switch(c) {
                    case '(': // ( -> 스택에 추가
                        s.push(c);
                        break;
                    case ')': // ) -> ( 까지 스택에서 pop
                        while(true) {
                            char peek = s.peek();
                            if(peek=='(') {
                                s.pop();
                                break;
                            } else {
                                sb.append(s.pop());
                            }
                        }
                        break;
                    default: // + - * / -> 스택 top 우선순위 >= 현재 연산자 우선순위라면 스택에서 pop
                        int nowRank = rank.get(c);
                        while(!s.isEmpty()) {
                            if(rank.get(s.peek())>=nowRank) {
                                sb.append(s.pop());
                            } else {
                                break;
                            } 
                        }
                        s.push(c);
                }
            }
        }

        while(!s.isEmpty()) { // stack에 남은 문자 제거
            sb.append(s.pop());
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
