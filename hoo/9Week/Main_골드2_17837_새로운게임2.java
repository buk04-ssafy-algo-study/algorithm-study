package SSAFY.study.algo.week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_골드2_17837_새로운게임2 {

    private static class Piece {    // 말 객체
        int row;
        int col;
        int dir;    // 방향, 1; 오, 2: 왼, 3: 상, 4: 하

        public Piece(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "(행: " + this.row + " 열: " + this.col + " 방향: " + this.dir + ")";
        }
    }

    private static int[] dirRow = {0, 0, 0, -1, 1};    // 오, 왼, 상, 하
    private static int[] dirCol = {0, 1, -1, 0, 0};    // 오, 왼, 상, 하

    private static int N;    // 체스판 크기
    private static int K;    // 말의 개수
    private static int[][] board;    // 체스판 정보
    private static List<Integer>[][] pieceBoard;    // 체스판 위에 말이 놓여진 상황
    private static Piece[] pieces;    // 체스 말들 담은 배열(한 턴에서 말의 이동을 위해 사용)

    public static void main(String[] args) throws Exception {
        init();
        int turn = 0;

        while (turn <= 1000) {    // 모든 말이 얹어질 때까지 혹은 절대 경우가 안나온다면 중단
            turn += 1;
            boolean isOver = true;
            for (int i = 0; i < K; i++) {
                isOver = doGame(i);
                if (isOver) break;  // 종료 조건 달성 시 말 옮기기 멈춤
            }
            if (isOver) break;    // 종료 조건 달성 시 반복문 멈춤
        }

        if (turn <= 1000) { // 1000회 안에 종료 조건 달성했다면 정답 출력
            System.out.println(turn);
            return;
        }
        System.out.println(-1); // 1000보다 큰 경우는 -1 출력
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        pieceBoard = new List[N][N];    // 빈 칸 : null로 초기화 됨
        for (int i = 0; i < N; i++) {    // 0: 흰 칸(가장 위에 옮긴 말 두기), 1: 빨간 칸(이동하고 이동한 말들 순서 뒤집기), 2: 파란 칸(이동 방향 반대로 하고 이동, 방향 바꾼 후에 파란 칸이면 가만히)
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                pieceBoard[i][j] = new ArrayList<>();
            }
        }

        pieces = new Piece[K];
        for (int i = 0; i < K; i++) {    // 말 리스트에 말 추가(말 번호 : 0부터 시작)
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken())-1;
            int col = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());
            pieces[i] = new Piece(row, col, dir);    // 행, 열은 1부터 시작하므로 -1한 값 넣어줌
            pieceBoard[row][col].add(i);    // 초기 보드 위의 말 위치 저장
        }
    }

    private static boolean isOuted(int nextRow, int nextCol) {  // 움직임이 범위를 벗어나는지 체크
        if ((0 <= nextRow && nextRow < N) && (0 <= nextCol && nextCol < N)) return false;

        return true;
    }

    private static int wherePiece(int pieceIdx, int turnPieceRow, int turnPieceCol) {   // 말이 해당 칸의 어느 위치에 있는지 찾는 함수
        int where = 0;
        for (int i = 0; i < 4; i++) {   // 0 ~ 3층 사이에 말이 있을 것이므로 4까지 반복
            if (pieceBoard[turnPieceRow][turnPieceCol].get(i) == pieceIdx) {
                where = i;
                break;
            }
        }

        return where;
    }

    private static void movePieces(int nextRow, int nextCol, List<Integer> movePieces, int i) { // 칸에 있는 리스트에서 옮겨야하는 말들 옮기는 함수
        int movePieceIdx = movePieces.get(i);
        pieceBoard[nextRow][nextCol].add(movePieceIdx);
        pieces[movePieceIdx].row = nextRow;
        pieces[movePieceIdx].col = nextCol;
    }

    private static void doMove(int wherePiece, int turnPieceRow, int turnPieceCol, int nextRow, int nextCol) {   // 말을 움직이는 함수
        int partitionSize = pieceBoard[turnPieceRow][turnPieceCol].size();  // 옮기기 전 말이 있는 칸의 말 개수
        List<Integer> movePieces = pieceBoard[turnPieceRow][turnPieceCol].subList(wherePiece, partitionSize);   // 현재 있는 말 위로는 다 옮김
        if (board[nextRow][nextCol] == 0) { // 움직이려는 칸이 흰 칸이면 그냥 쌓고 해당 말의 인덱스 바꿔줌
            for (int i = 0; i < movePieces.size(); i++) {
                movePieces(nextRow, nextCol, movePieces, i);
            }
        } else if (board[nextRow][nextCol] == 1) {  // 움직이려는 칸이 빨간 칸이면 역으로 쌓고 해당 말의 인덱스 바꿔줌
            for (int i = movePieces.size()-1; i >= 0; i--) {
                movePieces(nextRow, nextCol, movePieces, i);
            }
        }

        for (int i = partitionSize-1; i >= wherePiece; i--) {   // 원래 칸에서 옮긴 말들은 지워줌
            pieceBoard[turnPieceRow][turnPieceCol].remove(i);
        }
    }

    private static boolean doGame(int pieceIdx) {
        Piece turnPiece = pieces[pieceIdx];    // 현재 차례의 말
        int turnPieceRow = turnPiece.row;
        int turnPieceCol = turnPiece.col;
        int wherePiece = wherePiece(pieceIdx, turnPieceRow, turnPieceCol);  // 현재 말이 있는 층
        int turnPieceDir = turnPiece.dir;

        int nextRow = turnPieceRow + dirRow[turnPieceDir];  // 말이 움직일 행
        int nextCol = turnPieceCol + dirCol[turnPieceDir];  // 말이 움직일 열
        if (isOuted(nextRow, nextCol) || board[nextRow][nextCol] == 2) {    // 나가는 경우는 파란색과 같은 취급, 방향 반대로 하고 한 칸 이동
            if (turnPieceDir == 1) turnPiece.dir = 2;
            else if (turnPieceDir == 2) turnPiece.dir = 1;
            else if (turnPieceDir == 3) turnPiece.dir = 4;
            else if (turnPieceDir == 4) turnPiece.dir = 3;
            turnPieceDir = turnPiece.dir;	// 수정된 방향으로 reset

            nextRow = turnPieceRow + dirRow[turnPieceDir];  // 방향 전환 후 말이 움직일 행 reset
            nextCol = turnPieceCol + dirCol[turnPieceDir];  // 방향 전환 후 말이 움직일 열 reset
            if (isOuted(nextRow, nextCol) || board[nextRow][nextCol] == 2) return false;    // 방향 바꾸고 나서도 범위 밖으로 나가거나 파란 칸이면 턴 종료
            else {  // 방향 바꾸고 나서는 파란 칸이나 범위 바깥이 아니라면
                doMove(wherePiece, turnPieceRow, turnPieceCol, nextRow, nextCol);
            }
        }
        else {  // 처음부터 움직일 칸이 흰 칸이나 빨간 칸이라면
            doMove(wherePiece, turnPieceRow, turnPieceCol, nextRow, nextCol);
        }

        if (pieceBoard[nextRow][nextCol].size() >= 4) return true;  // 움직인 칸에 말이 4개 이상 들어있다면 게임 종료조건 달성

        return false;
    }


}
