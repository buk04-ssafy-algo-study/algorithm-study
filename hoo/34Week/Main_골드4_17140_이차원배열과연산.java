package SSAFY.study.algo.week34;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_골드4_17140_이차원배열과연산 {

    static class Atom implements Comparable<Atom> { // HashMap에서 꺼낸 것을 정렬할 때 쓸 객체, PQ에 넣어서 우선순위 따질 것임
        int num;
        int count;

        public Atom(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Atom a) {  // 등장 횟수 같으면 숫자 크기 오름차순 정렬, 그 외에는 등장 횟수 오름차순 정렬
            if (this.count == a.count) return this.num-a.num;

            return this.count - a.count;
        }
    }

    static int r, c, k;
    static int[][] A;   // 배열 A의 원본
    static int rowSize = 3, colSize = 3;    // 행과 열의 길이, 초기에는 3x3임

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        A = new int[101][101];  // 행, 열의 크기가 1~100 이랬으므로 이렇게 지정
        for (int i = 1; i <= rowSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= colSize; j++) {
                int inputNumber = Integer.parseInt(st.nextToken());
                A[i][j] = inputNumber;
            }
        }
    }

    static void calcR() {
        HashMap<Integer, Integer> countMap;
        int maxColSize = 0; // R연산을 통해 갱신된 가장 긴 행의 길이(열 개수)
        for (int i = 1; i <= rowSize; i++) {
            countMap = new HashMap<>();
            for (int j = 1; j <= colSize; j++) {    // 한 행에 대하여 개수 카운트
                if (A[i][j] == 0) continue; // 0은 정렬처리 하지 않음
                if (countMap.containsKey(A[i][j])) countMap.replace(A[i][j], countMap.get(A[i][j]) + 1);
                else countMap.put(A[i][j], 1);
                A[i][j] = 0;    // 계산한 숫자는 0으로 초기화
            }
            maxColSize = Integer.max(maxColSize, countMap.size() * 2);
            PriorityQueue<Atom> pq = new PriorityQueue<>();
            for (Integer key : countMap.keySet()) pq.offer(new Atom(key, countMap.get(key)));
            int index = 1;
            while (!pq.isEmpty()) { // pq에 담은 모든 숫자에 대해 수행
                Atom atom = pq.poll();
                A[i][index++] = atom.num;   // 1 + 2n에는 숫자 저장
                A[i][index++] = atom.count; // 2n에는 횟수 저장
            }
        }
        colSize = maxColSize;
    }

    static void calcC() {
        HashMap<Integer, Integer> countMap;
        int maxRowSize = 0; // R연산을 통해 갱신된 가장 긴 행의 길이(열 개수)
        for (int i = 1; i <= colSize; i++) {
            countMap = new HashMap<>();
            for (int j = 1; j <= rowSize; j++) {    // 한 행에 대하여 개수 카운트
                if (A[j][i] == 0) continue; // 0은 정렬처리 하지 않음
                if (countMap.containsKey(A[j][i])) countMap.replace(A[j][i], countMap.get(A[j][i]) + 1);
                else countMap.put(A[j][i], 1);
                A[j][i] = 0;    // 계산한 숫자는 0으로 초기화
            }
            maxRowSize = Integer.max(maxRowSize, countMap.size() * 2);
            PriorityQueue<Atom> pq = new PriorityQueue<>();
            for (Integer key : countMap.keySet()) pq.offer(new Atom(key, countMap.get(key)));
            int index = 1;
            while (!pq.isEmpty()) { // pq에 담은 모든 숫자에 대해 수행
                Atom atom = pq.poll();
                A[index++][i] = atom.num;   // 1 + 2n에는 숫자 저장
                A[index++][i] = atom.count; // 2n에는 횟수 저장
            }
        }
        rowSize = maxRowSize;
    }

    static int solve() {
        for (int i = 0; i <= 100; i++) {
            if (A[r][c] == k) return i; // 정답 찾았을 경우 출력하고 함수 종료

            if (rowSize >= colSize) calcR();   // R연산 수행
            else calcC();  // C연산 수행
        }

        return -1; // 정답 못찾았을 경우 -1 출력
    }

}
