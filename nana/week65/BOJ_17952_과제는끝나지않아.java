import java.util.*;

class Main {

    private static int N;   // 총 몇분인지

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        int res = 0;

        PriorityQueue<Task> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (sc.nextInt() == 1) {
                // 과제가 주어졌으면 큐에 넣는다
                int A = sc.nextInt();
                int T = sc.nextInt();

                pq.add(new Task(i, A, T));
            }   // 주어지지 않았다면 그대로 진행


            if (!pq.isEmpty()) {
                Task now = pq.poll();

                now.due--;  // 과제를 한번 시켰음

                if (now.due == 0) {
                    // 과제를 완료했다면
                    res += now.score;
                } else {
                    pq.add(now);
                }
            }
        }

        System.out.println(res);
    }

    public static class Task implements Comparable<Task> {
        int time, score, due;

        public Task(int time, int score, int due) {
            this.time = time;
            this.score = score;
            this.due = due;
        }

        @Override
        public int compareTo(Task o) {
            return o.time - this.time;
        }
    }
}
