import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int A[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int newA[] = new int[N]; // 각 인덱스에서 최장증가수열의 합 저장
        newA[0] = A[0]; // 초기화

        for (int i = 1; i < N; i++) {
            newA[i] = A[i]; // 현재보다 앞에서 더 작은 수가 한 번도 안 나올 경우 초기화
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) { // 현재보다 앞에서 더 작은 수가 나왔었는지 확인
                    newA[i] = Math.max(newA[i], newA[j] + A[i]);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            res = Math.max(res, newA[i]);
        }
        System.out.print(res);
    }
}
