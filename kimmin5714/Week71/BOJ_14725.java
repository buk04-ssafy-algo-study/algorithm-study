import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        List<String> wordList = new ArrayList<>(); // 정렬하기 위한 리스트

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            String str = "";
            while (st.hasMoreTokens()) {
                str += st.nextToken();
                str += " "; // 나중에 단어로 나누기 위한 공백
            }
            wordList.add(str); // 정보를 입력 받기
        }

        Collections.sort(wordList); // 사전 순으로 정렬

        List<String>[] newWordList = new ArrayList[N]; // 단어로 리스트에 담기

        for (int i = 0; i < N; i++) {
            newWordList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            String[] str = wordList.get(i).split(" "); // 공백으로 단어 구분
            for (int j = 0; j < str.length; j++)
                newWordList[i].add(str[j]);
        }

        for (int i = 0; i < N; i++) {
            int cnt = 0;

            // 직전 문자열과 같은 데까지 갯수 카운트, 출력x
            if (i >= 1) {
                while (newWordList[i - 1].get(cnt).equals(newWordList[i].get(cnt))) {
                    cnt++;
                }
            }
            for (int j = cnt; j < newWordList[i].size(); j++) { // 직전 문자열과 같지 않은 부분 출력
                for (int k = 0; k < cnt; k++) { // 직전 문자열과 같은 만큼 "--" 출력
                    sb.append("--");
                }
                sb.append(newWordList[i].get(j) + "\n");
                cnt++;
            }
        }
        System.out.print(sb);
    }
}
