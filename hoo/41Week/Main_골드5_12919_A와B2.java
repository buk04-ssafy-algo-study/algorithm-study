package SSAFY.study.algo.week41;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_골드5_12919_A와B2 {

    static String S, T;
    static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        isPossible = false;
        aAndB(T);
        if (isPossible) System.out.println(1);
        else System.out.println(0);
    }

    static void aAndB(String input) {
        if (isPossible) return; // 이미 가능하다고 판단됐으면 수행하지 않음
        if (input.length() == S.length()) { // 기저, 원래 문자열의 길이에 도달했을 때
            if (input.equals(S)) isPossible = true;
            return;
        }

        StringBuilder sb = new StringBuilder();
        if (String.valueOf(input.charAt(input.length()-1)).equals("A")) {   // A를 추가했던 경우라면
            sb.append(input);
            sb.deleteCharAt(input.length()-1);
            aAndB(sb.toString());
        }
        if (String.valueOf(input.charAt(0)).equals("B")) {  // A를 추가한 게 아니라 B를 추가하고 뒤집은 경우였을 수도 있으므로
            sb = new StringBuilder();
            sb.append(input);
            sb.reverse();
            sb.deleteCharAt(input.length()-1);
            aAndB(sb.toString());
        }
    }

}
