package SSAFY.study.algo.week38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main_골드4_1062_가르침 {

    static int N, K;
    static List<String> wordList;   // 단어들의 리스트
    static HashMap<String, Boolean> canRead;
    static int maxRead;

    public static void main(String[] args) throws IOException {
        init();
        if (K < 5) maxRead = 0;    // anta + tica 에서 애초에 5개의 글자를 필요로 함
        else if (K == 26) maxRead = N;   // 모든 글자를 가르칠 수 있으면 모든 단어를 읽을 수 있음
        else combination(0, 0);

        System.out.println(maxRead);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        wordList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            input = input.substring(4, input.length()-4);
            wordList.add(input);
        }
        initCanRead();
        maxRead = Integer.MIN_VALUE;
    }

    private static void initCanRead() {
        canRead = new HashMap<>();
        for (int i = 0; i < 26; i++) canRead.put(String.valueOf((char) (i + 97)), false);   // 읽을 수 있는 알파벳인지 여부 초기화
        canRead.replace("a", true);
        canRead.replace("c", true);
        canRead.replace("i", true);
        canRead.replace("n", true);
        canRead.replace("t", true);
    }

    static void countMaxRead() {
        // 단어들 순회하며 개수 세알리기
        int canReadWordCount = 0;
        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);
            int tempCount = 0;
            for (int j = 0; j < word.length(); j++) {
                String w = String.valueOf(word.charAt(j));
                if (canRead.get(w)) tempCount += 1;
            }
            if (tempCount == word.length()) canReadWordCount += 1;
        }
        maxRead = Math.max(maxRead, canReadWordCount);
    }

    static void combination(int start, int count) {  // r: 뽑아야하는 개수
        if (count == K-5) {
            countMaxRead();   // r개 다 뽑았으면 단어 개수 세알림
            return;
        }

        for (int i = start; i < 26; i++) {
            String alphabet = String.valueOf((char) (i+97));
            if (canRead.get(alphabet)) continue;    // 이미 뽑았으면 지나감
            canRead.replace(alphabet, true);
            combination(i+1, count+1);
            canRead.replace(alphabet, false);
        }
    }

}
