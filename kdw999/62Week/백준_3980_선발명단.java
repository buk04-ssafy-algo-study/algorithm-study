package Week62;

import java.io.*;
import java.util.*;

public class 백준_3980_선발명단 {

	static int[][] stat;
	static boolean[] player;
	static int maxStat;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
		int C = Integer.parseInt(br.readLine()); // 테케
		
		stat = new int[11][11];
		player = new boolean[11];
		
		for(int tc=1; tc<=C; tc++) {
			maxStat = 0;
			
			for(int i=0; i<11; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<11; j++) {
					stat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// DFS를 통해 배치 가능한 선수 조합을 찾아 능력치 합 계산
            dfs(0, 0);
            sb.append(maxStat).append("\n");
        }
        System.out.print(sb);
    }

    // DFS로 포지션에 선수를 배치
    static void dfs(int pos, int score) {
        if (pos == 11) {
            // 모든 포지션에 선수가 배치되면 최대 능력치 갱신
            maxStat = Math.max(maxStat, score);
            return;
        }

        // 각 포지션에 적합한 선수를 배치
        for (int i = 0; i < 11; i++) {
            if (!player[i] && stat[i][pos] > 0) {  // 선수 i가 아직 배치되지 않았고, 능력치가 0이 아닌 경우
                player[i] = true;  // 선수를 배치
                dfs(pos + 1, score + stat[i][pos]);  // 다음 포지션으로 재귀 호출
                player[i] = false;  // 다시 선수를 비활성화
            }
        }
    }
}
