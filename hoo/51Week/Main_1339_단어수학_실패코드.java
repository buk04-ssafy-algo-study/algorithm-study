package SSAFY.study.algo.week51;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main_1339_단어수학_실패코드 {

    static int N;
    static String[] words;

    public static void main(String[] args) throws IOException {
        init();
        Map<Character, Integer> pointMap = allocatePoints();
        System.out.println(calcSum(pointMap));
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        for (int i = 0; i < N; i++) words[i] = br.readLine();
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {  // 길이 순 내림차순 정렬
                return o2.length() - o1.length();
            }
        });
    }

    static Map<Character, Integer> allocatePoints() {  // 알파벳 별 포인트를 할당하는 함수
        Map<Character, Integer> pointMap = new HashMap<>();
        for (int i = 0; i < 26; i++) pointMap.put((char) ('A' + i), -1);    // 알파벳 별 포인트를 저장할 해시맵

        String first = words[0];
        int point = 9;  // 알파벳에 할당할 포인트를 기억할 변수
        for (int i = 0; i < first.length(); i++) {    // first의 길이를 돌며
            if (pointMap.get(first.charAt(i)) == -1) pointMap.put(first.charAt(i), point--);   // 해당 알파벳에 현재 포인트 할당해줌

            String nextWord;
            int nextWordIndex;
            for (int j = 1; j < N; j++) {
                nextWord = words[j];
                nextWordIndex = i - (first.length() - nextWord.length());
                if (nextWordIndex < 0 || nextWordIndex >= nextWord.length()) break;   // 현재 비교할 인덱스부터의 길이와 다른 단어의 알파벳에는 포인트 할당을 하지 않음
                if (pointMap.get(nextWord.charAt(nextWordIndex)) == -1) pointMap.put(nextWord.charAt(nextWordIndex), point--);   // 알파벳에 포인트 할당 안돼있으면 할당함
            }
        }

        return pointMap;
    }

    static long calcSum(Map<Character, Integer> pointMap) {
        long sum = 0;
        String word;
        String tempSum;
        for (int i = 0; i < N; i++) {
            word = words[i];
            tempSum = "";
            for (int j = 0; j < word.length(); j++) {
                tempSum += String.valueOf(pointMap.get(word.charAt(j)));
            }
            sum += Long.parseLong(tempSum);
        }

        return sum;
    }

}
