import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13975 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        int T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {

            PriorityQueue<Long> pq = new PriorityQueue<>();

            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<K;i++){
                pq.add(Long.parseLong(st.nextToken()));
            }

            Long sum = 0L;
            while (pq.size() > 1) {
                Long a = pq.poll();
                Long b = pq.poll();
                sum += a + b;
                pq.add(a + b);
            }

            System.out.println(sum);
        }
    }
}