package SSAFY.study.algo.week51;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1339_단어수학 {

    static class Alphabet {
        char letter;
        int weight;

        public Alphabet(char letter, int weight) {
            this.letter = letter;
            this.weight = weight;
        }

        @Override
        public String toString() { return this.letter + " " + this.weight; }
    }
    static List<Alphabet> alphabetList;
    static int N;
    static String[] words;
    static int[] points;


    public static void main(String[] args) throws IOException {
        init();
        calcSum();
    }

    static void init() throws IOException {
        alphabetList = new ArrayList<>();
        for (int i = 0; i < 26; i++) alphabetList.add(new Alphabet((char) ('A' + i), 0));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();

            char alphabet;
            for (int j = 1; j <= input.length(); j++) {
                alphabet = input.charAt(input.length() - j);
                alphabetList.get(alphabet - 'A').weight += (int) Math.pow(10, j-1);
            }
            words[i] = input;
        }
        Collections.sort(alphabetList, new Comparator<Alphabet>() { // 가중치 기준 내림차순 정렬
            @Override
            public int compare(Alphabet o1, Alphabet o2) {
                return o2.weight - o1.weight;
            }
        });

        points = new int[26];
        for (int i = 0; i < 10; i++) {  // 알파벳 리스트 돌며 가중치 순으로 9~0까지 값 할당
            points[alphabetList.get(i).letter - 'A'] = 9 - i;
        }
    }

    static void calcSum() {
        long sum = 0;
        String tempSum;
        for (int i = 0; i < words.length; i++) {
            tempSum = "";
            for (int j = 0; j < words[i].length(); j++) {
                tempSum += points[words[i].charAt(j) - 'A'];
            }
            sum += Long.parseLong(tempSum);
        }

        System.out.println(sum);
    }

}
