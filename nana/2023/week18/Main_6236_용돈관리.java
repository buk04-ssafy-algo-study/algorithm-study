import java.util.*;

public class Main_6236_용돈관리 {
    private static int[] num;
    private static int calc(int mid) {
        int count = 1; // 이미 1회 인출
        // 인출액을 money 변수에 저장
        int money = mid;

        for (int n : num) {
            // 해당 날짜의 금액만큼 사용
            money -= n;
            if (money < 0) {
                // 만약 금액이 음수가 된다면 인출
                count++;
                // money = 인출액 - 사용금액
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
            // 가장 돈을 많이 써야하는 날보다 인출액은 무조건 크다
            left = Math.max(left, num[i]);
        }

        while (left <= right) {
            int mid = (left + right) / 2;

            // 지정 횟수보다 적거나 같은 횟수로 인출했다면 K 갱신
            if (M >= calc(mid)) {
                K = mid;
                right = mid - 1;
            } else {
                // 지정 횟수보다 많이 인출했다면 인출금액을 더 크게 잡음
                left = mid + 1;
            }
        }

        System.out.println(K);
    }
}
