package SSAFY.study.algo.week20s.week26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_골드4_12851_숨바꼭질2 {

    static int N, K;
    static int minTime;
    static int count;
    static int[] isVisited; // 해당 좌표까지 가는 데 걸린 시간 저장

    public static void main(String[] args) throws Exception {
        init();
        bfs();
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        minTime = Integer.MAX_VALUE;
        count = 0;
        isVisited = new int[100001];
    }

    static boolean isOuted(int axis) {
        if (0 <= axis && axis <= 100000) return false;
        return true;
    }

    private static Queue<Integer> doQueue(Queue<Integer> queue, int now, int next) {
        if (isOuted(next)) return queue;
        if (isVisited[next] == 0 || isVisited[next] == isVisited[now] + 1) {
            queue.offer(next);
            isVisited[next] = isVisited[now] + 1;
        }

        return queue;
    }

    static void bfs() {
        int[] dir = new int[] {1, -1};   // 걸어가는 경우에 쓰일 벡터
        Queue<Integer> queue = new ArrayDeque<>();    // 0: 위치, 1: 이동한 횟수
        queue.offer(N);
        isVisited[N] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (isVisited[now] > minTime) continue; // 이미 최소 시간보다 많이 걸리는 경우는 이동 고려할 필요 없음
            if (now == K) { // 동생 있는 곳에 도착한 경우
                minTime = isVisited[now];   // 앞에서 이미 최소 시간보다 많이 걸리는 경우 걸렀으므로 여기서는 비교 없이 바로 갱신
                count += 1;
            }

            if (now != 0) { // 0일 때 텔레포트 해도 0인데, 이때를 이동 안하게 처리 안해줘서 count가 올라가고 있었음. 그래서 53%에서 틀림
                int nextWhenTeleport = now * 2;
                queue = doQueue(queue, now, nextWhenTeleport);
            }

            for (int d = 0; d < 2; d++) {
                int nextWhenWalk = now + dir[d];
                queue = doQueue(queue, now, nextWhenWalk);
            }
        }

        System.out.println(minTime);
        System.out.println(count);
    }

}
