import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        char[] arr = br.readLine().toCharArray();
        int[] cnt = new int[4]; // ACGT 갯수 조건

        st = new StringTokenizer(br.readLine());
        cnt[0] = Integer.parseInt(st.nextToken());
        cnt[1] = Integer.parseInt(st.nextToken());
        cnt[2] = Integer.parseInt(st.nextToken());
        cnt[3] = Integer.parseInt(st.nextToken());

        int res = 0;
        int left = 0; int right = P - 1;
        int[] check = new int[4]; // ACGT 갯수 카운트

        // 초기 P개 탐색
        for (int i = left; i <= right; i++) {
            switch (arr[i]) {
                case 'A':
                    check[0]++;
                    break;
                case 'C':
                    check[1]++;
                    break;
                case 'G':
                    check[2]++;
                    break;
                case 'T':
                    check[3]++;
                    break;
            }
        }

        int correct = 0;
        for (int i = 0; i < 4; i++)
            if (cnt[i] <= check[i]) correct++;
        if (correct >= 4) res++; // 조건 모두 일치하는 경우 

        while (right<S-1) { 
            switch (arr[++right]) { // right 오른쪽으로 한 칸 미루고 더하기
                case 'A':
                    check[0]++;
                    break;
                case 'C':
                    check[1]++;
                    break;
                case 'G':
                    check[2]++;
                    break;
                case 'T':
                    check[3]++;
                    break;
            }

            switch (arr[left++]) { // left 빼주고 오른쪽으로 한 칸 미루기
                case 'A':
                    check[0]--;
                    break;
                case 'C':
                    check[1]--;
                    break;
                case 'G':
                    check[2]--;
                    break;
                case 'T':
                    check[3]--;
                    break;
            }
            correct = 0;
            for (int i = 0; i < 4; i++)
                if (cnt[i] <= check[i]) correct++;
            if (correct >= 4) res++;
        }
        System.out.print(res);
    }
}