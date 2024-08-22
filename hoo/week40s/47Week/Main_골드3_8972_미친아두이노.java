package SSAFY.study.algo.week47;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_골드3_8972_미친아두이노 {

    static class Arduino {
        int row;
        int col;

        public Arduino(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {  // List에 들어있는 미친 아두이노의 remove 메서드를 이용한 삭제를 위해 equals 함수 오버라이드
            Arduino a = (Arduino) o;    // 비교를 위해 하위 타입(Arduino)으로 캐스팅
            if (this.row == a.row && this.col == a.col) return true;    // 같은 위치에 있으면 같은 것으로 간주
            return false;
        }
    }

    static int[] dirRow = new int[] {0, 1, 1, 1, 0, 0, 0, -1, -1, -1}; // 좌하, 하, 우하, 좌, 우, 좌상, 상, 우상 1~9까지의 방향, 0과 5자리에는 인덱스 처리를 위해 0 넣어줌
    static int[] dirCol = new int[] {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    static int R, C;
    static String[][] board;
    static int[] commands;
    static List<Arduino> crazyArduinoList;  // 현 시점의 미친 아두이노들을 담고 있는 리스트
    static boolean isDead;  // 종수의 아두이노가 죽었는 지 여부
    static int moveCount;

    public static void main(String[] args) throws IOException {
        Arduino initJongsuArduino = init();
        doRobots(initJongsuArduino);
        StringBuilder sb = new StringBuilder();
        if (isDead) sb.append("kraj " + moveCount);
        else {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) sb.append(board[i][j]);
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    static Arduino init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new String[R][C];
        crazyArduinoList = new ArrayList<>();
        Arduino jongsuArduino = null;
        String row;
        for (int i = 0; i < R; i++) {
            row = br.readLine();
            String col;
            for (int j = 0; j < C; j++) {
                col = String.valueOf(row.charAt(j));
                if (col.equals("R")) crazyArduinoList.add(new Arduino(i, j));
                else if (col.equals("I")) {
                    jongsuArduino = new Arduino(i, j);
                    board[i][j] = ".";  // 종수 아두이노의 위치는 맵에 굳이 표시 X
                    continue;
                }
                board[i][j] = col;
            }
        }
        String command = br.readLine();
        commands = new int[command.length()];
        for (int i = 0; i < command.length(); i++) commands[i] = Integer.parseInt(String.valueOf(command.charAt(i)));
        isDead = false;
        moveCount = 0;

        return jongsuArduino;
    }

    static void doRobots(Arduino jongsuArduino) {
        for (int command: commands) { // 커맨드 수만큼 수행
            jongsuArduino = moveJongsuArduino(jongsuArduino, command);
            if (board[jongsuArduino.row][jongsuArduino.col].equals("R")) {  // 종수가 움직인 칸에 미친 아두이노가 있으면 게임 종료
                isDead = true;
                return;
            }
            moveCrazyArduinos(jongsuArduino);
            if (isDead) return; // 미친 아두이노가 종수의 아두이노를 잡으면 게임종료
        }

        board[jongsuArduino.row][jongsuArduino.col] = "I";  // 끝까지 살아남았으면 종수 아두이노 보드 상에 표기
    }

    static Arduino moveJongsuArduino(Arduino jongsuArduino, int command) {
        moveCount += 1;
        return new Arduino(jongsuArduino.row + dirRow[command], jongsuArduino.col + dirCol[command]);
    }

    static void moveCrazyArduinos(Arduino jongsuArduino) {
        List<Arduino> tempArduinoList = new ArrayList<>();
        for (Arduino crazyArduino : crazyArduinoList) {
            board[crazyArduino.row][crazyArduino.col] = ".";    // 미친 아두이노가 있던 자리는 .으로 갱신
            int[] movedAxis = findAxisToMove(jongsuArduino, crazyArduino);  // 8방향 탐색 후 얻은 움직일 좌표
            if (movedAxis[0] == -1) {   // 미친 아두이노가 종수의 아두이노를 잡은 경우
                isDead = true;
                return;
            }
            tempArduinoList.add(new Arduino(movedAxis[0], movedAxis[1]));   // 일단 임시리스트에 저장
        }
        markCrazyArduinos(tempArduinoList);
    }

    static int[] findAxisToMove(Arduino jongsuArduino, Arduino crazyArduino) {
        int minDist = R*C;    // 미친 아두이노와 종수 아두이노의 최소 거리 저장
        int[] minAxis = null;  // 그때의 좌표 저장
        int dist, nextRow, nextCol;
        for (int d = 1; d < 10; d++) {  // 8방향에 대해서 수행
            if (d == 5) continue;   // 5는 없는 방향으므로 스킵
            nextRow = crazyArduino.row + dirRow[d];
            nextCol = crazyArduino.col + dirCol[d];
            dist = Math.abs(nextRow - jongsuArduino.row) + Math.abs(nextCol - jongsuArduino.col);

            if (dist == 0) return new int[] {-1, -1};   // 거리가 0인 경우 종수 아두이노를 잡은 경우임
            else if (dist < minDist) {   // 거리 갱신 시 새 정보 저장
                minDist = dist;
                minAxis = new int[] {nextRow, nextCol};
            }
        }

        return minAxis;
    }

    static void markCrazyArduinos(List<Arduino> tempArduinoList) {  // 모든 미친 아두이노가 동시에 움직인 후 터지는 아두이노 처리 및 보드에 표시
        List<Arduino> bombArduinoList = new ArrayList<>();
        int row, col;
        for (Arduino crazyArduino : tempArduinoList) {  // 터지는 아두이노 객체는 리스트에 담아주면서, 보드에 우선은 표시함
            row = crazyArduino.row;
            col = crazyArduino.col;
            if (board[row][col].equals("R")) bombArduinoList.add(new Arduino(row, col));
            board[row][col] = "R";
        }
        for (Arduino bombArduino : bombArduinoList) board[bombArduino.row][bombArduino.col] = ".";  // 터지는 아두이노가 있는 자리는 .으로 갱신
        tempArduinoList.removeAll(bombArduinoList); // 터지는 아두이노 모두 삭제
        crazyArduinoList = tempArduinoList; // 터지는 아두이노 모두 삭제된 아두이노 리스트로 갱신
    }

}
