import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9082 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] nearCnt = new int[N];

            String str = br.readLine();
            for (int i = 0; i < N; i++) nearCnt[i] = str.charAt(i) - '0';

            str = br.readLine();

            int res = 0;
            for (int j = 0; j < N; j++) {
                if (j == 0 && nearCnt[j] != 0 && nearCnt[j + 1] != 0) { // 첫번째 칸
                    nearCnt[j] -= 1;
                    nearCnt[j + 1] -= 1;
                    res++;
                } else if (j == (N - 1) && nearCnt[j - 1] != 0 && nearCnt[j] != 0) { // 마지막 칸
                    nearCnt[j - 1] -= 1;
                    nearCnt[j] -= 1;
                    res++;
                } else if (1 <= j && j <= (N - 2)
                        && nearCnt[j - 1] != 0 && nearCnt[j] != 0 && nearCnt[j + 1] != 0) { // 나머지 칸
                    nearCnt[j - 1] -= 1;
                    nearCnt[j] -= 1;
                    nearCnt[j + 1] -= 1;
                    res++;
                }
            }
            sb.append(res + "\n");
        }
        System.out.print(sb);
    }
}