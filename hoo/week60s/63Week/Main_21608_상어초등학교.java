package SSAFY.study.algo.week60s.week63;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_21608_상어초등학교 {

    static int N;
    static int[] students;   // 주어지는 학생 순서대로 저장
    static int[][] likeStudents;    // 각 학생이 좋아하는 학생 여부를 0, 1로 저장하는 배열

    public static void main(String[] args) throws IOException {
        init();
        assignSits();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        students = new int[N*N];
        likeStudents = new int[N*N+1][N*N+1];
        StringTokenizer st;
        for (int i = 0; i < N*N; i++) {
            st = new StringTokenizer(br.readLine());
            students[i] = Integer.parseInt(st.nextToken());
            for (int j = 1; j < 5; j++) {
                likeStudents[students[i]][Integer.parseInt(st.nextToken())] = 1;    // 학생 별 좋아하는 학생은 1로 마크
            }
        }
    }

    static void assignSits() {
        int[][] sit = new int[N][N];
        sit[1][1] = students[0];   // 제일 첫 학생은 무조건 1, 1에 앉힘.

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() { // 타입인 int[]의 0에는 row, 1에는 col, 2에는 비어있는 칸 수, 3에는 좋아하는 학생 수
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[3] == o2[3]) {   // 좋아하는 학생 수가 같은 좌석 수가 같으면
                    if (o1[2] == o2[2]) {   // 그 와중에 비어있는 칸 수도 같다면
                        if (o1[0] == o2[0]) return o1[1] - o2[1];   // 그 와중에 행도 같다면 열을 기준으로 선택
                        return o1[0] - o2[0];   // 행 기준 선택
                    }
                    return o2[2] - o1[2];   // 비어있는 칸 수 내림차순 기준 선택
                }

                return o2[3] - o1[3];   // 좋아하는 학생 수 내림차순 기준 선택
            }
        });

        int[][] assignedSits = new int[N*N + 1][2];  // 학생의 번호와 같은 인덱스에 해당 학생의 좌석 저장
        assignedSits[students[0]] = new int[] {1, 1};    // 제일 첫 학생 좌석 저장
        for (int student : students) {   // 제일 첫 학생 이후 모든 학생들에 대해 진행
            if (student == students[0]) continue; // 제일 첫 학생은 이미 앉혔으므로 건너 뜀
            pq.clear(); // pq 비우고 시작
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (sit[i][j] != 0) continue;   // 이미 배정된 좌석은 건너 뜀
                    pq.offer(calcSitScore(sit, student, i, j));
                }
            }
            int[] assignedSit = pq.poll();
            sit[assignedSit[0]][assignedSit[1]] = student;
            assignedSits[student] = new int[] {assignedSit[0], assignedSit[1]};
        }

        System.out.println(calcSatisfaction(sit, assignedSits));
    }

    static int[] calcSitScore(int[][] sit, int student, int row, int col) {   // 좌석의 점수를 계산하는 함수
        int[] sitScore = new int[4];
        sitScore[0] = row;
        sitScore[1] = col;

        int[] dirRow = new int[] {-1, 0, 1, 0}; // 상 우 하 좌
        int[] dirCol = new int[] {0, 1, 0, -1};
        int nextRow, nextCol;
        for (int d = 0; d < 4; d++) {
            nextRow = row + dirRow[d];
            nextCol = col + dirCol[d];
            if (isOuted(nextRow, nextCol)) continue;   // 범위 밖은 체크 x

            if (likeStudents[student][sit[nextRow][nextCol]] == 1) sitScore[3]++; // 좋아하는 학생 점수 추가
            if (sit[nextRow][nextCol] == 0) sitScore[2]++;  // 비어있는 좌석 점수 추가
        }

        return sitScore;
    }

    static int calcSatisfaction(int[][] sit, int[][] assignedSits) {
        int satisfaction = 0;

        int[] dirRow = new int[] {-1, 0, 1, 0}; // 상 우 하 좌
        int[] dirCol = new int[] {0, 1, 0, -1};
        int nearCount;
        int[] assignedSit;
        for (int student : students) {
            assignedSit = assignedSits[student];
            nearCount = 0;
            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = assignedSit[0] + dirRow[d];
                nextCol = assignedSit[1] + dirCol[d];
                if (isOuted(nextRow, nextCol)) continue;
                if (likeStudents[student][sit[nextRow][nextCol]] == 1) nearCount++; // 좋아하는 학생 점수 추가
            }
            if (nearCount != 0) satisfaction += Math.pow(10, (nearCount-1));
        }

        return satisfaction;
    }

    static boolean isOuted(int row, int col) {
        if ((0 > row || row >= N) || (0 > col || col >= N)) return true;

        return false;
    }

}
