import java.util.*;
import java.io.*;

class Main {

    private static int LION = 1;
    private static int[] dolls;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        dolls = new int[N];
        for (int i = 0; i < N; i++) {
            dolls[i] = Integer.parseInt(st.nextToken());
        }

        // 1이 K개 이상 있는 가장 작은 연속된 인형들의 집합의 크기
        int min = Integer.MAX_VALUE;
        int count = 0;
        int left = 0;

        for (int right = 0; right < N; right++) {
            if (dolls[right] == LION) {
                count++;
            }

            while (count >= K) {
                min = Math.min(min, right - left + 1);
                if (dolls[left] == LION) {
                    count--;
                }
                left++;
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}
