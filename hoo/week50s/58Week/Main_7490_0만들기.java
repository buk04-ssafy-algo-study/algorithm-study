package SSAFY.study.algo.week50s.week58;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main_7490_0만들기 {

    static PriorityQueue<String> formulaPq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        formulaPq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        int inputNumber;
        for (int t = 0; t < T; t++) {
            inputNumber = Integer.parseInt(br.readLine());
            makeFormula("", 1, inputNumber);

            while (!formulaPq.isEmpty()) sb.append(formulaPq.poll()).append("\n");  // pq에 쌓인 수식 string builder에 삽입
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void makeFormula(String formula, int now, int end) {  // formula: 이때까지 만들어진 식, now: 지금 숫자, end: 끝 숫자
        if (now == end) {   // 기저, 끝 숫자에 도달한 경우
            formula += String.valueOf(end);
            if (isFormulaZero(formula)) formulaPq.offer(formula);

            return;
        }

        formula += String.valueOf(now);
        makeFormula(formula + "+", now+1, end);
        makeFormula(formula + "-", now+1, end);
        makeFormula(formula + " ", now+1, end);
    }

    static boolean isFormulaZero(String formula) {  // 만들어진 식이 0인지 판별하는 함수
//        System.out.println(formula);
        int result = 0;
        int temp = 0;
        int multiplier = 1; // +, -에 따라 달라질 부호
        for (int i = 0; i < formula.length(); i++) {
            char nowChar = formula.charAt(i);
            switch (nowChar) {
                case '+':
                    result = result + (multiplier * temp);    // 이전까지 결과 계산 후
                    temp = 0;
                    multiplier = 1;  // 부호 수정
                    break;
                case '-':
                    result = result + (multiplier * temp);
                    temp = 0;
                    multiplier = -1;
                    break;
                case ' ':   // 빈칸은 건너 뜀
                    break;
                default:    // 숫자인 경우
                    temp = Integer.parseInt(String.valueOf(temp) + String.valueOf(nowChar));
                    break;
            }
//            System.out.println(nowChar);
//            System.out.println(result);
//            System.out.println(temp);
//            System.out.println("--------------");
        }
        result = result + (multiplier * temp);
        if (result == 0) {
//            System.out.println(formula);
//            System.out.println(result);
//            System.out.println(temp);
            return true;
        }

        return false;
    }

}
