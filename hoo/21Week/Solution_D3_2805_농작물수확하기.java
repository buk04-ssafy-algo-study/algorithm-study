package SSAFY.study.algo.week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_2805_농작물수확하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int N;
        int[][] map;
        for (int t = 1; t <= T; t++) {
            sb.append("#" + t + " " );
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String row = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(String.valueOf(row.charAt(j)));
                }
            }

            sb.append(count(map)).append("\n");
        }
        System.out.println(sb);
    }

    static int count(int[][] map) {
        int result = 0;

        int start = map.length/2;   // 각 행에서 더해줄 열의 시작값
        int end = start+1;  // 열의 끝값(인덱스라 +1)

        for (int i = 0; i < map.length; i++) {
            for (int j = start; j < end; j++) {
                result += map[i][j];
            }
            if (i < map.length / 2) {
                start -= 1;
                end += 1;
            } else {
                start += 1;
                end -= 1;
            }
        }

        return result;
    }

}
