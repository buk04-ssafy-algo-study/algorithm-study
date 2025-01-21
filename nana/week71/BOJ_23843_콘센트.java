import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        PriorityQueue<Integer> device = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> charge = new PriorityQueue<>();  // 가장 먼저 끝나는 애는 뽑아야함

        for (int i = 0; i < N; i++) {
            device.add(sc.nextInt());   // 충전이 가장 오래 걸리는 기기부터 충전
        }

        int time = 0;

        while (!device.isEmpty()) {

            // 콘센트에 자리가 없으면, 가장 시간이 적게 남은 기기를 뽑는다.
            if (charge.size() == M) {
                time = charge.poll();
            }

            // 먼저 뽑은 기기 충전시간 + 이제 꽂을 기기 충전시간 = 전체 충전시간
            charge.add(device.poll() + time);
        }

        while (!charge.isEmpty()) {
            // 제일 마지막 기기 충전시간 = 전체 충전시간
            time = charge.poll();
        }

        System.out.println(time);
    }
}
