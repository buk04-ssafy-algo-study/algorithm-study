import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();    // 단원 개수
        int T = sc.nextInt();   // 공부할 수 있는 총 시간

        // ** 한 단원에 한 문제를 출제한다. 단, 그 단원의 모든 내용을 알고 있어야 한다.
        // 0-1 배낭문제

        List<int[]> subject = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int K = sc.nextInt();   // 예상 공부 시간
            int S = sc.nextInt();   // 문제의 배점

            subject.add(new int[]{K, S});
        }

        int[] dp = new int[T + 1];

        for (int[] s : subject) {
            for (int i = T; i >= s[0]; i--) {
                dp[i] = Math.max(dp[i - s[0]] + s[1], dp[i]);
            }
        }

        System.out.println(dp[T]);
    }
}
