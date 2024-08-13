package SSAFY.study.algo.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_실버3_1021_회전하는큐 {

    static int N, M;
    static Deque<Integer> queue;
    static int[] pollList;

    public static void main(String[] args) throws IOException {
        init();
        int count = solve();
        System.out.println(count);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        queue = new ArrayDeque<>();
        pollList = new int[M];
        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            pollList[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static int findWillPoll(int willPoll) { // 뽑아야하는 수 인덱스 찾는 함수
        int willPollIdx = 0;    // 뽑아낼 원소의 큐에서의 위치
        Iterator iter = queue.iterator();
        while (iter.hasNext()) {    // 찾는 원소의 인덱스 찾기
            if ((int) iter.next() == willPoll) break;
            willPollIdx += 1;
        }

        return willPollIdx;
    }

    static void rotateQueue(String direction) { // 큐(덱) 돌리는 함수
        if (direction.equals("left")) queue.offer(queue.poll());
        else if (direction.equals("right")) queue.offerFirst(queue.pollLast());
    }

    static int solve() {
        int count = 0;
        for (int i = 0; i < M; i++) {
            int willPoll = pollList[i];
            while (!(queue.peek() == willPoll)) {   // 찾는 원소가 나오기 큐의 맨앞에 오기 전까지
                int willPollIdx = findWillPoll(willPoll);
                if (willPollIdx <= queue.size()/2) rotateQueue("left"); // 찾는 원소의 인덱스가 그 시점의 큐 사이즈를 2로 나눈 몫보다 작거나 같으면 왼쪽으로 회전
                else rotateQueue("right");  // 반대의 경우는 오른쪽으로 회전
                count += 1;
            }
            queue.poll();   // 첫 번째 원소로 만들었으면 뽑기
        }

        return count;
    }

}
