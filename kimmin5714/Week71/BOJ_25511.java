import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_25511 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<List<Integer>> adj = new ArrayList<>(); // 인접 리스트
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj.get(p).add(c); // 부모에 자식 추가
        }

        int findIdx = -1;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp == k) { // 찾으려는 노드의 인덱스 저장
                findIdx = i;
                break;
            }
        }

        // bfs
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0}); // 노드 번호, 깊이 저장

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curNum = cur[0];
            int curDepth = cur[1];

            if (curNum == findIdx) { // 가장 처음에 찾는 값이 나오는 경우
                System.out.print(curDepth);
                return;
            }

            List<Integer> next = adj.get(curNum);
            for (int i = 0; i < next.size(); i++) { // 인접한 노드 확인
                int newNum = next.get(i);

                if (newNum == findIdx) { // 찾으려는 노드인 경우 출력하고 종료
                    System.out.print(curDepth + 1);
                    return;
                }

                q.offer(new int[]{newNum, curDepth + 1}); // 큐에 추가
            }
        }
    }
}
