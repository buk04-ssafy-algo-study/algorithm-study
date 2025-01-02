import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2023 {
    private static int n;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        int[] prime = new int[]{2, 3, 5, 7};

        for(int i=0;i<4;i++)
            dfs(prime[i]); // 소수로 시작하는 수

        System.out.print(sb);
    }

    private static void dfs(int num) {
        if (String.valueOf(num).length() == n) {
            sb.append(num + "\n");
            return;
        }
        for (int i = 1; i <= 9; i++) {
            int newNum = num * 10 + i;
            if (isPrime(newNum))
                dfs(newNum);
        }
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
