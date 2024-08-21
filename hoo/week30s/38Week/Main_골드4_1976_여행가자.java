package SSAFY.study.algo.week10s.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_골드4_1976_여행가자 {

    static int N, M;
    static int[][] cities;  // 도시 간 연결 여부
    static int[] plan;  // 여행 계획

    public static void main(String[] args) throws IOException {
        init();
        boolean answer = travel();
        if (answer) {
            System.out.println("YES");
            return;
        }

        System.out.println("NO");
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        cities = new int[N+1][N+1]; // 도시 번호는 1번부터 시작하므로
        plan = new int[M];
        StringTokenizer st;
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) cities[i][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) plan[i] = Integer.parseInt(st.nextToken());
    }

    static boolean bfs(int from, int to) {  // from 도시에서 to 도시로 갈 수 있는지 판별 -> 싸이클 조심할 것
        boolean isPossible = false; // 해당 도시로 갈 수 있는지 여부 판단, 갈 수 있을 시 true로 값 변경
        boolean[] isVisited = new boolean[N+1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(from);
        isVisited[from] = true;

        while (!queue.isEmpty()) {
            if (isPossible) break;  // 목적지 도시로 갈 수 있다고 판단됐으면 반복문 종료
            int nowCity = queue.poll();
            for (int i = 1; i < cities[nowCity].length; i++) {  // 현재 도시랑 연결된 도시 여부 확인
                int nextCityConnected = cities[nowCity][i];
                if (nextCityConnected == 1) {    // 연결된 도시면 큐에 삽입, 자기 자신을 다시 거쳐서 다른 도시로 가는 경우도 있으므로 해당 경우 체크 -> 내일 다시 하기
                    if (i == to) {  // 그 와중에 목적지 도시이면 플래그 true로 바꾸고 반복문 종료
                        isPossible = true;
                        break;
                    }
                    if (!isVisited[i]) {
                        queue.offer(i);
                        isVisited[i] = true;
                    }
                }
            }
        }

        return isPossible;
    }

    static boolean travel() {
        boolean isPossible = true;
        for (int i = 0; i < M-1; i++) {
            int from = plan[i];
            int to = plan[i+1];
            isPossible = bfs(from, to);
            if (isPossible == false) break;
        }

        return isPossible;
    }

}
