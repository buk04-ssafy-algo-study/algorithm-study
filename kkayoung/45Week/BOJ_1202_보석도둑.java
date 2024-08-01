// https://www.acmicpc.net/problem/1202
import java.io.*;
import java.util.*;

public class Main {

    static class Gem {
        int M, V;
        Gem(int M, int V) {
            this.M = M;
            this.V = V;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long answer = 0;

        List<Gem> list = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Gem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list, (o1, o2) -> o1.M-o2.M);

        int[] weight = new int[K];
        for(int i=0;i<K;i++) {
            weight[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(weight);

        PriorityQueue<Gem> pq = new PriorityQueue<>((o1, o2) -> o2.V-o1.V);
        int gemIdx = 0;
        for(int i=0;i<K;i++) {
            while(gemIdx<N && list.get(gemIdx).M<=weight[i]) {
                pq.offer(list.get(gemIdx));
                gemIdx++;
            }
            if(!pq.isEmpty()) answer += pq.poll().V;
        }

        System.out.println(answer);
    }

}
