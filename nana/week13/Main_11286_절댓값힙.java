import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main_11286_절댓값힙 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 음수는 작을수록 절댓값이 크고, 양수는 클수록 절댓값이 크기 때문에 큐를 따로 생성
        // 음수를 넣는 큐는 내림차순으로 바꿔준다
        PriorityQueue<Integer> minus = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> plus = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x > 0) {    // x가 양수일 때 양수큐에 넣고
                plus.add(x);
            } else if (x < 0) { // x가 음수일 때는 음수큐에 넣음
                minus.add(x);
            } else {    // x==0인 경우
                if (minus.isEmpty() && plus.isEmpty()) {    // 큐가 모두 비었으면 0 반환
                    sb.append(0).append("\n");
                } else if (minus.isEmpty()) {   // 음수 큐가 비었다면
                    sb.append(plus.poll()).append("\n");    // 양수 중 가장 절댓값이 작은 수 반환
                } else if (plus.isEmpty()) {    // 양수 큐가 비었다면
                    sb.append(minus.poll()).append("\n");   // 음수 중 가장 절댓값이 작은 수 반환
                } else {    // 둘 다 비지 않았을 때
                    int p = Math.abs(plus.peek());  // 양수큐의 가장 절댓값이 작은 수와
                    int m = Math.abs(minus.peek()); // 음수큐의 가장 절댓값이 작은 수 비교

                    if (p < m) {    // 양수의 절댓값이 더 작은 경우 양수큐에서 뺌
                        sb.append(plus.poll()).append("\n");
                    } else {    // 음수의 절댓값이 작거나, 두 절댓값이 같은 경우 음수큐에서 뺌
                        sb.append(minus.poll()).append("\n");
                    }
                }
            }
        }
        System.out.println(sb);
    }
}
