package SSAFY.study.algo.week49;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2529_부등호 {

    static int k;
    static String[] inequalitySigns;
    static long minNumber, maxNumber;
    static String minNumberString, maxNumberString;

    public static void main(String[] args) throws IOException {
        init();
        boolean[] isUsed;
        for (int i = 0; i <= 9; i++) {  // 0 ~ 9 사용해서 숫자 만들기
            isUsed = new boolean[10];
            isUsed[i] = true;
            calcCase(String.valueOf(i), isUsed, 1);
        }
        System.out.println(maxNumberString);
        System.out.println(minNumberString);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        inequalitySigns = br.readLine().split(" ");
        minNumber = 9_876_543_211L; // 가능한 최대 수 9_876_543_210
        maxNumber = 0;  // 가능한 최소 수 012
        minNumberString = "";
        maxNumberString = "";
    }

    static void calcCase(String made, boolean[] isUsed, int count) {    // count: 골라진 숫자의 개수
        if (count == k + 1) {   // 기저, 숫자가 만들어졌으면 최대와 최솟값 갱신
            Long madeNumber = Long.valueOf(made);
            if (madeNumber < minNumber) {   // 최솟값 갱신한 경우
                minNumber = madeNumber;
                minNumberString = made;
            } else if (madeNumber > maxNumber) {
                maxNumber = madeNumber;
                maxNumberString = made;
            }
            return;
        }

        String inequalitySign = inequalitySigns[count-1];
        int beforeNumber = Integer.parseInt(String.valueOf(made.charAt(count-1)));
        for (int i = 0; i <= 9; i++) {
            if (isUsed[i]) continue;

            switch (inequalitySign) {   // 조건에 안맞는 경우들은 건너 뜀
                case ">":
                    if (beforeNumber <= i) continue;
                    break;
                case "<":
                    if (beforeNumber >= i) continue;
                    break;
            }
            isUsed[i] = true;
            calcCase(made + String.valueOf(i), isUsed, count+1);
            isUsed[i] = false;
        }
    }

}
