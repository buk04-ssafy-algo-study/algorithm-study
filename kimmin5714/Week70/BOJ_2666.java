import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2666 {
    private static int order[], cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int open1 = Integer.parseInt(st.nextToken());
        int open2 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        cnt = Integer.parseInt(st.nextToken());
        order = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            order[i] = Integer.parseInt(st.nextToken());
        }

        int res = dfs(0, open1, open2);
        System.out.print(res);
    }

    private static int dfs(int num, int open1, int open2) {
        if (num == cnt) return 0; // 더이상 차이 발생하지 않음

        int diff1 = Math.abs(open1 - order[num]); // 첫번째 문과 차이
        int diff2 = Math.abs(open2 - order[num]); // 두번째 문과 차이

        return Math.min(diff1 + dfs(num + 1, open2, order[num]), // 첫 번째 문을 선택한 후 두 번째 문은 그대로
                diff2 + dfs(num + 1, open1, order[num])); // 두 번째 문을 선택한 후 첫 번째 문은 그대로
    }
}