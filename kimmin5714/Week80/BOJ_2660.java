import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2660 {
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1) break;

            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        int[] score = new int[n + 1];
        int minScore = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            Queue<Integer> q = new ArrayDeque<>();

            // 1단계 친구 추가
            for (int j : adj.get(i)) {
                if (!visited[j]) {
                    visited[j] = true;
                    q.offer(j);
                }
            }

            for (int depth = 1; depth <= n; depth++) {
                int ret = allFriendCheck(visited, i, depth);
                if (ret != 0) {
                    score[i] = ret;
                    minScore = Math.min(minScore, ret);
                    break;
                }

                q = bfsLevel(q, adj, visited);
                if (q.isEmpty()) break;
            }
        }

        // 후보자 정리
        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (score[i] == minScore) {
                candidates.add(i);
            }
        }

        System.out.println(minScore + " " + candidates.size());
        for (int c : candidates) {
            System.out.print(c + " ");
        }
    }

    private static int allFriendCheck(boolean[] visited, int cur, int s) {
        for (int i = 1; i <= n; i++) {
            if (i != cur && !visited[i]) return 0;
        }
        return s;
    }

    private static Queue<Integer> bfsLevel(Queue<Integer> prevLevel, List<List<Integer>> adj, boolean[] visited) {
        Queue<Integer> nextLevel = new ArrayDeque<>();
        for (int cur : prevLevel) {
            for (int neighbor : adj.get(cur)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    nextLevel.offer(neighbor);
                }
            }
        }
        return nextLevel;
    }
}
