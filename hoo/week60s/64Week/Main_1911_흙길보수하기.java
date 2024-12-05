package SSAFY.study.algo.week60s.week64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1911_흙길보수하기 {

    static int N, L;
    static PriorityQueue<int[]> ponds;

    public static void main(String[] args) throws IOException {
        init();
        calcMinPlates();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        ponds = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                if (i1[0] == i2[0]) return i1[1] - i2[1];   // 시작 위치 같으면 끝 위치 기준 오름차순 정렬
                return i1[0] - i2[0];   // 시작 위치 기준 오름차순 정렬
            }
        });
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ponds.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
    }

    static void calcMinPlates() {
        int nowAxis = 0;
        int plateCount = 0;

        int[] nowPond;
        while (!ponds.isEmpty()) {
            nowPond = ponds.poll();

            while (true) {
                if (nowAxis >= nowPond[1]) break;   // 현재 웅덩이까지는 이미 덮었으면 종료

                if (nowAxis < nowPond[0]) nowAxis = nowPond[0]; // 웅덩이 시작위치보다 앞에 있으면 현재 웅덩이 덮으러 이동
                nowAxis += L;
                plateCount++;
            }
        }
        System.out.println(plateCount);
    }

}
