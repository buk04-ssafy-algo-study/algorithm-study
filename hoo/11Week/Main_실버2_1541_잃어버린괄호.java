package SSAFY.study.algo.week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_실버2_1541_잃어버린괄호 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String temp = "";

        int answer = 0;
        boolean minusFlag = false;	// 처음에는 양수임
        for (int i = 0; i < input.length(); i++) {	// 입력 길이에 대해서
            String c = String.valueOf(input.charAt(i));
            if (c.equals("-") || c.equals("+")) {	// 연산자를 만나면, 숫자 끝
                if (!minusFlag) answer += Integer.parseInt(temp);
                else answer -= Integer.parseInt(temp);
                if (c.equals("-")) {	// 마이너스를 만나면 그 뒤로는 다 마이너스
                    minusFlag = true;
                }
                temp = "";
            } else if (i == input.length()-1) {	// 연산자 마지막이면 숫자 넣어주기
                temp += c;
                if (!minusFlag) answer += Integer.parseInt(temp);
                else answer -= Integer.parseInt(temp);
            }
            else temp += c;
        }

        System.out.println(answer);
    }

}
