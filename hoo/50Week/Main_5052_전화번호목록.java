package SSAFY.study.algo.week50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_5052_전화번호목록 {

    static int N;
    static String[] phoneNumbers;
    static Map<String, Integer> phoneNumberCache;   // 전화번호를 캐시처럼? 사용하기 위해 맵 사용

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init(br);
            if (isConsistent()) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        System.out.println(sb);
    }

    static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        phoneNumbers = new String[N];
        phoneNumberCache = new HashMap<>();
        for (int i = 0; i < N; i++) {
            phoneNumbers[i] = br.readLine();
            phoneNumberCache.put(phoneNumbers[i], 1);
        }
    }

    static boolean isConsistent() {
        String now, nowSubString;
        for (int i = 0; i < N; i++) {
            now = phoneNumbers[i];
            for (int j = 0; j < now.length(); j++) {
                nowSubString = now.substring(0, j);
                if (phoneNumberCache.containsKey(nowSubString)) return false;   // 접두어와 겹치는 전화번호가 있는 경우임
            }
        }

        return true;
    }


}
