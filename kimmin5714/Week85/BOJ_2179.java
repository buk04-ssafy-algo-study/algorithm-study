import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        String[] answer = new String[2];

        for (int i = 0; i < N; i++) words[i] = br.readLine();

        int maxCnt = 0; // 접두사 최대 길이
        for (int i = 0; i < N - 1; i++) {

            String cur = words[i];
            if (cur.length() < maxCnt) continue; // 접두사 최대 길이보다 작으면 넘어감

            for (int j = i + 1; j < N; j++) {

                String next = words[j];
                if (next.length() < maxCnt || cur.equals(next)) continue; // 접두사 최대 길이보다 작거나 같은 문자열이면 넘어감

                int cnt = compareWords(cur, next); // 공통 접두사 길이 구하기

                if (cnt > maxCnt) { // 더 긴 접두사면 업데이트
                    maxCnt = cnt;
                    answer[0] = cur;
                    answer[1] = next;
                }
            }
        }
        if (maxCnt == 0) { // 접두사 같은 게 없으면 맨 앞 두 문자열 출력
            answer[0] = words[0];
            answer[1] = words[1];
        }
        System.out.print(answer[0] + "\n" + answer[1]);
    }

    private static int compareWords(String cur, String next) {
        int cnt = 0;
        for (int i = 0; i < cur.length() && i < next.length(); i++) { // 둘 중에 길이 짧은 문자열만큼 탐색
            if (cur.charAt(i) != next.charAt(i)) break; // 문자 같지 않으면 바로 종료
            cnt++;
        }
        return cnt;
    }
}
