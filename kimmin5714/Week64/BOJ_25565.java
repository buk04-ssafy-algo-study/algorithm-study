import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
    int r, c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Point{" +
                "r=" + r +
                ", c=" + c +
                '}';
    }

    @Override
    public int compareTo(Point o) {
        if (this.r == o.r) return this.c - o.c;
        return this.r - o.r;
    }
}

public class BOJ_25565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int arr[][] = new int[N][M];
        PriorityQueue<Point> togetherGrow = new PriorityQueue<Point>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        boolean[][][] check = new boolean[N][M][2];
        int rowExist = 0, colExist = 0, saveRow = 0, saveCol = 0;

        for (int i = 0; i < N; i++) {
            int cnt = 0;
            int startIdx = -1; // 연속된 1의 시작 인덱스

            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) {
                    if (cnt == 0) startIdx = j; // 첫 번째 1이 나온 위치 기록
                    cnt++; // 연속된 1의 개수 증가
                } else {
                    cnt = 0; // 1이 끊어지면 연속된 1의 개수 초기화
                }

                // 연속된 1의 개수가 K에 도달하면 check를 true로 설정
                if (cnt >= K) {
                    rowExist++;
                    saveRow = i;
                    for (int k = startIdx; k < startIdx + K; k++) {
                        check[i][k][0] = true; // 연속된 1의 구간에 true 설정
                    }
                }
            }
        }

        for (int i = 0; i < M; i++) {
            int cnt = 0;
            int startIdx = -1; // 연속된 1의 시작 인덱스

            for (int j = 0; j < N; j++) {
                if (arr[j][i] == 1) {
                    if (cnt == 0) startIdx = j; // 첫 번째 1이 나온 위치 기록
                    cnt++; // 연속된 1의 개수 증가
                } else {
                    cnt = 0; // 1이 끊어지면 연속된 1의 개수 초기화
                }

                // 연속된 1의 개수가 K에 도달하면 check를 true로 설정
                if (cnt >= K) {
                    colExist++;
                    saveCol = i;
                    for (int k = startIdx; k < startIdx + K; k++) {
                        check[k][i][1] = true; // 연속된 1의 구간에 true 설정
                    }
                }
                if (K == 1 && rowExist == 1)
                    colExist = 0;
            }
        }


        if (rowExist >= 1 && colExist >= 1) { // 가로, 세로
            if (rowExist >= K && colExist >= K) {
                sb.append(0);
            } else {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (check[i][j][0] && check[i][j][1])
                            togetherGrow.offer(new Point(i, j));
                    }
                }
            }
        } else if (rowExist >= 1 && colExist == 0) { // 가로만 있는 경우
            if (rowExist == 1) { // 겹치는 경우
                int cnt = 0;
                int firstOneIdx = -1;
                boolean firstOne = false;
                for (int i = 0; i < M; i++) {
                    if (arr[saveRow][i] == 1) {
                        if (!firstOne) {
                            firstOneIdx = i;
                            firstOne = true;
                        }
                        cnt++;
                    }
                }

                if (cnt >= 2 * K) {
                    sb.append(0);
                } else if (cnt >= K && cnt < 2 * K) {
                    int start = K - (2 * K - cnt);
                    for (int j = firstOneIdx + start; j < firstOneIdx + K; j++)
                        togetherGrow.offer(new Point(saveRow, j));
                }
            } else if (rowExist >= 2) { // 안겹치는 경우
                sb.append(0);
            }
        } else if (rowExist == 0 && colExist >= 1) { // 세로만 있는 경우
            if (colExist == 1) { // 겹치는 경우
                int cnt = 0;
                int firstOneIdx = -1;
                boolean firstOne = false;
                for (int i = 0; i < N; i++) {
                    if (arr[i][saveCol] == 1) {
                        if (!firstOne) {
                            firstOneIdx = i;
                            firstOne = true;
                        }
                        cnt++;
                    }
                }
                if (cnt >= 2 * K) {
                    sb.append(0);
                } else if (cnt >= K && cnt < 2 * K) {
                    int start = K - (2 * K - cnt);
                    for (int j = firstOneIdx + start; j < firstOneIdx + K; j++)
                        togetherGrow.offer(new Point(j, saveCol));
                }
            } else if (colExist >= 2) { // 안겹치는 경우
                sb.append(0);
            }
        }
        if (togetherGrow.size() > 0) {
            sb.append(togetherGrow.size() + "\n");
            while (!togetherGrow.isEmpty()) {
                Point p = togetherGrow.poll();
                sb.append((p.r + 1) + " " + (p.c + 1) + "\n");
            }
        }
        System.out.print(sb);
    }
}