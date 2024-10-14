import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21758 {
    static int[] arr; // 꿀 저장
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int left = leftHoney(); // 꿀통이 가장 왼쪽에 고정
        int right = rightHoney(); // 꿀통이 가장 오른쪽에 고정
        int mid = midHoney(); // 꿀통이 중간에 있을 때

        int max = Math.max(left, Math.max(right, mid)); // 가장 큰 값 출력
        System.out.println(max);
    }

    private static int midHoney() {
        int leftSum[] = new int[N+1]; // 왼쪽으로 더해진 꿀 양 저장
        int rightSum[] = new int[N+1]; // 오른쪽으로 더해진 꿀 양 저장

        for (int i = 2; i < N+1; i++)
            leftSum[i] = leftSum[i - 1] + arr[i];

        for (int i = N-1; i >= 1; i--)
            rightSum[i] = rightSum[i + 1] + arr[i];

        int max = 0;
        for (int i = 1; i < N+1; i++) // 꿀통이 i에 있을 때 최대 꿀 양
            max = Math.max(max, leftSum[i] + rightSum[i]);

        return max;
    }

    private static int rightHoney() {
        int[] sum = new int[N+1];

        for(int i=2;i<N+1;i++) // 왼쪽에서 오른쪽으로 꿀 누적합 계산
            sum[i] = sum[i-1]+arr[i-1];

        int allSum = sum[N]; // 총 꿀의 양
        int max = 0;
        for(int i=1;i<N;i++) // 자기 자신 위치 꿀 제외, i까지 꿀의 합 추가
            max = Math.max(max, allSum-arr[i] + sum[i]);

        return max;
    }

    private static int leftHoney() {
        int[] sum = new int[N+1];

        for(int i=N-1;i>=1;i--) // 오른쪽에서 왼쪽으로 꿀의 누적합 계산
            sum[i] = sum[i+1]+arr[i+1];

        int allSum = sum[1];
        int max = 0;
        for(int i=2;i<N+1;i++) // 자시 자신 위치 꿀 제외, i까지 꿀의 합 추가
            max = Math.max(max, allSum - arr[i] + sum[i]);

        return max;
    }
}
