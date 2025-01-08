package twentytwentyfive.january.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2234_성곽 {
    static int[] dirRow = new int[] {0, -1, 0, 1};
    static int[] dirCol = new int[] {-1, 0, 1, 0};

    static int N, M;
    static int[][] map; // 각 칸의 벽 정보를 비트로 저장하는 배열
    static List<Integer> roomAreaList;  // 각 방 번호의 인덱스에 해당 방의 크기 저장하는 리스트

    public static void main(String[] args) throws IOException {
        init();
        int[][] roomNumberArr = new int[N][M];  // 각 칸에 방의 번호를 저장해줄 배열
        int roomNumber = 1;
        int widestRoom = 0; // 가장 넓은 방의 크기 저장하는 변수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (roomNumberArr[i][j] != 0) continue; // 이미 방 번호 매겨졌으면 건너 뜀
                int roomArea = checkRoomArea(i, j, roomNumberArr, roomNumber++);
                roomAreaList.add(roomArea);
                widestRoom = Math.max(widestRoom, roomArea);
            }
        }

        int widestBrokenWallRoom = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                widestBrokenWallRoom = Math.max(widestBrokenWallRoom, breakWallAndCalcArea(i, j, roomNumberArr));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(roomNumber-1).append("\n");
        sb.append(widestRoom).append("\n");
        sb.append(widestBrokenWallRoom).append("\n");
        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        roomAreaList = new ArrayList<>();
        roomAreaList.add(0);    // 방 번호는 1번부터 시작하므로 0번 방에는 크기 0 넣어줌
    }

    static int checkRoomArea(int row, int col, int[][] roomNumberArr, int roomNumber) { // 주어진 각 칸 별 벽 정보를 이용해 방의 크기를 확인하는 함수
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {row, col});
        roomNumberArr[row][col] = roomNumber;

        int roomArea = 1;   // 방의 넓이
        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();

            // 0 0 0 1  좌 상 우 하 순으로 벽이 있는 경우를 나타내는 비트
            // 0 0 1 0
            // 0 1 0 0
            // 1 0 0 0
            int nowBit = 1;
            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];
                if ( (nowBit & map[now[0]][now[1]]) == nowBit || roomNumberArr[nextRow][nextCol] != 0 ) {  // 만약 해당 방향 벽이 막혀있는 상황이면 못 감
                    nowBit = nowBit << 1;
                    continue;
                }
                q.offer(new int[] {nextRow, nextCol});
                roomNumberArr[nextRow][nextCol] = roomNumber;
                roomArea++; // 방 크기 1 확장
                nowBit = nowBit << 1;   // 다음 방향의 벽 체크하기 위해 왼쪽으로 한 칸 쉬프트
            }
        }

        return roomArea;
    }

    static int breakWallAndCalcArea(int row, int col, int[][] roomNumberArr) {    // 벽을 부수고 만들 수 있는 가장 큰 방의 크기를 체크하는 함수
        int maxValue = 0;

        int nextRow, nextCol;
        for (int d = 0; d < 4; d++) {
            nextRow = row + dirRow[d];
            nextCol = col + dirCol[d];
            if (isOuted(nextRow, nextCol) || roomNumberArr[nextRow][nextCol] == roomNumberArr[row][col]) continue;

            maxValue = Math.max(maxValue, roomAreaList.get(roomNumberArr[row][col]) + roomAreaList.get(roomNumberArr[nextRow][nextCol]));
        }

        return maxValue;
    }

    static boolean isOuted(int row, int col) {    // 범위 밖인 건 벽으로 방지가 돼서 굳이 필요 없는 듯
        if (row < 0 || row >= N || col < 0 || col >= M) return true;
        return false;
    }

}
