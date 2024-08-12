import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1715 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순

        while(n-- > 0)
            pq.offer(Integer.parseInt(br.readLine()));

        int sum = 0;
        while(pq.size() > 1) { // 숫자가 2개인 경우 비교
            int tmp = pq.poll() + pq.poll();
            sum+=tmp;
            pq.offer(tmp);
        }

        System.out.println(sum);
    }
}