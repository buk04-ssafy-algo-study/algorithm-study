import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Gem
{
    int m,v;

    public Gem(int m, int v) {
        this.m = m;
        this.v = v;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Gem> gems = new ArrayList<>();
        for(int i = 0; i < N; ++i)
        {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            gems.add(new Gem(m,v));
        }

        gems.sort((o1, o2) -> {
            if (o1.m == o2.m) return o2.v - o1.v;
            return o1.m - o2.m;
        });

        List<Integer> bags = new ArrayList<>();
        for(int i = 0; i < K; ++i)
        {
            bags.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(bags);

        int gem_idx = 0;
        int gem_size = gems.size();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long answer = 0;
        for(int bag : bags)
        {
            while(gem_idx < gem_size && bag >= gems.get(gem_idx).m) pq.add(gems.get(gem_idx++).v);

            if(!pq.isEmpty()) answer += pq.poll();
        }
        System.out.println(answer);
    }
}