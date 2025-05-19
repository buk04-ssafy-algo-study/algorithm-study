import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_22252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        Long answer = 0L;
        Map<String, PriorityQueue<Integer>> map = new HashMap<>();

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            if (N == 1) {
                int K = Integer.parseInt(st.nextToken());
                if (!map.containsKey(name)) {
                    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
                    for (int k = 0; k < K; k++)
                        pq.offer(Integer.parseInt(st.nextToken()));
                    map.put(name, pq);
                } else {
                    PriorityQueue<Integer> pq = map.get(name);
                    for (int k = 0; k < K; k++)
                        pq.offer(Integer.parseInt(st.nextToken()));
                }
            } else {
                int B = Integer.parseInt(st.nextToken());
                if(map.containsKey(name)) {
                    PriorityQueue<Integer> pq = map.get(name);

                    for (int i = 0; i < B && !pq.isEmpty(); i++) {
                        answer += pq.poll();
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
