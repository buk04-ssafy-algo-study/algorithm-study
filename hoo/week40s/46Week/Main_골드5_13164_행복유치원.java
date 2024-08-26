package SSAFY.study.algo.week46;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_골드5_13164_행복유치원 {

    static int N, K;
    static int[] children;
    static int[] diff;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        init();
        int answer = 0;
//        for (int i = 0; i < N-K; i++) answer += diff[i];    // 작은 차이의 원생들부터 그룹으로 묶어줌.(인접한 원생들끼리는 같은 그룹으로 묶어주며 그룹의 비용을 초기화하는 효과와 동일)
        for (int i = 0; i < N-K; i++) answer += pq.poll();
        System.out.println(answer);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        children = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) children[i] = Integer.parseInt(st.nextToken());
//        diff = new int[N-1];
//        for (int i = 1; i < N; i++) diff[i-1] = children[i] - children[i-1];    // 인접한 두 원생 간 키 차이를 이용하기 위해 배열 초기화
//        Arrays.sort(diff);  // 저장한 차이를 오름차순 정렬
        pq = new PriorityQueue<>();
        for (int i = 1; i < N; i++) pq.offer(children[i] - children[i-1]);
    }

}
