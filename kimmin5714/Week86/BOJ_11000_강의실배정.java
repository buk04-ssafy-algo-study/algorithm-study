import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11000_강의실배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] time = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());
            time[i][1] = Integer.parseInt(st.nextToken());
        }

        // 시작 시간 오름차순, 종료 시간 오름차순 정렬
        Arrays.sort(time, (o1, o2)->{
            if(o1[0] == o2[0]) return o1[1]-o2[1];
            return o1[0] - o2[0];
        });

        // 끝나는 시간 오름차순 저장
        PriorityQueue<Integer> endPq = new PriorityQueue<>();
        endPq.offer(time[0][1]);

        for(int i=1;i<N;i++) {
            // 강의 끝나는 시간(pq)이 i번째 강의 시작 시간보다 작거나 같으면 pq에서 제거
            if(time[i][0]>=endPq.peek()) endPq.poll();

            // i번째 강의 종료 시간 추가
            endPq.offer(time[i][1]);
        }

        // pq크기가 필요한 강의실 갯수
        System.out.println(endPq.size());
    }
}