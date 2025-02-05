package SSAFY.study.algo.week70s.week71;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_23843_콘센트 {

    static int N, M;
    static int[] needTimes;

    public static void main(String[] args) throws IOException {
        init();
        calcMinTime();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        needTimes = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) needTimes[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(needTimes); // 필요한 시간 오름차순 정렬
    }

    static void calcMinTime() {
        if (N <= M) {   // 만약 콘센트 개수가 전자기기 개수보다 많거나 같게 있다면 제일 오래 걸리는 충전시간 출력 후 함수 종료
            System.out.println(needTimes[N-1]);
            return;
        }

        PriorityQueue<Integer> sockets = new PriorityQueue<>();  // 콘센트를 표현할 pq, 충전 빨리 끝나는 걸 빨리 빼주기 위해 오름차순 정렬
        for (int i = N-1; i > Math.max(0, (N-1)-M); i--) sockets.offer(needTimes[i]);

        int offerElectric = (N-1)-M;  // 다음 충전할 차례가 된 전자기기
        int needTime = 0;   // 충전에 필요한 시간
        int completedElectric;  // 충전 끝난 전자기기, 매 반복에서 pq 제일 앞의 전자기기를 사용
        while (!sockets.isEmpty()) {
            completedElectric = sockets.poll();
            needTime += completedElectric;

            int usingSocketElectric;
            List<Integer> stillUsingSocketElectricList = new ArrayList<>(); // 다음 차례에도 여전히 콘센트를 사용해야하는 전자기기 저장하는 리스트
            for (int i = 0; i < M-1; i++) {   // 콘센트 사용 중이던 M개 중 제일 시간 적게 걸리던 거는 앞에서 뽑았으니 M-1개만 반복
                usingSocketElectric = sockets.poll();
                usingSocketElectric -= completedElectric;
                if (usingSocketElectric >= 1) stillUsingSocketElectricList.add(usingSocketElectric);   // 충전 시간 남은 전자기기는 소켓에 넣기 전 임시로 리스트에 저장
            }
            for (int i = 0; i < stillUsingSocketElectricList.size(); i++) sockets.offer(stillUsingSocketElectricList.get(i));   // 충전 시간 남은 전자기기 소켓에 넣어줌

            int remainElectrics = sockets.size();   // 현재 소켓에 남은 전자기기들
            for (int i = 0; i < M-remainElectrics; i++) {
                sockets.offer(needTimes[offerElectric--]); // 남는 콘센트만큼 충전 차례가 된 전자기기를 넣어줌
                if (offerElectric < 0) break;
            }

            if (offerElectric < 0) break;   // 모든 전자기기 다 소켓에 넣었으면 종료
        }

        while (!sockets.isEmpty()) {    // 콘센트에 남은 전자기기 중 가장 충전 시간 오래 걸리는 전자기기의 충전시간 답에 더해줌
            if (sockets.size() == 1) needTime += sockets.poll();
            else sockets.poll();
        }

        System.out.println(needTime);
    }

    // 8 4 4
    // 4 4 4
    // 1 1 9

    // 7 6 5 5
    // 2 1 4 2
    // 1 3 3 1
    // 2 2 2 2
    // 1     1

}
