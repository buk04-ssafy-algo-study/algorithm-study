package SSAFY.study.algo.week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드5_7511_소셜네트워킹어플리케이션_메모리초과 {

    static class Edge { // 주어지는 유저 수(노드)보다 친구 관계의 수(간선)가 더 작으므로 간선리스트(무방향)로 연결 정보 나타냄
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    static int n, k, m; // n: 유저 수, k: 친구 관계 수, m: 미리 구할 쌍의 수
    static List<List<Edge>> friendList;    // 친구 관계 저장할 리스트
    static int[][] haveToSearch;    // 구해야 하는 친구 쌍

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            sb.append("Scenario " + t + ":").append("\n");
            init(br);
            for (int i = 0; i < haveToSearch.length; i++) {
                int start = haveToSearch[i][0];
                int end = haveToSearch[i][1];
                if (find(start, end)) sb.append("1").append("\n");
                else sb.append("0").append("\n");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void init(BufferedReader br) throws IOException {
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        friendList = new ArrayList<>();
        for (int i = 0; i < n; i++) friendList.add(new ArrayList<>());
        for (int i = 0; i < k; i++) {   // 주어지는 친구 관계
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friendList.get(a).add(new Edge(a, b));
            friendList.get(b).add(new Edge(b, a));
        }
        m = Integer.parseInt(br.readLine());
        haveToSearch = new int[m][2];
        for (int i = 0; i < m; i++) {   // 구해야 하는 친구쌍
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            haveToSearch[i] = new int[] {u, v};
        }
    }

    static boolean find(int start, int end) {    // 친구 관계가 연결돼있으면 1, 아니면 0
        boolean[] isVisited = new boolean[n];
        isVisited[start] = true;
        Queue<Edge> queue = new ArrayDeque<>();
        for (int i = 0; i < friendList.get(start).size(); i++) {
            queue.offer(friendList.get(start).get(i));
        }
        while (!queue.isEmpty()) {
            Edge now = queue.poll();
            if (now.to == end) {
                return true;
            }
            if (isVisited[now.to]) continue;
            isVisited[now.to] = true;
            for (int i = 0; i < friendList.get(now.to).size(); i++) queue.offer(friendList.get(now.to).get(i));
        }

        return false;
    }

}
