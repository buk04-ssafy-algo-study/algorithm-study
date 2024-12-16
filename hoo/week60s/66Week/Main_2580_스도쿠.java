package SSAFY.study.algo.week60s.week66;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2580_스도쿠 {

    static int[] dirRow = new int[] {-1, 0, 1, 0};  // 상 좌 하 우
    static int[] dirCol = new int[] {0, -1, 0, 1};
    static int[][] board;
    static boolean isCompleted; // 스도쿠 완성됐는 지 여부 저장하는 변수
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        List<int[]> blankList = init();
        doSudoku(blankList, 0);
        System.out.println(sb);
    }

    static List<int[]> init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[9][9];
        isCompleted = false;
        sb = new StringBuilder();
        List<int[]> blankList = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) blankList.add(new int[] {i, j});
            }
        }

        return blankList;
    }

    static void doSudoku(List<int[]> blankList, int cnt) {    // 스도쿠 진행
        if (isCompleted) return;    // 스도쿠 판 완성된 경우 함수 진행 종료
        if (cnt == blankList.size()) {  // 모든 칸 다 채운 경우, 스도쿠 판 완성됐음을 체크 후 함수 종료
            isCompleted = true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j]).append(" ");
                }
                sb.append("\n");
            }

            return;
        }

        int[] now = blankList.get(cnt);
        for (int n = 1; n <= 9; n++) {
            if (!checkLine(now, n) || !checkSquare(now, n)) continue;   // 행, 열, 사각형 내에 이미 있는 숫자면 건너 뜀
            board[now[0]][now[1]] = n;
            doSudoku(blankList, cnt+1);  // 다음 칸에 대해 재귀 수행
            board[now[0]][now[1]] = 0;
        }

    }

    static boolean checkLine(int[] axis, int nowNumber) {
        for (int i = 0; i < 9; i++) {
            if (i != axis[0] && board[i][axis[1]] == nowNumber) return false;   // 지금 열 모두 체크하는데 같은 숫자 이미 있으면 거짓 반환
            if (i != axis[1] && board[axis[0]][i] == nowNumber) return false;   // 지금 행 ~
        }

        return true;    // 조건 충족 시 참 반환
    }

    static boolean checkSquare(int[] axis, int nowNumber) {
        int rowOffset = (axis[0] / 3) * 3;  // 정사각형의 행이 시작하는 위치
        int colOffset = (axis[1] / 3) * 3;  // 열이 시작하는 위치
        for (int i = rowOffset; i < rowOffset + 3; i++) {   // 정사각형의 행
            for (int j = colOffset; j < colOffset + 3; j++) {   // 정사각형의 열
                if (board[i][j] == nowNumber) return false; // 정사각형 내에 이미 있는 숫자면 거짓 반환
            }
        }

        return true;    // 조건 충족 시 참 반환
    }

}
