package SSAFY.study.algo.week43;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class 틱택토_내가한거 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = br = new BufferedReader(new InputStreamReader(System.in));;
        String inputLine;
        String[][] board;
        HashMap<Character, Integer> countMap;   // X, O, 빈칸 개수 저장할 map
        StringBuilder sb = new StringBuilder();
        while (true) {
            inputLine = br.readLine();
            if (inputLine.equals("end")) break; // 종료 입력 시 반복 종료

            countMap = new HashMap<>();
            countMap.put('X', 0);
            countMap.put('O', 0);
            countMap.put('.', 0);
            board = new String[3][3];
            char input;
            for (int i = 0; i < 9; i++) {
                input = inputLine.charAt(i);  // 격자판에 틱택토 그리기
                board[i/3][i%3] = String.valueOf(input);
                countMap.put(input, countMap.get(input)+1);
            }
            if (calcIfValid(countMap, board)) sb.append("valid").append("\n");
            else sb.append("invalid").append("\n");
        }
        if (sb.length() == 0) return;
        System.out.println(sb);
    }

    static boolean calcIfValid(HashMap<Character, Integer> countMap, String[][] board) {
        String tictactoeResult = isTicTacToe(board);    // 틱택토 결과 저장해놓음
        if (tictactoeResult.equals("D")) return false;  // X, O 둘 다 틱택토가 되는 건 불가능한 경우
        if (countMap.get('.') == 0) {   // 빈칸이 하나도 없을 때
            if (countMap.get('X')-1 != countMap.get('O')) return false;   // 1. X개수 5 아니면 무조건 불가능한 경우
            if (tictactoeResult.equals("O")) return false;   // 2. O가 틱택토가 되면 불가능한 경우
        } else {    // 빈칸이 하나라도 있을 때
            if (tictactoeResult.equals("N")) return false;  // 틱택토가 안됐는데 빈칸이 있는 건 불가능한 경우
            if (tictactoeResult.equals("X") && countMap.get('O') != countMap.get('X') - 1) return false;   // X가 틱택토일 때 O개수가 X개수보다 한 개 적지 않으면 불가능한 경우
            if (tictactoeResult.equals("O") && countMap.get('O') != countMap.get('X')) return false;    // O가 틱택토일 때 O개수가 X개수와 같지 않으면 불가능한 경우
        }

        return true;
    }

    static String isTicTacToe(String[][] board) {
        Set<String> tictactoeSet = new HashSet<>(); // 틱택토가 되는 문자 일단 다 저장
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals(".") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) tictactoeSet.add(board[i][0]); // 행 틱택토 판단
            if (!board[0][i].equals(".") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) tictactoeSet.add(board[0][i]);    // 열 틱택토 판단
        }
        if (!board[0][0].equals(".") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) tictactoeSet.add(board[0][0]); // 왼쪽 대각선 틱택토 판단
        if (!board[2][0].equals(".") && board[2][0].equals(board[1][1]) && board[1][1].equals(board[2][0])) tictactoeSet.add(board[2][0]);    // 오른쪽 대각선 틱택토 판단

        if (tictactoeSet.size() >= 2) return "D";   // X, O 모두 틱택토가 그려진 경우
        else if (tictactoeSet.size() == 1) {
            Iterator iter = tictactoeSet.iterator();
            return iter.next().toString();
        } else return "N"; // 틱택토가 아닌 경우 N을 리턴함
    }

}
