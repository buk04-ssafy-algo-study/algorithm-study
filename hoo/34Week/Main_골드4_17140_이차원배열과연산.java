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

    static void calc(boolean isRowOperation) {  // RowOperation이냐 아니냐에 따른 분기처리
        HashMap<Integer, Integer> countMap;
        int maxDimensionSize = 0;
        int primarySize = isRowOperation ? rowSize : colSize;
        int secondarySize = isRowOperation ? colSize : rowSize;

        for (int i = 1; i <= primarySize; i++) {
            countMap = new HashMap<>();
            for (int j = 1; j <= secondarySize; j++) {
                int value = isRowOperation ? A[i][j] : A[j][i];
                if (value == 0) continue;   // 0이면 카운트 x
                countMap.put(value, countMap.getOrDefault(value, 0) + 1);

                if (isRowOperation) A[i][j] = 0;    // 카운트 한 변수는 0으로 바꿔줌
                else A[j][i] = 0;
            }
            maxDimensionSize = Math.max(maxDimensionSize, countMap.size() * 2); // rowSize, colSize를 바꿔주기 위한 최대값 저장
            PriorityQueue<Atom> pq = new PriorityQueue<>();
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                pq.offer(new Atom(entry.getKey(), entry.getValue()));
            }

            int index = 1;
            while (!pq.isEmpty()) {
                Atom atom = pq.poll();
                if (isRowOperation) {
                    A[i][index++] = atom.num;   // 1 + 2n에는 숫자
                    A[i][index++] = atom.count; // 2n에는 카운트 저장
                } else {
                    A[index++][i] = atom.num;
                    A[index++][i] = atom.count;
                }
            }
        }

        if (isRowOperation) colSize = maxDimensionSize;
        else rowSize = maxDimensionSize;
    }


    static int solve() {
        for (int i = 0; i <= 100; i++) {
            if (A[r][c] == k) return i; // 정답 찾았을 경우 출력하고 함수 종료

            if (rowSize >= colSize) calc(true);   // R연산 수행
            else calc(false);  // C연산 수행
        }

        return -1; // 정답 못찾았을 경우 -1 출력
    }

}
