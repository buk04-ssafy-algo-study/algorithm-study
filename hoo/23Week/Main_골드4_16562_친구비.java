package SSAFY.study.algo.week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드4_16562_친구비 {

    static class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return this.from + " " + this.to;
        }
    }

    static int N, M, k;
    static int[] fees;  // 친구비(크기 N+1로 만들기)
    static List<List<Edge>> network;
    static boolean[] friendCheck;   // 친구가 됐는지 여부 체크

    public static void main(String[] args) throws IOException {
        init();
        int minimumFee = 0; // 한 관계를 맺었을 때 가장 최소 비용들을 다 더해줄 변수
        for (int i = 1; i < N+1; i++) {
            if (friendCheck[i]) continue;   //이미 친구가 됐으면 패스
            List<Integer> tempFriends = bfs(i);
            minimumFee += findMinumum(tempFriends); // 한 관계 안에서의 최소 비용
        }
        if (minimumFee > k) System.out.println("Oh no");
        else System.out.println(minimumFee);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        fees = new int[N+1];    // 친구 관계가 1부터 주어져서 N+1로
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) fees[i] = Integer.parseInt(st.nextToken());
        network = new ArrayList<>();
        for (int i = 0; i < N+1; i++) network.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            network.get(from).add(new Edge(from, to));
            network.get(to).add(new Edge(to, from));
        }
        friendCheck = new boolean[N+1];
    }

    static List<Integer> bfs(int startNum) { // startNum : 탐색 시작할 사람 번호
        List<Integer> tempFriends = new ArrayList<>();
        tempFriends.add(startNum);
        friendCheck[startNum] = true;
        Queue<Edge> queue = new ArrayDeque<>();
        for (int i = 0; i < network.get(startNum).size(); i++) {
            if (friendCheck[network.get(startNum).get(i).to]) continue;
            queue.offer(network.get(startNum).get(i));
            friendCheck[network.get(startNum).get(i).to] = true;
            tempFriends.add(network.get(startNum).get(i).to);
        }
        while (!queue.isEmpty()) {
            Edge now = queue.poll();

            for (int i = 0; i < network.get(now.to).size(); i++) {
                Edge next = network.get(now.to).get(i);
                if (friendCheck[next.to]) continue;
                queue.offer(next);
                friendCheck[next.to] = true;
                tempFriends.add(next.to);
            }
        }

        return tempFriends;
    }

    static int findMinumum(List<Integer> tempFriends) {
        int minimumFee = Integer.MAX_VALUE;
        for (int i = 0; i < tempFriends.size(); i++) {
            minimumFee = Math.min(minimumFee, fees[tempFriends.get(i)]);
        }

        return minimumFee;
    }

}
