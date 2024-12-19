import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] indegree = new int[N+1]; // 진입차수 저장
        List<Integer>[] connect = new ArrayList[N+1]; // 해결 해야 할 다음 문제
        for(int i=1;i<=N;i++)
            connect[i] = new ArrayList<>();

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 쉬운 일부터 처리하기 위한 우선순위큐

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            connect[A].add(B); // 다음 일 저장
            indegree[B]++; // 진입차수 증가
        }

        // 위상정렬
        for(int i=1;i<=N;i++)
            if(indegree[i] == 0) pq.offer(i); // 진입차수가 0인 노드 추가

        while(!pq.isEmpty()) {
            int cur = pq.poll();
            List<Integer> nextList = connect[cur];
            sb.append(cur+" ");

            for(int i=0;i<nextList.size();i++) { // 현재 노드에서 나온 간선 모두 지우기
                int next = nextList.get(i);
                if (--indegree[next] == 0) // 진입차수 갱신 후 0이된 노드 추가
                        pq.offer(next);
            }
        }
        System.out.print(sb);
    }
}
