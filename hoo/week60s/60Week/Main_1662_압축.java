package SSAFY.study.algo.week60;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1662_압축 {

    static int index;   // 확인할 인덱스

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        System.out.println(unzip(input));   // 재귀 함수 실행
    }

    static int unzip(String input) {
        int length = 0;
        while (index < input.length()) {    //
            char current = input.charAt(index); //

            if (index + 1 < input.length() &&   // 주어진 문자의 끝이 아닌 문자이면서
                    Character.isDigit(current) && input.charAt(index + 1) == '(') { // 현재 인덱스가 숫자이며, 다음 문자가 여는 괄호인 경우
                int k = Integer.parseInt(String.valueOf(input.charAt(index)));  // 괄호 내의 숫자 길이에 곱해줄 현재 숫자 정수화
                index += 2; // 여는 괄호 다음 숫자부터 수행하게

                length += k * unzip(input); // 괄호 내의 문자에 대해 재귀 수행, 길이를 현재 숫자와 곱해줌
            } else if (current == ')') {    // 기저, 닫는 괄호인 경우 다음 인덱스로 늘려주고 지금까지 센 길이 반환
                index++;
                return length;
            } else {    // 다음 문자가 괄호 여는 게 아닌 경우이며 숫자라면 길이+1, 인덱스+1
                length++;
                index++;
            }
        }

        return length;  // 최종 길이 반환
    }

}
