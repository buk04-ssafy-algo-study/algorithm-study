package SSAFY.study.algo.week48;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_골드5_1092_배 {

    static int N, M;
    static Integer[] cranes;
    static Integer[] boxes;

    public static void main(String[] args) throws IOException {
        init();
        moveBoxes();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cranes = new Integer[N];    // Collections.reverseOrder()를 사용하기 위해서는 Wrapper class 형태여야 함.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) cranes[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(cranes, Collections.reverseOrder());
        M = Integer.parseInt(br.readLine());
        boxes = new Integer[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) boxes[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(boxes, Collections.reverseOrder());
    }

    static void moveBoxes() {
        if (cranes[0] < boxes[0]) { // 가장 무거운 박스를 들 수 없는 경우 -1 출력 후 리턴
            System.out.println(-1);
            return;
        }

        int answer = 0;
        int count = 0;
        boolean[] removed = new boolean[M];  // 박스가 처리되었는지 여부를 추적하는 배열

        while(count < M){
            int craneIdx = 0;

            for(int boxIdx = 0; boxIdx < M; boxIdx++) {
                if (craneIdx == N) break;  // 모든 크레인이 할당되었으면 종료
                if (!removed[boxIdx] && cranes[craneIdx] >= boxes[boxIdx]) {
                    // 아직 처리되지 않았고 크레인이 들 수 있는 박스를 찾으면
                    removed[boxIdx] = true;  // 해당 박스를 처리된 것으로 표시
                    craneIdx++;
                    count++;
                }
            }
            answer++;
        }

        System.out.println(answer);
    }

}
