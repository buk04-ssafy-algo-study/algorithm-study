// https://www.acmicpc.net/problem/7682
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String input = br.readLine();
            if(input.equals("end")) break;
            
            char[][] board = new char[3][3];
            int xCnt = 0;
            int oCnt = 0;
            for(int r=0;r<3;r++) {
                for(int c=0;c<3;c++) {
                    board[r][c] = input.charAt(r*3+c);
                    if(board[r][c]=='X') xCnt++;
                    else if(board[r][c]=='O') oCnt++;
                }
            }
            
            sb.append(check(board, xCnt, oCnt) ? "valid\n" : "invalid\n");
        }
        System.out.println(sb.toString());
    }

    static boolean check(char[][] board, int xCnt, int oCnt) {

        if(oCnt>xCnt || xCnt-oCnt>=2) return false;


        boolean isOSuccess = isSuccess(board, 'O');
        boolean isXSuccess = isSuccess(board, 'X');

        if(isOSuccess && isXSuccess) return false;
        if(xCnt+oCnt==9) {
            if(isOSuccess) return false;
            if(xCnt-oCnt != 1) return false;
        } else {
            if(isOSuccess) {
                if(xCnt !=oCnt) return false;
            } else if(isXSuccess) {
                if(xCnt-1 != oCnt) return false;
            } else return false;
        }
        return true;
    }

    static boolean isSuccess(char[][] board, char ch) {
        
        for(int r=0;r<3;r++) {
            int cnt = 0;
            for(int c=0;c<3;c++) {
                if(board[r][c]==ch) cnt++;
            }
            if(cnt==3) return true;
        }

        for(int c=0;c<3;c++) {
            int cnt = 0;
            for(int r=0;r<3;r++) {
                if(board[r][c]==ch) cnt++;
            }
            if(cnt==3) return true;
        }

        if(board[0][0]==ch && board[1][1]==ch && board[2][2]==ch) {
            return true;
        }
        if(board[0][2]==ch && board[1][1]==ch && board[2][0]==ch) {
            return true;
        }

        return false;
    }

}
