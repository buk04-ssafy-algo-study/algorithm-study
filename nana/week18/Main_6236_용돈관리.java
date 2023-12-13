import java.util.*;

public class Main_6236_용돈관리 {
    private static int[] num;

    private static int calc(int mid) {
        int count = 1;
        int money = mid;

        for (int n : num) {
            money -= n;
            if (money < 0) {
                count++;
                money = mid - n;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = 0;

        num = new int[N];
        int left = 0, right = 10000 * 100000;

        for (int i = 0; i < N; i++) {
            num[i] = sc.nextInt();
            left = Math.max(left, num[i]);
        }

        while (left <= right) {
            int mid = (left + right) / 2;

            if (M >= calc(mid)) {
                K = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(K);
    }
}
