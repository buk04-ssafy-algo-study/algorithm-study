package SSAFY.study.algo.week60;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_12891_DNA비밀번호 {

    static int S, P;
    static String dnaString;
    static Map<Character, Integer> needACGT;

    public static void main(String[] args) throws IOException {
        init();
        countPossible();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        dnaString = br.readLine();
        needACGT = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        needACGT.put('A', Integer.parseInt(st.nextToken()));
        needACGT.put('C', Integer.parseInt(st.nextToken()));
        needACGT.put('G', Integer.parseInt(st.nextToken()));
        needACGT.put('T', Integer.parseInt(st.nextToken()));
    }

    static void countPossible() {
        Map<Character, Integer> acgtMap = new HashMap<>();  // 현재 윈도우 안에 있는 A, C, G, T 개수 카운트
        acgtMap.put('A', 0);
        acgtMap.put('C', 0);
        acgtMap.put('G', 0);
        acgtMap.put('T', 0);
        int fulfillCount = 0;   // A, C, G, T 만족하는 개수 카운트
        for (char key : needACGT.keySet()) if (needACGT.get(key) == 0) fulfillCount++;  // 0개 필요한 문자들은 미리 카운트+1

        char outChar, inChar;
        for (int i = 0; i < P; i++) {   // 첫 글자부터 P개까지 a, c, g, t 개수 저장
            inChar = dnaString.charAt(i);
            acgtMap.put(inChar, acgtMap.get(inChar)+1);
            if (acgtMap.get(inChar).equals(needACGT.get(inChar))) fulfillCount++;    // 딱 조건 충족 개수가 됐을 때 fullfillCount +1
        }
        int count = (fulfillCount == 4)? 1:0;   // 제일 첫 윈도우가 조건 충족하는 지 여부에 따라 0 or 1로 초기화

        for (int i = P; i < S; i++) {
            inChar = dnaString.charAt(i);
            acgtMap.put(inChar, acgtMap.get(inChar)+1);
            if (acgtMap.get(inChar).equals(needACGT.get(inChar))) fulfillCount++;    // 딱 조건 충족 개수가 됐을 때 fullfillCount +1
//            System.out.println(acgtMap);
//            System.out.println(fulfillCount);
            outChar = dnaString.charAt(i-P);
            if (acgtMap.get(outChar).equals(needACGT.get(outChar))) fulfillCount--;  // 조건 충족 카운트보다 딱 한 개만큼 작아졌다면 fullfillCount -1
            acgtMap.put(outChar, acgtMap.get(outChar)-1);
//            System.out.println(acgtMap);
//            System.out.println(fulfillCount);
//            System.out.println("=============");

            if (fulfillCount == 4) count++; // 조건 모두 충족한다면 count+1
        }

        System.out.println(count);
    }

}
