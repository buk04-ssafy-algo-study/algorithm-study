package Week75;

import java.io.*;
import java.util.*;

public class 백준_10472_십자뒤집기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			char[][] goal = new char[3][3];
			
			for(int i=0; i<3; i++) {
				String line = br.readLine();
				for(int j=0; j<line.length(); j++) {
					goal[i][j] = line.charAt(j);
				}
			}
			
			sb.append(solve(goal)+"\n");
		}
		System.out.println(sb);
	}
	
	private static int solve(char[][] goal) {
		
		Queue<char[][]> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        
        // 초기 상태를 큐에 추가
        char[][] start = new char[3][3];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(start[i], '.'); // 모든 칸을 흰색으로 초기화
        }
        
        // 큐에 초기 상태 추가
        q.offer(start);
        visited.add(boardToString(start)); // 상태를 문자열로 변환하여 방문 처리
		
		int result = 0;
		
		while(!q.isEmpty()) {
			
			int qs = q.size();

			while(qs > 0) {
				qs--;
				char[][] qp = q.poll();
				
				 // 목표 상태에 도달했는지 체크
	            boolean same = true;
	            for (int i = 0; i < 3; i++) {
	                for (int j = 0; j < 3; j++) {
	                    if (qp[i][j] != goal[i][j]) {
	                        same = false;
	                        break;
	                    }
	                }
	            }

	            if (same) return result;

				 // 십자 모양을 눌러서 상태 변경
                for (int i = 0; i < 9; i++) {
                    char[][] cross = cloneBoard(qp);
                    applyCross(cross, i);

                 // 상태를 문자열로 변환하여 방문 여부 체크
                    String boardStr = boardToString(cross);
                    if (!visited.contains(boardStr)) {
//                    	System.out.println(boardStr);
                        visited.add(boardStr);
                        q.offer(cross);
                    }
                }

			}
			result++;
		}
		return result;
	}
	
	private static char[][] cloneBoard(char[][] board) {
        char[][] cloned = new char[3][3];
        for(int i = 0; i < 3; i++) {
        	for(int j=0; j < 3; j++) {
        		cloned[i][j] = board[i][j];
        	}
        }
        return cloned;
    }
	
	private static void applyCross(char[][] board, int idx) {
		
			if(idx==0) {
				board[0][0] = board[0][0] == '.' ? '*' : '.'; 
				board[1][0] = board[1][0] == '.' ? '*' : '.'; 
				board[0][1] = board[0][1] == '.' ? '*' : '.'; 
			 
			}
			
			if(idx==1) {
				board[0][0] = board[0][0] == '.' ? '*' : '.'; 
				board[0][1] = board[0][1] == '.' ? '*' : '.'; 
				board[0][2] = board[0][2] == '.' ? '*' : '.'; 
				board[1][1] = board[1][1] == '.' ? '*' : '.'; 
				 
			}

			if(idx==2) {
				board[0][1] = board[0][1] == '.' ? '*' : '.'; 
				board[0][2] = board[0][2] == '.' ? '*' : '.'; 
				board[1][2] = board[1][2] == '.' ? '*' : '.'; 
				 
			}
			
			if(idx==3) {
				board[0][0] = board[0][0] == '.' ? '*' : '.'; 
				board[1][0] = board[1][0] == '.' ? '*' : '.'; 
				board[1][1] = board[1][1] == '.' ? '*' : '.'; 
				board[2][0] = board[2][0] == '.' ? '*' : '.'; 
				 
			}
			
			if(idx==4) {
				board[0][1] = board[0][1] == '.' ? '*' : '.'; 
				board[1][1] = board[1][1] == '.' ? '*' : '.'; 
				board[1][0] = board[1][0] == '.' ? '*' : '.'; 
				board[1][2] = board[1][2] == '.' ? '*' : '.'; 
				board[2][1] = board[2][1] == '.' ? '*' : '.'; 
				 
			}
			
			if(idx==5) {
				board[0][2] = board[0][2] == '.' ? '*' : '.'; 
				board[1][1] = board[1][1] == '.' ? '*' : '.'; 
				board[1][2] = board[1][2] == '.' ? '*' : '.'; 
				board[2][2] = board[2][2] == '.' ? '*' : '.'; 
				 
			}
			
			if(idx==6) {
				board[1][0] = board[1][0] == '.' ? '*' : '.'; 
				board[2][0] = board[2][0] == '.' ? '*' : '.'; 
				board[2][1] = board[2][1] == '.' ? '*' : '.'; 
				 
			}
			
			if(idx==7) {
				board[1][1] = board[1][1] == '.' ? '*' : '.'; 
				board[2][0] = board[2][0] == '.' ? '*' : '.'; 
				board[2][1] = board[2][1] == '.' ? '*' : '.'; 
				board[2][2] = board[2][2] == '.' ? '*' : '.'; 
				  
			}
			
			if(idx==8) {
				board[1][2] = board[1][2] == '.' ? '*' : '.'; 
				board[2][1] = board[2][1] == '.' ? '*' : '.'; 
				board[2][2] = board[2][2] == '.' ? '*' : '.'; 
				
			}
    }
	
	private static String boardToString(char[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }
}
