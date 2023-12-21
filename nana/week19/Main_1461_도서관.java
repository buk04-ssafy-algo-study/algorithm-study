import java.util.*;

public class Main_1461_도서관 {

    private static int N, M, res;
    private static PriorityQueue<Integer> positive, negative;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        res = 0;

        // 우선순위 큐 내림차순
        positive = new PriorityQueue<>((o1, o2) -> o2 - o1);
        negative = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < N; i++) {
            int temp = sc.nextInt();
            if (temp < 0) negative.add(temp * -1);
            else positive.add(temp);
        }

        // 원점에서 가장 먼 곳은 편도로 이동
        int max = 0;
        if (positive.isEmpty()) max = negative.peek();
        else if (negative.isEmpty()) max = positive.peek();
        else max = Math.max(negative.peek(), positive.peek());

        // 총 M개의 책을 들기 때문에
        // 가장 먼 책 한권을 빼서 * 2 (왕복) 한 뒤 결과에 더함
        // M - 1 권은 그냥 뽑기만 한다
        while (!positive.isEmpty()) {
            int temp = positive.poll();
            for (int i = 0; i < M - 1; i++) {
                if (!positive.isEmpty())
                    positive.poll();
            }
            res += temp * 2;
        }

        while (!negative.isEmpty()) {
            int temp = negative.poll();
            for (int i = 0; i < M - 1; i++) {
                if (!negative.isEmpty())
                    negative.poll();
            }
            res += temp * 2;
        }


        // 가장 먼 곳의 책은 편도로 가져오는 게 좋다
        // 왕복으로 미리 계산을 했기 때문에 책을 한권 뽑는다
        res -= max;

        System.out.println(res);

    }
}
