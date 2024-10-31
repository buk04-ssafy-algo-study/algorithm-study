import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_7490 {
    static int M;
    static String[] op = {"+", "-", " "};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            List<String> res = new ArrayList<>();
            M = Integer.parseInt(br.readLine());

            dfs(1, "1", res); // 재귀로 식 구하기

            Collections.sort(res); // 아스키 코드 순으로 정렬
            for (int j = 0; j < res.size(); j++)
                System.out.println(res.get(j));
            System.out.println();
        }
    }

    public static void dfs(int depth, String express, List<String> res) {
        if (depth == M) {  // 숫자를 모두 넣었다면 종료
            if (cal(express)) // 합이 0인지 계산
                res.add(express);
            return;
        }

        for (int i = 0; i < op.length; i++) // 연산자에 따라 String 업데이트
            dfs(depth + 1, express + op[i] + (depth + 1), res);
    }

    public static boolean cal(String express) {

        String str = express.replaceAll(" ", ""); // 공백 없애기
        StringTokenizer st = new StringTokenizer(str, "+-", true); // 연산자 기준으로 token 나누기

        int ans = Integer.parseInt(st.nextToken()); // 맨 처음 숫자

        while (st.hasMoreTokens()) {
            String tmp = st.nextToken(); // 연산자

            if (tmp.equals("+"))
                ans += Integer.parseInt(st.nextToken()); // 다음 숫자 더해주기
            else if (tmp.equals("-"))
                ans -= Integer.parseInt(st.nextToken()); // 다음 숫자 빼주기
        }

        return ans == 0;
    }
}