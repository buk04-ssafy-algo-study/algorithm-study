/*
위상 정렬 알고리즘
- 싸이클 없는 방향 그래프에서 노드 간의 순서를 결정
- 순서 보장 못함

1. 인접 리스트, 진입 차수 배열 초기화
2. 큐 생성
3. 진입 차수가 0인 정점 큐에 넣기
4. 큐에서 poll : 그 노드에 인접한 노드들 진입 차수 -1
5. 진입 차수가 0이면 큐에 추가
6. 큐에서 poll한 값들이 위상 정렬된 결과
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2252 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<=n;i++)
            adj.add(new ArrayList<>());

        int[] degree = new int[n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj.get(a).add(b);
            degree[b]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1;i<=n;i++){
            if(degree[i] == 0)
                q.offer(i);
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            System.out.print(cur+" ");

            for(int i=0;i<adj.get(cur).size();i++){
                int s = adj.get(cur).get(i);
                degree[s] --;
                if(degree[s] == 0) q.offer(s);
            }
        }
    }
}