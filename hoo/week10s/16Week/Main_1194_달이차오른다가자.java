package SSAFY.study.algo.week10s.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1194_달이차오른다가자 {

    static class Ms {   // 이동 중인 민식이 표현할 클래스
        int row;
        int col;
        int dist;
        int havingKey;    // a~f 키를 가지고 있는 지 여부 저장할 값, 비트 형식으로 할 거라 정수로 선언

        public Ms(int row, int col, int dist, int havingKey) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.havingKey = havingKey;    // 각 객체 별로 다른 배열 참조하게 깊은 복사 수행
        }
    }

    static int N, M;
    static char[][] maze;

    public static void main(String[] args) throws IOException {
        Ms startMs = init();
        System.out.println(findShortestRoute(startMs));
    }

    static Ms init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new char[N][M];

        Ms startMs = new Ms(0, 0, 0, 0);
        String inputRow;
        for (int i = 0; i < N; i++) {
            inputRow = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = inputRow.charAt(j);
                if (maze[i][j] == '0') startMs = new Ms(i, j, 0, 0);
            }
        }

        return startMs;
    }

    static int findShortestRoute(Ms startMs) {
        Queue<Ms> q = new ArrayDeque<>();
        boolean[][][] isVisited = new boolean[N][M][64];    // 각 열쇠 별 가지고 있는 여부에 따라 이동 거리 저장해주는 배열, 각 열쇠를 가지고 있는 모든 경우를 체크해주기 위해 0~64 값을 가지게 선언
        q.offer(startMs);
        isVisited[startMs.row][startMs.col][0] = true;

        int[] dirRow = new int[] {-1, 0, 1, 0};
        int[] dirCol = new int[] {0, 1, 0, -1};
        Ms now;
        while (!q.isEmpty()) {
            now = q.poll();
            if (maze[now.row][now.col] == '1') return now.dist;    // 탈출에 성공한 민식이가 이동한 거리 반환

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now.row + dirRow[d];
                nextCol = now.col + dirCol[d];
                if (0 > nextRow || N <= nextRow || 0 > nextCol || M <= nextCol || maze[nextRow][nextCol] == '#')
                    continue;  // 미로 밖이나 벾은 무조건 건너 뜀
                if (isVisited[nextRow][nextCol][now.havingKey]) continue;   // 지금 열쇠 조합으로 이미 한 번 방문했던 곳은 건너 뜀

                char nextInfo = maze[nextRow][nextCol];
                if ((int) 'a' <= (int) nextInfo && (int) nextInfo <= (int) 'f') {    // 다음 칸이 열쇠인 경우
                    int nextKey = 1 << (nextInfo-'a');    // 각 열쇠의 정수형 번호(0~5)에 따라 왼쪽으로 쉬프트해줌, 그리고 현재 민식이가 가진 키에 해당 열쇠를 더해줌
                    nextKey = now.havingKey | nextKey;
                    q.offer(new Ms(nextRow, nextCol, now.dist+1, nextKey));
                    isVisited[nextRow][nextCol][nextKey] = true;
                } else if ((int) 'A' <= (int) nextInfo && (int) nextInfo <= (int) 'F') {    // 다음 칸이 문인 경우
                    if ( (now.havingKey & (1 << (nextInfo-'A')) ) == 0 ) continue; // 해당 문을 열기 위해 필요한 열쇠가 없는 경우 건너 뜀

                    q.offer(new Ms(nextRow, nextCol, now.dist+1, now.havingKey));
                    isVisited[nextRow][nextCol][now.havingKey] = true;
                } else {    // 나머지 경우는 그냥 바로 진행
                    q.offer(new Ms(nextRow, nextCol, now.dist+1, now.havingKey));
                    isVisited[nextRow][nextCol][now.havingKey] = true;
                }
            }
        }

        return -1;
    }

}
